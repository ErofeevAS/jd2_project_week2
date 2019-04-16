package com.gmail.erofeev.st.alexei.secondweek.controller;

import com.gmail.erofeev.st.alexei.secondweek.controller.impl.DocumentControllerImpl;
import com.gmail.erofeev.st.alexei.secondweek.controller.validataor.impl.DocumentDTOValidatorImpl;
import com.gmail.erofeev.st.alexei.secondweek.service.DocumentService;
import com.gmail.erofeev.st.alexei.secondweek.service.model.DocumentDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DocumentControllerTest {
    private DocumentController documentController;
    @Mock
    private DocumentService documentService;
    @Mock
    private DocumentDTOValidatorImpl documentDTOValidator;

    @Before
    public void init() {
        documentController = new DocumentControllerImpl(documentService, documentDTOValidator);
    }

    @Test
    public void shouldAddDocumentDTO() {
        DocumentDTO documentDTO = new DocumentDTO("012345678901234567891gxfgdgdfg");
        DocumentDTO addedDocumentDTO = new DocumentDTO("012345678901234567891gxfgdgdfg");
        addedDocumentDTO.setId(1L);
        when(documentController.add(documentDTO)).thenReturn(addedDocumentDTO);
        addedDocumentDTO = documentController.add(documentDTO);
        Assert.assertEquals(1L, addedDocumentDTO.getId().longValue());
    }

    @Test
    public void shouldGetDocumentDTOById() {
        DocumentDTO returnedDocument = new DocumentDTO("012345678901234567891gxfgdgdfg");
        returnedDocument.setId(1L);
        when(documentController.getDocumentById(1L)).thenReturn(returnedDocument);
        returnedDocument = documentController.getDocumentById(1L);
        Assert.assertEquals(1L, returnedDocument.getId().longValue());
    }

    @Test
    public void shouldRemoveDocumentById() {
        documentController.delete(1L);
    }
}

