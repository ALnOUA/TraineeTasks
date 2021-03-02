package shop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "shop.*")
public class WebConfig {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @Configuration
    @Component
    public class DataSourceBean {

        @ConfigurationProperties(prefix = "spring.datasource")
        @Bean
        @Primary
        public DataSource getDataSource() {
            return DataSourceBuilder
                    .create()
                    .url("jdbc:mysql://localhost:3306/online_shop?serverTimezone=UTC&useSSL=false")
                    .username("root")
                    .password("leha21")
                    .driverClassName("com.mysql.jdbc.Driver")

                    .build();
        }
    }
}
