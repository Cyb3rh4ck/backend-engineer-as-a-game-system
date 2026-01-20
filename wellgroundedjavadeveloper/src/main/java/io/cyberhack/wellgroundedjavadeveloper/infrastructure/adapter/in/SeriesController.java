package io.cyberhack.wellgroundedjavadeveloper.infrastructure.adapter.in;


import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cyberhack.wellgroundedjavadeveloper.application.service.CheckSeriesUseCase;

// 3. INFRAESTRUCTURA (Adaptador REST)
// Recibe JSON, lo convierte a Java, y llama al caso de uso.

@RestController
@RequestMapping("/api/v1/analysis")
public class SeriesController {

    private final CheckSeriesUseCase checkSeriesUseCase;

    public SeriesController(CheckSeriesUseCase checkSeriesUseCase) {
        this.checkSeriesUseCase = checkSeriesUseCase;
    }

    // Endpoint: POST /api/v1/analysis/duplicates
    // Body: { "numbers": [1, 2, 3, 1] }
    @PostMapping("/check-duplicates")
    public ResponseEntity<Map<String, Object>> checkDuplicates(@RequestBody SeriesRequest request) {
        boolean hasDuplicates = checkSeriesUseCase.execute(request.numbers());
        
        // Respondemos con un JSON estructurado
        return ResponseEntity.ok(Map.of(
            "hasDuplicates", hasDuplicates,
            "count", request.numbers().length,
            "status", "ANALYZED"
        ));
    }

    // Un DTO interno (Record de Java 17) para mapear el JSON de entrada
    public record SeriesRequest(int[] numbers) {}

}
