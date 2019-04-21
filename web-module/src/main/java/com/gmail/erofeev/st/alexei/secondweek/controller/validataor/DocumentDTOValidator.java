package com.gmail.erofeev.st.alexei.secondweek.controller.validataor;

import com.gmail.erofeev.st.alexei.secondweek.service.model.DocumentDTO;

import java.util.Map;

public interface DocumentDTOValidator {
    Map<String, String> validate(DocumentDTO documentDTO);
}