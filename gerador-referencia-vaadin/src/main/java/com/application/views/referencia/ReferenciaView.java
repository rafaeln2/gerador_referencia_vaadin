package com.application.views.referencia;

import org.springframework.beans.factory.annotation.Autowired;

import com.application.controllers.ReferenciaService;
import com.application.views.MainLayout;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.component.textfield.TextArea;
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
    	tfResultado.getElement().setProperty("innerHTML", "<p>Hello World</p></br><span>Some text after a break</span>");
//    	tfResultado = new Html("<body><h1></h1></body>");
    	
    	tfResultado.setVisible(true);
    	
        tfDoi = new TextField("DOI");
        tfDoi.setTooltipText("10.xxxxxx/exemplo");
        tfDoi.setWidth("15em");
        
        btnGerarReferencia = new Button("Gerar referência");
        btnGerarReferencia.addClickListener(e -> {
//            Notification.show("Hello " + doi.getValue());
        	try {
				tfResultado.getElement().setProperty("innerHTML", service.prepararReferencia(tfDoi.getValue())); //(service.prepararReferencia(tfDoi.getValue()));
				tfResultado.setVisible(true);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
        });
        btnGerarReferencia.addClickShortcut(Key.ENTER);

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, tfDoi, btnGerarReferencia, tfResultado);

        add(tfDoi, btnGerarReferencia, tfResultado);
    }

}
