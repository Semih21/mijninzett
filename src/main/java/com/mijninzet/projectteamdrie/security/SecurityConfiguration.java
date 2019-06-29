package com.mijninzet.projectteamdrie.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("select email, password, '1' as enabled from user where email=? and status='VERIFIED'")
    private String usersQuery;

    @Value("select u.email, r.role_name from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?")
    private String rolesQuery;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomLoginSuccessHandler successHandler;
    private final DataSource dataSource;

    public SecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder, CustomLoginSuccessHandler succesHandler, DataSource dataSource) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.successHandler = succesHandler;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // URLs matching for access rights
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/confirm").permitAll()
                .antMatchers("/forgot-password").permitAll()
                .antMatchers("/confirm-reset").permitAll()
                .antMatchers("/reset-password").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/home/**").hasAnyAuthority("ADMIN", "TEACHER", "COORDINATOR", "SCHEDULAR", "MANAGER")
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers("/exception/**").hasAnyAuthority("TEACHER")
                .antMatchers("/schedule/**").hasAnyAuthority("TEACHER")
                .antMatchers("/subject/**").hasAnyAuthority("COORDINATOR")
                .antMatchers("/cohort/**").hasAnyAuthority("ADMIN")
                .antMatchers("/generateCohortSchedule").hasAnyAuthority("SCHEDULER")
                .anyRequest().authenticated()
                .and()
                // form login
                .csrf().disable().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .successHandler(successHandler)
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout().deleteCookies("remember-me").permitAll().and().rememberMe().tokenValiditySeconds(120);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/javascripts/**", "/images/**");
    }
}
