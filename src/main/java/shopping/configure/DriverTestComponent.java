package shopping.configure;



import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

@Component
public class DriverTestComponent implements CommandLineRunner {

    private final DataSource dataSource;

    public DriverTestComponent(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== DRIVER AND CONNECTION TEST ===");
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver found in classpath");
        } catch (ClassNotFoundException e) {
            System.err.println(" MySQL Driver NOT found: " + e.getMessage());
            return;
        }
        
        
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("Database connection successful");
            System.out.println("Database: " + metaData.getDatabaseProductName());
            System.out.println("Version: " + metaData.getDatabaseProductVersion());
            System.out.println("URL: " + metaData.getURL());
        } catch (Exception e) {
            System.err.println(" Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("=== TEST COMPLETED ===");
    }
}