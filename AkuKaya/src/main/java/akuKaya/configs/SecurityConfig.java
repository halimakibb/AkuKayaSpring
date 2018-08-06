package main.java.akuKaya.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import main.java.akuKaya.handlers.UserAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our security data source

	@Autowired
	private DataSource dataSource;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private Environment env;

	@Bean
    public AuthenticationSuccessHandler userAuthenticationSuccessHandler(){
        return new UserAuthenticationSuccessHandler();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// use jdbc authentication ... oh yeah!!!

		auth.jdbcAuthentication()
				.usersByUsernameQuery("select Username as username, Password as password, IsActive as enabled from dbo.TblM_User where username = ?")
				.authoritiesByUsernameQuery("SELECT Users.Username as username," + " Roles.RoleName as role"
						+ "	FROM dbo.TblM_User AS Users"
						+ "	JOIN dbo.TblMapping_User_Role AS UserRole ON Users.UserID = UserRole.UserID"
						+ "	JOIN dbo.TblM_Role AS Roles ON UserRole.RoleID = Roles.RoleID" + "	WHERE Users.Username=?")
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/transaction/**")
					.hasAnyAuthority("User", "Admin")
				.antMatchers("/users/**")
					.permitAll()
				.antMatchers("/**")
					.permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
					.loginPage("/users/login")
					.failureUrl("/users/login?error")
					.loginProcessingUrl("/users/authenticate")
					.successHandler(userAuthenticationSuccessHandler())
					.permitAll()
				.and()
				.logout()
	                .invalidateHttpSession(true)
	                .clearAuthentication(true)
	                .logoutSuccessUrl("/users/login?logout")
	                .deleteCookies("JSESSIONID")
	                .permitAll()
				.and()
				.exceptionHandling()
					.accessDeniedPage("/forbidden")
					.and()
	            .rememberMe().key(env.getProperty("spring.security.remember_me"));

	}

}
