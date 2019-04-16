package com.gmail.erofeev.st.alexei.secondweek.controller;

import com.gmail.erofeev.st.alexei.secondweek.controller.config.AppConfig;
import com.gmail.erofeev.st.alexei.secondweek.controller.exception.ControllerException;
import com.gmail.erofeev.st.alexei.secondweek.service.model.DocumentDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {AppConfig.class},
        loader = AnnotationConfigContextLoader.class)
public class DocumentControllerIntegrationTest {
    private static final String DESCRIPTION = "description";

    @Autowired
    private DocumentController documentController;

    @Test
    public void shouldAddDocumentDTO() {
        DocumentDTO documentDTO = new DocumentDTO(DESCRIPTION);
        DocumentDTO documentDTO2  = documentController.add(documentDTO);
        Assert.assertNotNull(documentDTO2.getId());
        Assert.assertNotNull(documentDTO2.getUniqueNumber());
        Assert.assertEquals(DESCRIPTION, documentDTO2.getDescription());
    }

    @Test
    public void shouldGetDocumentDTOById() {
        DocumentDTO documentDTO = documentController.getDocumentById(15L);
        Assert.assertNotNull(documentDTO.getId());
        Assert.assertNotNull(documentDTO.getUniqueNumber());
        long id = documentDTO.getId();
        Assert.assertEquals(15L, id);
    }


    @Test(expected = ControllerException.class)
    public void shouldNotFoundDocumentDTOById() {
        DocumentDTO documentDTO = documentController.getDocumentById(Long.MAX_VALUE);
    }
    @Test(expected = ControllerException.class)
    public void shouldNotFoundDocumentDTOWhenDelete() {
        documentController.delete(Long.MAX_VALUE);
    }

    @Test(expected = ControllerException.class)
    public void shouldDeleteDocumentDTO() {
        documentController.delete(1L);
        documentController.delete(1L);
    }
}

