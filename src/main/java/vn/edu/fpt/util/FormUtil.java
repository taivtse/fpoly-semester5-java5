package vn.edu.fpt.util;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import vn.edu.fpt.command.AbstractCommand;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@Component
public class FormUtil {
    private static CookieLocaleResolver localeResolver;

    @Autowired
    private CookieLocaleResolver injectedLocaleResolver;

    @PostConstruct
    public void init() {
        localeResolver = injectedLocaleResolver;
    }

    public static <T extends AbstractCommand> T populate(Class<T> clazz, HttpServletRequest request) {
        T object = null;
        try {
            object = clazz.newInstance();

            DateTimeConverter dateConverter = new DateConverter(null);
            dateConverter.setPatterns(new String[]{"dd/MM/yyyy", "MM/dd/yyyy"});

            ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
            convertUtilsBean.register(dateConverter, Date.class);

            BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean, new PropertyUtilsBean());
            beanUtilsBean.populate(object, request.getParameterMap());

            object.setLocale(localeResolver.resolveLocale(request));
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return object;
    }
}
