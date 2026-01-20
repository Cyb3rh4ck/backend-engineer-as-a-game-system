package io.cyberhack.wellgroundedjavadeveloper.application.service;

import org.springframework.stereotype.Service;

import io.cyberhack.wellgroundedjavadeveloper.domain.service.DuplicateVerifier;

// 2. SERVICIO DE APLICACIÓN (Capas intermedias)
// Orquesta la petición. En un caso real, aquí agregaríamos logs de negocio,
// métricas o llamaríamos a múltiples servicios de dominio.

@Service
public class CheckSeriesUseCase {

    private final DuplicateVerifier duplicateVerifier;

    public CheckSeriesUseCase(DuplicateVerifier duplicateVerifier) {
        this.duplicateVerifier = duplicateVerifier;
    }

    public boolean execute(int[] series) {
        // Delegamos al servicio de dominio
        // Aquí podríamos transformar DTOs si fuera necesario.
        return duplicateVerifier.execute(series);
    }

}
