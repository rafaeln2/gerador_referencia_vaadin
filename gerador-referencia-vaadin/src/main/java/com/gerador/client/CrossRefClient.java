package com.gerador.client;

import com.gerador.models.dtos.WorkDTO;
import com.gerador.models.dtos.agency.ResponseAgencyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="crossref-api", url = "https://api.crossref.org/")
public interface CrossRefClient {

    @GetMapping(value = "/works/{doi}")
    ResponseEntity<WorkDTO> works(@PathVariable String doi);

    @GetMapping(value = "/works/{doi}/agency/")
    ResponseEntity<ResponseAgencyDTO> agency(@PathVariable String doi);
}
