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
    void validaRequisicaoViaCep() {
        final var doi = "10.1596/978-0-8213-7536-5";
        try {
            final var referencia = service.prepararReferencia(doi);
            System.out.println(referencia.toString());
            Assertions.assertTrue(StringUtils.isNotBlank(referencia));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
