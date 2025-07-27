package shopping;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupLogger {

    @Value("${spring.datasource.url:NOT_FOUND}")
    private String dbUrl;

    @EventListener(ApplicationReadyEvent.class)
    public void logUrl() {
        System.out.println(">>> [DEBUG] spring.datasource.url = " + dbUrl);
    }
}
