package com.gmail.erofeev.st.alexei.secondweek.service.converter;

import com.gmail.erofeev.st.alexei.secondweek.repository.model.Document;
import com.gmail.erofeev.st.alexei.secondweek.service.model.DocumentDTO;
import org.springframework.stereotype.Component;

public interface DocumentConverter {
    DocumentDTO toDTO(Document document);

    Document fromDTO(DocumentDTO documentDTO);


}
