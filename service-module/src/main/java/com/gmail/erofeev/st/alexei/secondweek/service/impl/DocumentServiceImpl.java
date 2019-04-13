package com.gmail.erofeev.st.alexei.secondweek.service.impl;

import com.gmail.erofeev.st.alexei.secondweek.repository.DocumentRepository;
import com.gmail.erofeev.st.alexei.secondweek.repository.model.Document;
import com.gmail.erofeev.st.alexei.secondweek.service.DocumentService;
import com.gmail.erofeev.st.alexei.secondweek.service.converter.DocumentConverter;
import com.gmail.erofeev.st.alexei.secondweek.service.exception.DocumentServiceException;
import com.gmail.erofeev.st.alexei.secondweek.service.model.DocumentDTO;
import com.gmail.erofeev.st.alexei.secondweek.service.util.UniqueNumberGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {
    private static final Logger logger = LogManager.getLogger(DocumentServiceImpl.class);
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public DocumentDTO add(DocumentDTO documentDTO) {
        documentDTO.setUniqueNumber(UniqueNumberGenerator.generate());
        Document document = DocumentConverter.fromDTO(documentDTO);
        document = documentRepository.add(document);
        documentDTO = DocumentConverter.toDTO(document);
        return documentDTO;
    }

    @Override
    public DocumentDTO getDocumentById(Long id) {
        Document document = documentRepository.getDocumentById(id);
        if (document != null) {
            DocumentDTO documentDTO = DocumentConverter.toDTO(document);
            return documentDTO;
        } else {
            String message = "Document with id: " + id + " not found";
            logger.error(message);
            throw new DocumentServiceException(message);
        }
    }

    @Override
    public void delete(Long id) {
        documentRepository.delete(id);
    }
}
