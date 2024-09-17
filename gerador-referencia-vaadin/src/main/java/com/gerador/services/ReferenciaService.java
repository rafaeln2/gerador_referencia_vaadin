package com.gerador.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.gerador.client.CrossRefClient;
import com.gerador.models.GeradorReferenciaResolver;
import com.gerador.models.Referencia;
import com.gerador.models.dtos.enums.TipoDocumento;
import org.springframework.stereotype.Service;

import com.gerador.models.dtos.WorkDTO;
import com.gerador.models.dtos.agency.ResponseAgencyDTO;
import com.gerador.models.dtos.type.ResponseTypeDTO;
import com.nimbusds.jose.shaded.gson.Gson;

import static java.util.Objects.isNull;

@Service
public class ReferenciaService {
    //https://api.crossref.org/works/10.1037/0003-066X.59.1.29/ medicine
    //https://api.crossref.org/works/10.3233/ISU-2002-222-309
    static int codigoSucesso = 200;
    private final GeradorReferenciaResolver geradorReferencia;
    private final CrossRefClient crossRefClient;

    public ReferenciaService(GeradorReferenciaResolver geradorReferencia, CrossRefClient crossRefClient) {
        this.geradorReferencia = geradorReferencia;
        this.crossRefClient = crossRefClient;
    }

    public String prepararReferencia(final String doi) throws Exception {
        if (!validaDOICrossref(doi)) {
            throw new IllegalArgumentException("DOI não registrado na agência CrossRef.");
        }

        try {
            var response = crossRefClient.works(doi);
            if (response.getStatusCode().value() != codigoSucesso)
                throw new RuntimeException("HTTP error code : " + response.getStatusCode());

            WorkDTO work = response.getBody();

            if (isNull(work) || isNull(work.getWorkMessage())) { // Tratar
                throw new RuntimeException("Work not found");
            }

            return geradorReferencia
                    .resolve(TipoDocumento.fromValue(work.getWorkMessage().getType()))
                    .gerarReferencia(work);
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }

    private Boolean validaDOICrossref(final String doi) throws Exception {
        if (doi.isEmpty() || doi.isBlank()) {
            throw new IllegalArgumentException("Insira um DOI");
        }

        var response = crossRefClient.agency(doi);
        if (response.getStatusCode().value() != codigoSucesso) {
            throw new RuntimeException("Não foi possível obter resposta do site da CrossRef");
        }

        ResponseAgencyDTO agency = response.getBody();

        if (isNull(agency) || isNull(agency.getMessage())){
            throw new RuntimeException(); // Tratar
        }

        return agency.getMessage().getAgency().getId().equalsIgnoreCase("crossref");
    }

}
