package com.gerador.views.referencia;

import com.vaadin.flow.component.notification.NotificationVariant;
import org.springframework.beans.factory.annotation.Autowired;

import com.gerador.services.ReferenciaService;
import com.gerador.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Referência")
@Route(value = "referencia", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class ReferenciaView extends HorizontalLayout {

    private static final long serialVersionUID = 673385547414433022L;
    
	private TextField tfDoi;
    private Button btnGerarReferencia;
    private Div tfResultado = new Div();
    
    @Autowired
    private ReferenciaService service;
    
    public ReferenciaView() {
    	tfResultado.getElement().setProperty("innerHTML", "<body/>");

    	tfResultado.setVisible(true);
    	
        tfDoi = new TextField("DOI");
        tfDoi.setTooltipText("10.xxxxxx/exemplo");
        tfDoi.setWidth("15em");

        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setDuration(5000);
        
        btnGerarReferencia = new Button("Gerar referência");
        btnGerarReferencia.addClickListener(e -> {
        	try {
				tfResultado.getElement().setProperty("innerHTML", service.prepararReferencia(tfDoi.getValue()));
				tfResultado.setVisible(true);
			} catch (Exception ex) {
                tfDoi.setErrorMessage(ex.getMessage());
                notification.setText(ex.getMessage());
                notification.open();
				ex.printStackTrace();
			}
        });
        btnGerarReferencia.addClickShortcut(Key.ENTER);

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, tfDoi, btnGerarReferencia, tfResultado);

        add(tfDoi, btnGerarReferencia, tfResultado);
    }

}
