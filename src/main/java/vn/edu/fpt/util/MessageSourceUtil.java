package vn.edu.fpt.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class MessageSourceUtil {
    private static MessageSource messageSource;

    @Qualifier("messageSource")
    @Autowired
    private MessageSource injectedMessageSource;

    @PostConstruct
    public void init() {
        messageSource = injectedMessageSource;
    }

    public static String get(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }
}
