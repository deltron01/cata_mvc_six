package org.sid.sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration    // mandatory annotation
@EnableWebSecurity // mandatory annotation
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		BCryptPasswordEncoder bcpe = getPWDENC();
		//System.out.println("--*" + bcpe.encode("1234") + "*--");
		/*auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN", "USER");
		auth.inMemoryAuthentication().withUser("user1").password("{noop}1234").roles("USER");*/
		
		/*auth.inMemoryAuthentication().withUser("admin").password(bcpe.encode("1234")).roles("ADMIN", "USER");
		auth.inMemoryAuthentication().withUser("user1").password(bcpe.encode("1234")).roles("USER");
		auth.inMemoryAuthentication().passwordEncoder(bcpe);*/
		
		auth.jdbcAuthentication().dataSource(dataSource)
		       .usersByUsernameQuery("SELECT username AS principal, password AS credentials, active FROM users where username = ?")
		       .authoritiesByUsernameQuery("SELECT username AS principal, role AS role FROM users_roles WHERE username = ?")
		       .rolePrefix("ROLE_")
		       .passwordEncoder(bcpe);
	}

	@Bean
	public BCryptPasswordEncoder getPWDENC() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	       http.formLogin().loginPage("/login");
	       //http.authorizeRequests().anyRequest().authenticated();
	       http.authorizeRequests().antMatchers("/formProduit","/save","/delete","/edit").hasRole("ADMIN");
	       http.authorizeRequests().antMatchers("/index").hasRole("USER");
	       http.exceptionHandling().accessDeniedPage("/403");
	}
}
