package com.gerador.models;

import com.gerador.models.dtos.WorkDTO;
import com.gerador.models.dtos.enums.TipoDocumento;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import org.joda.time.format.*;

@Component
public class LayoutReferenciaArtigoCientifico implements LayoutReferenciaStrategy {

    @Override
    public String gerarReferencia(WorkDTO work) {
        StringBuilder referencia = new StringBuilder();
        referencia.append("<span>");

        if(!work.getWorkMessage().getType().equalsIgnoreCase("journal-article")){
            throw new IllegalArgumentException("Tipo inválido");
        }

        //Autores
        switch (work.getWorkMessage().getAuthor().size()) {
            case 1:
                referencia.append(String.format("%s, %s.",
                        work.getWorkMessage().getAuthor().get(0).getFamily().toUpperCase(),
                        Arrays.stream(work.getWorkMessage().getAuthor().get(0).getGiven().split(" "))
                                .map(s -> s.substring(0, 1))
                                .map(s -> s + ". ")
                                .collect(Collectors.joining())).replaceFirst(".$", ""));
                break;

            case 2:
                referencia.append(String.format("%s, %s",
                        work.getWorkMessage().getAuthor().get(0).getFamily().toUpperCase(),
                        Arrays.stream(work.getWorkMessage().getAuthor().get(0).getGiven().split(" "))
                                .map(s -> s.substring(0, 1))
                                .map(s -> s + ". ")
                                .collect(Collectors.joining())).replaceFirst(".$", "").concat("; "));
                referencia.append(String.format("%s, %s",
                        work.getWorkMessage().getAuthor().get(1).getFamily().toUpperCase(),
                        Arrays.stream(work.getWorkMessage().getAuthor().get(1).getGiven().split(" "))
                                .map(s -> s.substring(0, 1))
                                .map(s -> s + ". ")
                                .collect(Collectors.joining())).replaceFirst(".$", ""));
                break;

            case 3:
                referencia.append(String.format("%s, %s",
                        work.getWorkMessage().getAuthor().get(0).getFamily().toUpperCase(),
                        Arrays.stream(work.getWorkMessage().getAuthor().get(0).getGiven().split(" "))
                                .map(s -> s.substring(0, 1))
                                .map(s -> s + ". ")
                                .collect(Collectors.joining())).replaceFirst(".$", "").concat("; "));
                referencia.append(String.format("%s, %s",
                        work.getWorkMessage().getAuthor().get(1).getFamily().toUpperCase(),
                        Arrays.stream(work.getWorkMessage().getAuthor().get(1).getGiven().split(" "))
                                .map(s -> s.substring(0, 1))
                                .map(s -> s + ". ")
                                .collect(Collectors.joining())).replaceFirst(".$", "").concat("; "));
                referencia.append(String.format("%s, %s",
                        work.getWorkMessage().getAuthor().get(2).getFamily().toUpperCase(),
                        Arrays.stream(work.getWorkMessage().getAuthor().get(2).getGiven().split(" "))
                                .map(s -> s.substring(0, 1))
                                .map(s -> s + ". ")
                                .collect(Collectors.joining())).replaceFirst(".$", ""));
                break;

            default:
                referencia.append(String.format("%s, %s. <i>et al.</i>",
                        work.getWorkMessage().getAuthor().get(0).getFamily().toUpperCase(),
                        Arrays.stream(work.getWorkMessage().getAuthor().get(0).getGiven().split(" "))
                                .map(s -> s.substring(0, 1))
                                .collect(Collectors.joining())));
                break;
        }

        //Titulo e/ou subtitulo
        if (!work.getWorkMessage().getSubtitle().isEmpty()) {
            referencia.append(String.format(" %s: %s.", work.getWorkMessage().getTitle().get(0), work.getWorkMessage().getSubtitle().get(0)));
        } else {
            referencia.append(String.format(" %s.", work.getWorkMessage().getTitle().get(0)));
        }
        referencia.append("<b> %s</b>".formatted(work.getWorkMessage().getContainerTitle().toString().replaceAll("[\\[\\]]", "")));

        referencia.append(", [<i>s. l.</i>]"); //não tem o local de publicacao na resposta da API

        //volume, edição e pagina
        if (!Strings.isEmpty(work.getWorkMessage().getVolume()))
            referencia.append(", v. %s".formatted(work.getWorkMessage().getVolume()));
        if (work.getWorkMessage().getJournalIssue() != null && !Strings.isEmpty(work.getWorkMessage().getJournalIssue().getIssue()))
            referencia.append(", n. %s".formatted(work.getWorkMessage().getJournalIssue().getIssue()));
        if (!Strings.isEmpty(work.getWorkMessage().getPage()))
            referencia.append(", p. %s".formatted(work.getWorkMessage().getPage()));

        //data por extenso da publicação. ex: abr. 2014
        if (!work.getWorkMessage().getPublished().getDateParts().isEmpty()) {
            if (work.getWorkMessage().getPublished().getDateParts().get(0).size() == 1){
                referencia.append(", %s".formatted(work.getWorkMessage().getPublished().getDateParts().get(0).get(0)));
            } else if (work.getWorkMessage().getPublished().getDateParts().get(0).size() == 2) {
                org.joda.time.LocalDate date = new org.joda.time.LocalDate(work.getWorkMessage().getPublished().getDateParts().get(0).get(0), work.getWorkMessage().getPublished().getDateParts().get(0).get(1), 1);
                org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("MMM yyyy");
                referencia.append(", %s".formatted(formatter.print(date)));
            } else {
                org.joda.time.LocalDate date = new org.joda.time.LocalDate(work.getWorkMessage().getPublished().getDateParts().get(0).get(0), work.getWorkMessage().getPublished().getDateParts().get(0).get(1), work.getWorkMessage().getPublished().getDateParts().get(0).get(2));
                org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("dd MMM yyyy");
                referencia.append(", %s".formatted(formatter.print(date)));
            }
        } else {
            referencia.append(", [<i>s. d.</i>]");
        }

        referencia.append(String.format(". DOI: %s. ", work.getWorkMessage().getDoi()));

        if (!work.getWorkMessage().getResource().getPrimary().getUrl().isEmpty()) {
            referencia.append(String.format("Disponível em: %s. ", work.getWorkMessage().getResource().getPrimary().getUrl()));

            LocalDate dataAtual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
            referencia.append(String.format("Acesso em: %s.", dataAtual.format(formatter)));
        }

        referencia.append("</span>");
        return referencia.toString();
    }

    @Override
    public TipoDocumento documentoCompativel() {
        return TipoDocumento.JOURNAL_ARTICLE;
    }
}