package com.gmail.erofeev.st.alexei.secondweek.controller;

import com.gmail.erofeev.st.alexei.secondweek.controller.validataor.impl.DocumentDTOValidatorImpl;
import com.gmail.erofeev.st.alexei.secondweek.service.model.DocumentDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class DocumentDTOValidatorTest {
    private DocumentDTOValidatorImpl documentDTOValidator;

    @Before
    public void init() {
        documentDTOValidator = new DocumentDTOValidatorImpl();
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

    @Test
    public void shouldReturnNotEmpty() {
        DocumentDTO documentDTO = new DocumentDTO("");
        Map<String, String> errors = documentDTOValidator.validate(documentDTO);
        boolean isValid = errors.containsKey("description_empty");
        Assert.assertTrue(isValid);
    }

    @Test
    public void shouldReturnMapErrorWithNotNull() {
        DocumentDTO documentDTO = new DocumentDTO();
        Map<String, String> errors = documentDTOValidator.validate(documentDTO);
        boolean isValid = errors.containsKey("description_null");
        Assert.assertTrue(isValid);
    }
}
