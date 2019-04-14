package com.gmail.erofeev.st.alexei.secondweek.controller;

import com.gmail.erofeev.st.alexei.secondweek.controller.validataor.DocumentDTOValidator;
import com.gmail.erofeev.st.alexei.secondweek.service.model.DocumentDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class DocumentDTOValidatorTest {
    private DocumentDTOValidator documentDTOValidator;

    @Before
    public void init() {
        documentDTOValidator = new DocumentDTOValidator();
    }

    @Test
    public void shouldReturnMapErrors() {
        DocumentDTO documentDTO = new DocumentDTO("012345678901234567891");
        Map<String, String> errors = documentDTOValidator.validate(documentDTO);
        Assert.assertFalse(errors.isEmpty());
    }

    @Test
    public void shouldReturnEmptyMapErrors() {
        DocumentDTO documentDTO = new DocumentDTO("1");
        Map<String, String> errors = documentDTOValidator.validate(documentDTO);
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnNotNull() {
        DocumentDTO documentDTO = new DocumentDTO("1");
        Map<String, String> errors = documentDTOValidator.validate(documentDTO);
        Assert.assertNotNull(errors);
    }
}
