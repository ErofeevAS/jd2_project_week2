package com.gmail.erofeev.st.alexei.secondweek.controller.impl;

import com.gmail.erofeev.st.alexei.secondweek.controller.DocumentController;
import com.gmail.erofeev.st.alexei.secondweek.controller.exception.ControllerException;
import com.gmail.erofeev.st.alexei.secondweek.controller.validataor.DocumentDTOValidator;
import com.gmail.erofeev.st.alexei.secondweek.service.DocumentService;
import com.gmail.erofeev.st.alexei.secondweek.service.model.DocumentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class DocumentControllerImpl implements DocumentController {
    private static final Logger logger = LogManager.getLogger(DocumentControllerImpl.class);
    private DocumentService documentService;
    private DocumentDTOValidator documentDTOValidator;

    @Autowired
    public DocumentControllerImpl(DocumentService documentService, DocumentDTOValidator documentDTOValidator) {
        this.documentService = documentService;
        this.documentDTOValidator = documentDTOValidator;
    }

    @Override
    public DocumentDTO add(DocumentDTO documentDTO) {
        Map<String, String> errors = documentDTOValidator.validate(documentDTO);
        if (errors.isEmpty()) {
            try {
                DocumentDTO savedDocumentDTO = documentService.add(documentDTO);
                logger.info(savedDocumentDTO + " was saved");
                return savedDocumentDTO;
            } catch (RuntimeException e) {
                logger.error(e.getMessage(), e);
                throw new ControllerException(e.getMessage(), e);
            }
        } else {
            for (Map.Entry<String, String> entry : errors.entrySet()) {
                String message = "not valid data: " + entry;
                logger.error(message);
                throw new ControllerException(message);
            }
        }
        return documentDTO;
    }

    @Override
    public DocumentDTO getDocumentById(Long id) {
        try {
            DocumentDTO documentDTO = documentService.getDocumentById(id);
            logger.info(documentDTO + " was found");
            return documentDTO;
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            throw new ControllerException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            documentService.delete(id);
            logger.info("document with id: " + id + " was deleted");
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            throw new ControllerException(e.getMessage(), e);
        }
    }
}
