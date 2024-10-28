package com.example.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizationService {
    @Autowired
    private MessageSource messageSource;

    public Locale getLocaleFromHeader(String localeStr) {
        if (localeStr != null && !localeStr.isEmpty()) {
            String[] parts = localeStr.split(",")[0].split("-");
            if (parts.length == 2) {
                return new Locale(parts[0], parts[1]);
            } else {
                return new Locale(parts[0]);
            }
        }
        return Locale.getDefault(); // Default locale
    }

    public String getMessage(String code, Locale locale) {
        return messageSource.getMessage(code, null, locale);
    }
}
