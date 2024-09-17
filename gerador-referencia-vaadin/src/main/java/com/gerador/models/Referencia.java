package com.gerador.models;

import com.gerador.models.dtos.WorkDTO;

public class Referencia {
    private final WorkDTO workDTO;

    public Referencia(WorkDTO workDTO) {
        this.workDTO = workDTO;
    }

    public String produzirReferencia(LayoutReferenciaStrategy layoutReferenciaStrategy){
        return layoutReferenciaStrategy.gerarReferencia(workDTO);
    };
}
