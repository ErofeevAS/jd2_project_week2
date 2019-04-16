package com.gmail.erofeev.st.alexei.secondweek.controller.validataor.impl;

import com.gmail.erofeev.st.alexei.secondweek.controller.validataor.DocumentDTOValidator;
import com.gmail.erofeev.st.alexei.secondweek.service.model.DocumentDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.gmail.erofeev.st.alexei.secondweek.controller.message.Messages.*;

@Component
public class DocumentDTOValidatorImpl implements DocumentDTOValidator {
    private static final int MAX_LENGTH_DESCRIPTION = 20;

    public Map<String, String> validate(DocumentDTO documentDTO) {
        Map<String, String> errors = new HashMap();
        String description = documentDTO.getDescription();
        if (description == null) {
            errors.put("description_null", MESSAGE_NOT_NULL);
        } else {
            if (description.length() > MAX_LENGTH_DESCRIPTION) {
                errors.put("description_length", MESSAGE_LENGTH_LESS_THEN_TWENTY);
            }
            if (description.isEmpty()) {
                errors.put("description_empty", MESSAGE_NOT_EMPTY);
            }
        }
        return errors;
    }
}
