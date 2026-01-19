package io.cyberhack.wellgroundedjavadeveloper.application.service;

import org.springframework.stereotype.Service;

import io.cyberhack.wellgroundedjavadeveloper.domain.model.SiteContent;
import io.cyberhack.wellgroundedjavadeveloper.domain.ports.out.SiteRepositoryPort;

// 3. SERVICIO DE APLICACIÓN (Use Case)
// Implementa la lógica de negocio.
// Fíjate que usamos Inyección de Dependencias por Constructor.
// Inyectamos la INTERFAZ (Puerto), no la implementación concreta.

@Service
public class CheckSiteService {

    private final SiteRepositoryPort siteRepositoryPort;

    public CheckSiteService(SiteRepositoryPort siteRepositoryPort) {
        this.siteRepositoryPort = siteRepositoryPort;
    }

    public SiteContent checkSite(String url) {
        // Aquí iría lógica de negocio pura (ej. verificar palabras prohibidas, calcular métricas)
        System.out.println("Dominio: Solicitando análisis para " + url);
        return siteRepositoryPort.fetchContentByUrl(url);
    }

}
