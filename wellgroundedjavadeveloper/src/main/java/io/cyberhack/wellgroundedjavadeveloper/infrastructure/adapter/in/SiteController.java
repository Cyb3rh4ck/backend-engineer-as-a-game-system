package io.cyberhack.wellgroundedjavadeveloper.infrastructure.adapter.in;

import org.springframework.web.bind.annotation.RestController;

import io.cyberhack.wellgroundedjavadeveloper.application.service.CheckSiteService;
import io.cyberhack.wellgroundedjavadeveloper.domain.model.SiteContent;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


// 5. ADAPTADOR DE ENTRADA (REST Controller)
// El punto de entrada al hexágono.
@RestController
public class SiteController {

    private final CheckSiteService checkSiteService;

    public SiteController(CheckSiteService checkSiteService) {
        this.checkSiteService = checkSiteService;
    }

    @GetMapping("/api/analyze")
    public ResponseEntity<SiteContent> analyze(@RequestParam String url) {
        // Delegamos al Servicio de Aplicación
        SiteContent siteContent = checkSiteService.checkSite(url);
        return ResponseEntity.ok(siteContent);
    }
}
