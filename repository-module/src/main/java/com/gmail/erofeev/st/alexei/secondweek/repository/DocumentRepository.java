package com.gmail.erofeev.st.alexei.secondweek.repository;

import com.gmail.erofeev.st.alexei.secondweek.repository.model.Document;

public interface DocumentRepository {
    Document add(Document document);

    Document getDocumentById(Long id);

    void delete(Long id);

}
