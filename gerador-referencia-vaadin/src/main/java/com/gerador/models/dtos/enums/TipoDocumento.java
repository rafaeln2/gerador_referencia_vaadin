package com.gerador.models.dtos.enums;

import com.gerador.models.TipoArtigoCientifico;
import com.gerador.models.TipoStrategy;

public enum TipoDocumento {
    ARTIGO_CIENTIFICO("Artigo Cientifico", "", new TipoArtigoCientifico());

    public final String descricao;
    public final String responseTypeDescription;
    public final TipoStrategy strategy;

    TipoDocumento(String descricao, String responseTypeDescription, TipoStrategy strategy) {
        this.descricao = descricao;
        this.responseTypeDescription = responseTypeDescription;
        this.strategy = strategy;
    }
}
