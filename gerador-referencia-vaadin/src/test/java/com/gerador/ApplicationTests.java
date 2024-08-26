package com.gerador;

import com.gerador.services.ReferenciaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ApplicationTests {
    @Autowired
    ReferenciaService service;

    @Test
    void validaReferenciaArtigoPublicacaoPeriodica() {
        final var doi = "10.1590/S1519-70772014000100002";
        //Teste realizado com o DOI 10.1590/S1519-70772014000100002, pego direto do documento da ABNT 6023/2018
        //na seção 7.7.6 Artigo, seção e/ou matéria de publicação periódica em meio eletrônico

        //10.1590/S1519-70772014000100002
        //10.1596/978-0-8213-7536-5
        try {
            final var referencia = service.prepararReferencia(doi);
            System.out.println(referencia.toString());
            Assertions.assertTrue(StringUtils.isNotBlank(referencia));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
