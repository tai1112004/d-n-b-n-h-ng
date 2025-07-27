package shopping;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "shopping.Repository")
@ComponentScan(basePackages = "shopping")
public class Application {
    public static void main(String[] args) {
    	 System.out.println(">>> [DEBUG] ENV VARIABLES:");
         for (Map.Entry<String, String> env : System.getenv().entrySet()) {
             System.out.println(env.getKey() + " = " + env.getValue());
         }
        SpringApplication.run(Application.class, args);
    }
}
