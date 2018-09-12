package by.itsm.server.postprocessor;

import by.itsm.server.annotation.Addition;
import by.itsm.server.model.Response;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class RequestServicePostProcessor implements BeanPostProcessor {

    private static String ADDITION = " ||addition||";
    private Map<String, Object> beans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        boolean match = Arrays.stream(bean.getClass().getMethods())
                .anyMatch(m -> m.isAnnotationPresent(Addition.class));
        if (match) {
            beans.put(beanName, bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beans.containsKey(beanName)) {
            Object original = beans.get(beanName);
            Object beanProxy = Proxy.newProxyInstance(
                    original.getClass().getClassLoader(),
                    original.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        Response result = (Response) method.invoke(original, args);
                        Arrays.stream(bean.getClass().getMethods()).forEach((it) -> {
                            if (it.getName().equals(method.getName()) && it.isAnnotationPresent(Addition.class)) {
                                result.setMessage(result.getMessage() + ADDITION);
                            }
                        });
                        return result;
                    }
            );
            return beanProxy;
        }
        return bean;
    }
}
