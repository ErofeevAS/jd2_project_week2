package com.gmail.erofeev.st.alexei.secondweek.service.converter.impl;

import com.gmail.erofeev.st.alexei.secondweek.repository.model.Document;
import com.gmail.erofeev.st.alexei.secondweek.service.converter.DocumentConverter;
import com.gmail.erofeev.st.alexei.secondweek.service.model.DocumentDTO;
import org.springframework.stereotype.Component;

@Component
class DocumentConverterImpl implements DocumentConverter {

    public DocumentDTO toDTO(Document document) {
        Long id = document.getId();
        String uniqueNumber = document.getUniqueNumber();
        String description = document.getDescription();
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(id);
        documentDTO.setDescription(description);
        documentDTO.setUniqueNumber(uniqueNumber);
        return documentDTO;
    }

    public Document fromDTO(DocumentDTO documentDTO) {
        Long id = documentDTO.getId();
        String description = documentDTO.getDescription();
        String uniqueNumber = documentDTO.getUniqueNumber();
        Document document = new Document();
        document.setId(id);
        document.setDescription(description);
        document.setUniqueNumber(uniqueNumber);
        return document;
    }
}