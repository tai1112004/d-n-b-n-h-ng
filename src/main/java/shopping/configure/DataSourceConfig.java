package shopping.configure;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    
    @Value("${MYSQLHOST}")
    private String host;
    
    @Value("${MYSQLPORT}")
    private String port;
    
    @Value("${MYSQL_DATABASE}")
    private String database;
    
    @Value("${MYSQLUSER}")
    private String username;
    
    @Value("${MYSQL_ROOT_PASSWORD}")
    private String password;
    
    @Bean
    @Primary
    public DataSource dataSource() {
        System.out.println("=== CREATING DATASOURCE ===");
        System.out.println("Host: " + host);
        System.out.println("Port: " + port);
        System.out.println("Database: " + database);
        System.out.println("Username: " + username);
        
        HikariConfig config = new HikariConfig();
        String jdbcUrl = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&createDatabaseIfNotExist=true", 
                                     host, port, database);
        
        System.out.println("JDBC URL: " + jdbcUrl);
        
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        
        // Connection pool settings
        config.setMaximumPoolSize(5);
        config.setMinimumIdle(1);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: MySQL Driver not found: " + e.getMessage());
            throw new RuntimeException("MySQL Driver not found", e);
        }
        
        HikariDataSource dataSource = new HikariDataSource(config);
        System.out.println("DataSource created successfully");
        
        return dataSource;
    }
}