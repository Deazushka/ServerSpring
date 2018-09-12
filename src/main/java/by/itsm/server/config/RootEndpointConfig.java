package by.itsm.server.config;

import by.itsm.server.service.RequestProcessor;
import by.itsm.server.web.RootEndpoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@ComponentScan("by.itsm.server")
public class RootEndpointConfig {

    @Bean
    public RootEndpoint rootEndpoint(
            ObjectMapper mapper,
//            Provider<List<RequestProcessor>> processor,
            RequestProcessor processor,
            ConfigurableEnvironment environment) {

        Integer port =
                environment.getProperty(
                        "server.port",
                        Integer.class,
                        8088
                );
        Integer threadCount =
                environment.getProperty(
                        "thread.count",
                        Integer.class,
                        4
                );

        return new RootEndpoint(
                port,
                threadCount,
                mapper,
                processor);

    }
}
