package com.gerador.models;

import com.gerador.models.dtos.WorkDTO;
import com.gerador.models.dtos.enums.TipoDocumento;

public interface LayoutReferenciaStrategy {
    String gerarReferencia(WorkDTO workDTO);

    TipoDocumento documentoCompativel();
}
