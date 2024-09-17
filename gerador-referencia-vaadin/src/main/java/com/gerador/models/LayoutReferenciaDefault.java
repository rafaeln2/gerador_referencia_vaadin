package com.gerador.models;

import com.gerador.models.dtos.WorkDTO;
import com.gerador.models.dtos.enums.TipoDocumento;

public class LayoutReferenciaDefault implements LayoutReferenciaStrategy{
    @Override
    public String gerarReferencia(WorkDTO workDTO) {
        return "";
    }

    @Override
    public TipoDocumento documentoCompativel() {
        return null;
    }
}
