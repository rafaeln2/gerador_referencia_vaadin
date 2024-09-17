package com.gerador.models;

import com.gerador.models.dtos.enums.TipoDocumento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.nonNull;

@Component
public class GeradorReferenciaResolver {
    private final Map<TipoDocumento, LayoutReferenciaStrategy> layouts = new ConcurrentHashMap<>();
    private final LayoutReferenciaStrategy LAYOUT_DEFAULT = new LayoutReferenciaDefault();

    public GeradorReferenciaResolver(List<LayoutReferenciaStrategy> layouts) {
        layouts.stream().filter(layout -> nonNull(layout.documentoCompativel()))
                .forEach(layout -> this.layouts.put(layout.documentoCompativel(), layout));
    }

    public LayoutReferenciaStrategy resolve(TipoDocumento tipoDocumento) {
        return layouts.getOrDefault(tipoDocumento, LAYOUT_DEFAULT);
    }
}
