package com.example.SpringbootApi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.SpringbootApi.aop.AuthenticationEntryPointImpl;
import com.example.SpringbootApi.handler.AuthenticationFailureHandlerImpl;
import com.example.SpringbootApi.handler.AuthenticationSuccessHandlerImpl;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
    
    @Autowired
    private UserDetailsService userDetailsService;
    
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/user/isLogin").permitAll()
				.antMatchers("/product").permitAll()
				.anyRequest().authenticated()
				.and().logout().permitAll()
				.and().formLogin()
				.loginProcessingUrl("/login")
				.usernameParameter("username")
	            .passwordParameter("password")
				.successHandler(new AuthenticationSuccessHandlerImpl())
                .failureHandler(new AuthenticationFailureHandlerImpl())
                .and().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointImpl())
                .and().csrf().ignoringAntMatchers("/login");
	}

    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略靜態資源
        web.ignoring().antMatchers("/js/**", "/css/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        	.passwordEncoder(passwordEncoder());
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
