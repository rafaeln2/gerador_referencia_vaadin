package com.gerador.models;

import com.gerador.models.dtos.WorkDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TipoArtigoCientifico implements TipoStrategy {

    @Override
    public String gerarReferencia(WorkDTO work) {
        StringBuilder referencia = new StringBuilder();
        referencia.append("<span>");

        //Autores
        if (work.getWorkMessage().getAuthor().size() == 1) {
            referencia.append(String.format("%s, %s.", work.getWorkMessage().getAuthor().get(0).getFamily().toUpperCase(), work.getWorkMessage().getAuthor().get(0).getGiven()));
        } else if (work.getWorkMessage().getAuthor().size() < 2) {
            for (int x = 0; x < work.getWorkMessage().getAuthor().size(); x++) {
                referencia.append(String.format("%s, %s.;", work.getWorkMessage().getAuthor().get(x).getFamily().toUpperCase(), work.getWorkMessage().getAuthor().get(x).getGiven()));
            }
            referencia.append(String.format("%s, %s.", work.getWorkMessage().getAuthor().get(2).getFamily().toUpperCase(), work.getWorkMessage().getAuthor().get(2).getGiven()));
        } else { // et al
            referencia.append(String.format("%s, %s <i>et al.</i>",
                    work.getWorkMessage().getAuthor().get(0).getFamily().toUpperCase(),
                    String.join("", Arrays.asList(work.getWorkMessage().getAuthor().get(0).getGiven()))
            ));
        }

        //Titulo e/ou subtitulo
        if (!work.getWorkMessage().getSubtitle().isEmpty()) {
            referencia.append(String.format(" %s: %s.", work.getWorkMessage().getTitle().get(0), work.getWorkMessage().getSubtitle().get(0)));
        } else {
            referencia.append(String.format(" %s.", work.getWorkMessage().getTitle().get(0)));
        }
        referencia.append("<b> %s</b>".formatted(work.getWorkMessage().getContainerTitle().toString().replaceAll("[\\[\\]]", "")));

        referencia.append(", [s.l] "); //não tem o local de publicacao na resposta da API

        //volume, edição e pagina
        if (!work.getWorkMessage().getVolume().isEmpty())
            referencia.append(", v. %s".formatted(work.getWorkMessage().getVolume()));
        if (!work.getWorkMessage().getJournalIssue().getIssue().isEmpty())
            referencia.append(", n. %s".formatted(work.getWorkMessage().getJournalIssue().getIssue()));
        referencia.append(", p. %s-%s".formatted("0", "999")); //TODO: pegar as paginas pela interface

        //data por extenso da publicação. ex: abr. 2014
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, work.getWorkMessage().getPublished().getDateParts().get(0).get(1) - 1);
        referencia.append(", %s %s. ".formatted(new SimpleDateFormat("MMM").format(cal.getTime()), work.getWorkMessage().getPublished().getDateParts().get(0).get(0)));

        referencia.append(String.format("DOI: %s. ", work.getWorkMessage().getDoi()));

        if (!work.getWorkMessage().getResource().getPrimary().getUrl().isEmpty()) {
            referencia.append(String.format("Disponível em: %s. ", work.getWorkMessage().getResource().getPrimary().getUrl()));

            LocalDate dataAtual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
            referencia.append(String.format("Acesso em: %s.", dataAtual.format(formatter)));
        }

        referencia.append("</span>");
        return referencia.toString();
    }
}