package io.cyberhack.wellgroundedjavadeveloper.infrastructure.adapter.out;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.springframework.stereotype.Component;

import io.cyberhack.wellgroundedjavadeveloper.domain.model.SiteContent;
import io.cyberhack.wellgroundedjavadeveloper.domain.ports.out.SiteRepositoryPort;

// 4. ADAPTADOR DE SALIDA (Infraestructura)
// AQUÍ es donde vive el código que aprendiste.
// Esta clase es "sucia", conoce detalles técnicos (HTTP, Excepciones, Timeouts).
// Transforma el mundo técnico al mundo del dominio.

@Component
public class JavaNativeHttpAdapter implements SiteRepositoryPort {

    private final HttpClient httpClient;

    public JavaNativeHttpAdapter() {
        // Configuramos el cliente una sola vez (Singleton por naturaleza del Bean)
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2) // Preferimos HTTP/2
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    @Override
    public SiteContent fetchContentByUrl(String url) {
        try {
            // A. Construir el Request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .header("User-Agent", "Janus-Bot/1.0")
                    .build();

            // B. Enviar Síncronamente (Blocking)
            // Usamos BodyHandlers.ofString() como en tu ejemplo
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // C. Validar respuesta técnica (Manejo de errores de Infraestructura)
            if (response.statusCode() != 200) {
                throw new RuntimeException("Error HTTP al conectar: " + response.statusCode());
            }

            // D. Mapear a Objeto de Dominio
            // El adaptador convierte el String crudo a nuestro 'SiteContent' limpio
            String body = response.body();
            return new SiteContent(url, body, body.length());

        } catch (IOException | InterruptedException e) {
            // InterruptedException requiere restaurar el flag de interrupción
            Thread.currentThread().interrupt();
            throw new RuntimeException("Fallo en la comunicación externa", e);
        }
    }

}
