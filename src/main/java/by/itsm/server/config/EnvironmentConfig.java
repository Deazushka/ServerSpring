package by.itsm.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class EnvironmentConfig {
    @Value("${thread.count}")
    private Integer threadCount;

    @Value("${thread.timeout}")
    private Integer timeout;

    @Value("${server.port}")
    private Integer port;

    @Value("${profiles.active}")
    private String profiles;
}
