package com.gerador.models;

import com.gerador.models.dtos.WorkDTO;

public class Referencia {
    private WorkDTO workDTO;

    public Referencia(WorkDTO workDTO) {
        this.workDTO = workDTO;
    }

    public String produzirReferencia(TipoStrategy tipoStrategy){
        return tipoStrategy.gerarReferencia(workDTO);
    };
}
