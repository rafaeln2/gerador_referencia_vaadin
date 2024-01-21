package com.application.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.application.model.Work;
import com.nimbusds.jose.shaded.gson.Gson;

@Service
public class CrossRefService {
	//https://api.crossref.org/works/10.1037/0003-066X.59.1.29/ medicine
    static String webService = "https://api.crossref.org/works/"; //https://api.crossref.org/works/10.3233/ISU-2002-222-309
    static int codigoSucesso = 200;

    public Work buscaDOI(final String doi) throws Exception {
        String urlParaChamada = webService + doi;

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
//
//            Gson gson = new Gson();
//            Referencia referencia = gson.fromJson(jsonEmString, Referencia.class);
            
            Gson gson = new Gson();
            Work work = gson.fromJson(jsonEmString, Work.class);
            
            return work;
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }
}
