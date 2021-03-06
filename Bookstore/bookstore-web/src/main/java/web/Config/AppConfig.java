package web.Config;
import Configuration.JPAConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
@Configuration

@Import({JPAConfig.class})
@PropertySources({@PropertySource(value="classpath:local/db.properties"),})
public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
