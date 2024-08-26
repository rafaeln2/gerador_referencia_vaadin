package com.gerador.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.gerador.models.Referencia;
import com.gerador.models.TipoStrategy;
import com.gerador.models.dtos.enums.TipoDocumento;
import org.springframework.stereotype.Service;

import com.gerador.models.dtos.WorkDTO;
import com.gerador.models.dtos.agency.ResponseAgencyDTO;
import com.gerador.models.dtos.type.ResponseTypeDTO;
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

            WorkDTO work = gson.fromJson(jsonEmString, WorkDTO.class);

//            referencia = gerarReferencia(work);
            var referencia = new Referencia(work);

            return referencia.produzirReferencia(TipoDocumento.ARTIGO_CIENTIFICO.strategy);
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }

//    private String produzirReferencia(TipoStrategy tipoDocumento) {
//        return tipoDocumento.gerarReferencia(workDTO);
//    }

    private String gerarReferencia(WorkDTO work) {
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


    // bate na crossref e valida se ele é registrado na base deles.
    private Boolean validaDOICrossref(final String doi) throws Exception {
        String urlParaChamada = webService + webServiceWork + doi + webServiceWorkAgency;
        if (doi.isEmpty() || doi.isBlank()) {
            throw new IllegalArgumentException("Insira um DOI");
        }

        URL url = new URL(urlParaChamada);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        if (conexao.getResponseCode() != codigoSucesso) {
//                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());
            throw new RuntimeException("Não foi possível obter resposta do site da CrossRef");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conexao.getInputStream())));

        String resposta, jsonEmString = "";
        while ((resposta = bufferedReader.readLine()) != null) {
            jsonEmString += resposta;
        }
        Gson gson = new Gson();
        ResponseAgencyDTO agency = gson.fromJson(jsonEmString, ResponseAgencyDTO.class);

        return agency.getMessage().getAgency().getId().equalsIgnoreCase("crossref");
    }

    //tem que anotar as classes com jsonignoreproperties, funcionando atualmente com o gson.fromJson
//	private Boolean validaDOICrossrefTeste(final String doi) throws Exception {
//		String urlParaChamada = webService + webServiceWork + doi + webServiceWorkAgency;
//		if (doi.isEmpty() || doi.isBlank()){
//			throw new IllegalArgumentException("Insira um DOI");
//		}
//
//		final HttpClient client = HttpClient.newHttpClient();
//		final HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create(urlParaChamada)).build();
//		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//		final ObjectMapper mapper = new ObjectMapper();
//		if (response.body().contains("erro")) {
//			throw new IllegalArgumentException("CEP não encontrado!");
//		}
//		final var dto = mapper.readValue(response.body(), ResponseAgencyDTO.class);
//		return dto.getMessage().getAgency().getId().equalsIgnoreCase("crossref");
//	}

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
