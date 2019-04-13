package com.gmail.erofeev.st.alexei.secondweek.controller.validataor;

import com.gmail.erofeev.st.alexei.secondweek.service.model.DocumentDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DocumentDTOValidator {
    private static final int MAX_LENGTH_DESCRIPTION = 20;

    public Map<String, String> validate(DocumentDTO documentDTO) {
        Map<String, String> errors = new HashMap();
        String description = documentDTO.getDescription();
        if (description.length() > MAX_LENGTH_DESCRIPTION) {
            errors.put(description, "TOO_LONG_DESCRIPTION");
        }
        return errors;
    }

}
