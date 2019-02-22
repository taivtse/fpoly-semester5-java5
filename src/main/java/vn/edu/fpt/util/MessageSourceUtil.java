package vn.edu.fpt.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import vn.edu.fpt.command.AbstractCommand;

import javax.annotation.PostConstruct;

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

    public static String get(String key, AbstractCommand command) {
        if (command == null)
            return messageSource.getMessage(key, null, null);
        return messageSource.getMessage(key, null, command.getLocale());
    }
}
