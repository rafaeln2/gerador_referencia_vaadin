package com.application;

import com.application.controllers.CrossRefService;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "gerador-referencia-vaadin")
public class Application implements AppShellConfigurator, CommandLineRunner {

	private static final long serialVersionUID = 2838069679349980311L;
	
	@Autowired
	CrossRefService crossRefService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	@Profile("!test")
//	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//		//https://api.crossref.org/works/10.1037/0003-066X.59.1.29/
//		return args -> {
//			String str = restTemplate.getForObject("https://api.crossref.org/works/10.1037/0003-066X.59.1.29/", String.class);
//			JSONObject myCustomer = new JSONObject(str);
//			String name = myCustomer.getString("name");
//			JSONObject address = myCustomer.getJSONObject("address"); // if address is a composite object with city, street, etc...
//			
//			var teste = restTemplate.get(
//					"http://localhost:8080/api/random", Quote.class);
//			log.info(quote.toString());
//		};
//	}

	@Override
	public void run(String... args) throws Exception {
//		RestTemplate restTemplate = new RestTemplate();
//		String str = restTemplate.getForObject("https://api.crossref.org/works/10.1037/0003-066X.59.1.29/", String.class);
//		JSONObject myCustomer = new JSONObject(str);
//		String name = myCustomer.getString("name");
//		JSONObject address = myCustomer.getJSONObject("address"); // if address is a composite object with city, street, etc...
		
		var doi = "10.3233/ISU-2002-222-309";
		var referencia = crossRefService.buscaDOI(doi);
		System.out.println(referencia.getMessage().getTitle());
		System.out.println(referencia.getMessage().getPublished());
		System.out.println(referencia.getMessage().getPublished().getDateParts());
		System.out.println(referencia.getMessage().getVolume());
		referencia.getMessage().getAuthor().forEach(e -> System.out.println(String.format("%s %s", e.getGiven(), e.getFamily())));
		System.out.println(referencia.getMessage().getContainerTitle());
	}
}
