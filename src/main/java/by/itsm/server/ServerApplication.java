package by.itsm.server;

import by.itsm.server.config.EnvironmentConfig;
import by.itsm.server.config.ObjectMapperConfig;
import by.itsm.server.config.RootEndpointConfig;
import by.itsm.server.model.Request;
import by.itsm.server.service.RequestProcessor;
import by.itsm.server.service.SimpleProcessor;
import by.itsm.server.web.RootEndpoint;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ObjectMapperConfig.class, RootEndpointConfig.class, EnvironmentConfig.class);
        context.registerShutdownHook();

//        RequestProcessor processor = context.getBean(RequestProcessor.class);
//        System.out.println(processor.process(new Request("Alexander", "Hi")));

        RootEndpoint endpoint = context.getBean(RootEndpoint.class);
        while (true) {
            endpoint.run();
        }
    }
}
