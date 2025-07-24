package shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "shopping.Repository")
@ComponentScan(basePackages = "shopping")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
