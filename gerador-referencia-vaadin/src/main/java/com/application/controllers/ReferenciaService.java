package com.application.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
	    case "book-section":
	        System.out.println("Book Section");
	        break;
	    case "monograph":
	        System.out.println("Monograph");
	        break;
	    case "report-component":
	        System.out.println("Report Component");
	        break;
	    case "report":
	        System.out.println("Report");
	        break;
	    case "peer-review":
	        System.out.println("Peer Review");
	        break;
	    case "book-track":
	        System.out.println("Book Track");
	        break;
	    case "journal-article":
	        System.out.println("Journal Article");
	        break;
	    case "book-part":
	        System.out.println("Part");
	        break;
	    case "other":
	        System.out.println("Other");
	        break;
	    case "book":			
	    	if (work.getWorkMessage().getAuthor().size() == 1) {
			referencia.append(String.format("%s, %s.", work.getWorkMessage().getAuthor().get(0).getFamily().toUpperCase(), work.getWorkMessage().getAuthor().get(0).getGiven()));
		} else if (work.getWorkMessage().getAuthor().size() < 2) {
			for (int x = 0; x < work.getWorkMessage().getAuthor().size(); x++) {
				referencia.append(String.format("%s, %s.;", work.getWorkMessage().getAuthor().get(x).getFamily().toUpperCase(), work.getWorkMessage().getAuthor().get(x).getGiven()));
			}
			referencia.append(String.format("%s, %s.", work.getWorkMessage().getAuthor().get(2).getFamily().toUpperCase(), work.getWorkMessage().getAuthor().get(2).getGiven()));
		} else { // et al
//			var temp = StringUtils.split(work.getWorkMessage().getAuthor().get(0).getGiven(), " ");
//			temp.forEach(e -> e = e.substring(0, 1));
			referencia.append(String.format("%s, %s. et al.", 
					work.getWorkMessage().getAuthor().get(0).getFamily().toUpperCase(),
					String.join("" , Arrays.asList(work.getWorkMessage().getAuthor().get(0).getGiven().split("\\s+")).stream().map(e -> e = e.substring(0)).toList().toString())
			));
		}
		
		
		if (work.getWorkMessage().getSubtitle().size() > 0) {
			referencia.append(String.format("<b> %s: </b> %s.", work.getWorkMessage().getTitle().get(0), work.getWorkMessage().getSubtitle().get(0)));
		} else {
			referencia.append(String.format("<b> %s. </b>", work.getWorkMessage().getTitle().get(0)));
		}
//		referencia.append(String.format("<b> %s. </b>", work.getWorkMessage().getTitle().get(0)));
//		referencia.append(String.format("", work.getWorkMessage().getSubtitle().get(0)));
		
		referencia.append(" [s.l]: ");
		
		referencia.append(String.format("%s, ", work.getWorkMessage().getPublisher()));
		
		referencia.append(String.format(" %s. ", work.getWorkMessage().getPublished().getDateParts().get(0).get(0)));
		
		referencia.append(String.format("DOI: %s. ", work.getWorkMessage().getDoi()));
		
		if (!work.getWorkMessage().getResource().getPrimary().getUrl().isEmpty()) {
			referencia.append(String.format("Disponível em: %s. ", work.getWorkMessage().getResource().getPrimary().getUrl()));
			
			LocalDate dataAtual = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
			referencia.append(String.format("Acesso em: %s.", dataAtual.format(formatter)));
		}
		
		referencia.append("</span>");
	        break;
	    case "journal-volume":
	        System.out.println("Journal Volume");
	        break;
	    case "book-set":
	        System.out.println("Book Set");
	        break;
	    case "reference-entry":
	        System.out.println("Reference Entry");
	        break;
	    case "proceedings-article":
	        System.out.println("Proceedings Article");
	        break;
	    case "journal":
	        System.out.println("Journal");
	        break;
	    case "component":
	        System.out.println("Component");
	        break;
	    case "book-chapter":
	        System.out.println("Book Chapter");
	        break;
	    case "proceedings-series":
	        System.out.println("Proceedings Series");
	        break;
	    case "report-series":
	        System.out.println("Report Series");
	        break;
	    case "proceedings":
	        System.out.println("Proceedings");
	        break;
	    case "database":
	        System.out.println("Database");
	        break;
	    case "standard":
	        System.out.println("Standard");
	        break;
	    case "reference-book":
	        System.out.println("Reference Book");
	        break;
	    case "posted-content":
	        System.out.println("Posted Content");
	        break;
	    case "journal-issue":
	        System.out.println("Journal Issue");
	        break;
	    case "dissertation":
	        System.out.println("Dissertation");
	        break;
	    case "grant":
	        System.out.println("Grant");
	        break;
	    case "dataset":
	        System.out.println("Dataset");
	        break;
	    case "book-series":
	        System.out.println("Book Series");
	        break;
	    case "edited-book":
	        System.out.println("Edited Book");
	        break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + work.getWorkMessage().getType());
		}
		return referencia.toString();
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
