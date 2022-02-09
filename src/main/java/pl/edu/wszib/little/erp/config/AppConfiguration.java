package pl.edu.wszib.little.erp.config;

import org.hibernate.SessionFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@ComponentScan("pl.edu.wszib.little.erp")
public class AppConfiguration implements WebMvcConfigurer
{
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS =
            {
                    "classpath:/META-INF/resources/", "classpath:/resources/",
                    "classpath:/static/", "classpath:/public/","classpath:/static/images"
            };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    @Configuration
    public class DatasourceConfig {
        @Bean
        public DataSource datasource() {
            return DataSourceBuilder.create()
                    .driverClassName("com.mysql.cj.jdbc.Driver")
                    .url("jdbc:mysql://localhost:3306/littleerp")
                    .username("root")
                    .password("")
                    .build();
        }
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }
}
