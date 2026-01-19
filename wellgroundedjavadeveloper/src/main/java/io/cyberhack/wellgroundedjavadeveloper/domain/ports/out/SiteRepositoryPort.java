package io.cyberhack.wellgroundedjavadeveloper.domain.ports.out;

import io.cyberhack.wellgroundedjavadeveloper.domain.model.SiteContent;

// 2. PUERTO DE SALIDA (Output Port)
// Es un contrato (Interface). El Dominio dice:
// "Necesito que alguien me traiga el contenido de una URL. No me importa cómo."
public interface SiteRepositoryPort {
    SiteContent fetchContentByUrl(String url);
}
