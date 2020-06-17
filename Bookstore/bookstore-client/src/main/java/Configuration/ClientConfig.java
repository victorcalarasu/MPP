package Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan({"Wrapper","UserInterface"})
public class ClientConfig {
    @Bean
    RestTemplate restTemplate(){return new RestTemplate();}
}
