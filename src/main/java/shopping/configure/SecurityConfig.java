package shopping.configure;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailsService ; 
	@Autowired
	JWTConfig jwtConfig ; 
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.cors()
        	.and()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/verify").authenticated()
                .antMatchers("/api/admin/**").hasRole("ADMIN") 
                .antMatchers("/api/user/**").hasRole("USER") 
                .anyRequest().permitAll()
            .and()
            .httpBasic() ;
        http.addFilterBefore(jwtConfig, UsernamePasswordAuthenticationFilter.class) ; 
        http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider()); 
    }
    
    @Bean 
    public AuthenticationProvider authenticationProvider() 
    {
    	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider() ; 
    	daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12)); 
    	daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return  daoAuthenticationProvider ; 
    }
    @Bean
    public AuthenticationManager AuthenticationManager(AuthenticationConfiguration config) throws Exception 
    {
    	return config.getAuthenticationManager() ;
    }
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration config = new CorsConfiguration();
    	config.setAllowedOrigins(Arrays.asList("https://baitaplon-git-main-hoang-tais-projects.vercel.app"));
    	config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    	config.setAllowedHeaders(Arrays.asList("*"));
    	config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }


}
