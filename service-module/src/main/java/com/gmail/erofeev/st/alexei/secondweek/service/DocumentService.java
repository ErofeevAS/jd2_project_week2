package com.gmail.erofeev.st.alexei.secondweek.service;

import com.gmail.erofeev.st.alexei.secondweek.service.model.DocumentDTO;

public interface DocumentService {
    DocumentDTO add(DocumentDTO documentDTO);

    DocumentDTO getDocumentById(Long id);

    void delete(Long id);
}
