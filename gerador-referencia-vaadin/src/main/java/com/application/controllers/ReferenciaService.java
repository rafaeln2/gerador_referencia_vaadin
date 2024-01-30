package com.application.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.application.dto.ResponseWorkDTO;
import com.application.dto.agency.ResponseAgencyDTO;
import com.application.dto.type.ResponseTypeDTO;
import com.nimbusds.jose.shaded.gson.Gson;

@Service
public class ReferenciaService {
	//https://api.crossref.org/works/10.1037/0003-066X.59.1.29/ medicine
	//https://api.crossref.org/works/10.3233/ISU-2002-222-309
    static String webService = "https://api.crossref.org/";
    static String webServiceWork = "works/";
    static String webServiceWorkAgency = "/agency/";
    static String webServiceType = "types/";
    static int codigoSucesso = 200;

    public String prepararReferencia(final String doi) throws Exception {
        String urlParaChamada = webService + webServiceWork + doi;
        String referencia;
        if (!validaDOICrossref(doi)) {
			throw new IllegalArgumentException("DOI não registrado na agência CrossRef.");
		}
        
        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != codigoSucesso)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            
            String resposta, jsonEmString = "";
            while ((resposta = bufferedReader.readLine()) != null) {
				jsonEmString += resposta;
			}
            Gson gson = new Gson();
            
            ResponseWorkDTO work = gson.fromJson(jsonEmString, ResponseWorkDTO.class);
            
            referencia = gerarReferencia(work);
            
            return referencia;
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }

	private String gerarReferencia(ResponseWorkDTO work) {
		StringBuilder referencia = new StringBuilder();
		referencia.append("<span>");
		switch (work.getWorkMessage().getType()) {
		case "book": {
			work.getWorkMessage().getAuthor().forEach(e -> {
				referencia.append(String.format("%s, %s", e.getFamily().toUpperCase(), e.getGiven()));
			}); //fix multiple author names
			referencia.append(".");
			
			if (!work.getWorkMessage().getSubtitle().get(0).isEmpty()) {
				referencia.append(String.format("<b> %s: </b> %s.", work.getWorkMessage().getTitle().get(0), work.getWorkMessage().getSubtitle().get(0)));
			} else {
				referencia.append(String.format("<b> %s. </b>", work.getWorkMessage().getTitle().get(0)));
			}
//			referencia.append(String.format("<b> %s. </b>", work.getWorkMessage().getTitle().get(0)));
//			referencia.append(String.format("", work.getWorkMessage().getSubtitle().get(0)));
			
			referencia.append(" [s.l]: ");
			
			referencia.append(String.format("%s, ", work.getWorkMessage().getPublisher()));
			
			referencia.append(String.format(" %s ", work.getWorkMessage().getPublished().getDateParts().get(0).get(0)));
			
			referencia.append(String.format("DOI: %s. ", work.getWorkMessage().getDoi()));
			
			referencia.append(String.format("Disponível em: %s.", work.getWorkMessage().getResource().getPrimary().getUrl()));
			
			referencia.append("</span>");
			return referencia.toString();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + work.getWorkMessage().getType());
		}
	}
	

//

	private Boolean validaDOICrossref(final String doi) throws Exception {
		String urlParaChamada = webService + webServiceWork + doi + webServiceWorkAgency;
        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != codigoSucesso)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            
            String resposta, jsonEmString = "";
            while ((resposta = bufferedReader.readLine()) != null) {
				jsonEmString += resposta;
			}
            Gson gson = new Gson();
            ResponseAgencyDTO Agency = gson.fromJson(jsonEmString, ResponseAgencyDTO.class);
            
            return Agency.getMessage().getAgency().getId().equalsIgnoreCase("crossref");
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }		
	}
	
	private String buscaTypes(final String doi) throws Exception {
		String urlParaChamada = webService + webServiceWork + doi + webServiceType;
        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != codigoSucesso)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            
            String resposta, jsonEmString = "";
            while ((resposta = bufferedReader.readLine()) != null) {
				jsonEmString += resposta;
			}
            Gson gson = new Gson();
            ResponseTypeDTO type = gson.fromJson(jsonEmString, ResponseTypeDTO.class);
            
            return type.getMessage().getId();
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }	
	}
}
