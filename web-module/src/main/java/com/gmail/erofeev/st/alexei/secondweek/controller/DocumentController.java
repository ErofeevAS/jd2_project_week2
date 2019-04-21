package com.gmail.erofeev.st.alexei.secondweek.controller;

import com.gmail.erofeev.st.alexei.secondweek.service.model.DocumentDTO;

public interface DocumentController {
    DocumentDTO add(DocumentDTO documentDTO);

    DocumentDTO getDocumentById(Long id);

    void delete(Long id);
}
