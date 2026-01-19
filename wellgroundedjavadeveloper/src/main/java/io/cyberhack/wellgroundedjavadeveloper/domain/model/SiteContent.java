package io.cyberhack.wellgroundedjavadeveloper.domain.model;

// 1. EL MODELO DE DOMINIO
// Usamos 'record' (Java 17+) para inmutabilidad.
// Esta clase NO sabe nada de HTTP, Status Codes ni Headers.
// Solo le importa el negocio: contenido y longitud.
public record SiteContent(String url, String content, int length) {

    // Podemos tener validaciones de negocio aquí (Rich Domain Model)
    public SiteContent {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("La URL no puede estar vacía");
        }
    }

}
