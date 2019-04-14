package com.gmail.erofeev.st.alexei.secondweek.controller.app;

import com.gmail.erofeev.st.alexei.secondweek.controller.DocumentController;
import com.gmail.erofeev.st.alexei.secondweek.controller.config.AppConfig;
import com.gmail.erofeev.st.alexei.secondweek.controller.impl.DocumentControllerImpl;
import com.gmail.erofeev.st.alexei.secondweek.service.model.DocumentDTO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        DocumentController documentController = ctx.getBean(DocumentControllerImpl.class);
        DocumentDTO documentDTO = new DocumentDTO("0123456789");
        documentController.getDocumentById(1L);
//        documentController.add(documentDTO);
//        documentController.delete(1L);
        //System.out.println(documentController.getDocumentById(99L));
    }
}
