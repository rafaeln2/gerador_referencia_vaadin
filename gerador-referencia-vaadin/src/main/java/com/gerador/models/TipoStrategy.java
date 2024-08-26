package com.gerador.models;

import com.gerador.models.dtos.WorkDTO;

public interface TipoStrategy {
    String gerarReferencia(WorkDTO workDTO);
}
