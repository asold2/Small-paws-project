package smallpawsproject.securityjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class JWTSecurityConfiguration extends WebSecurityConfigurerAdapter
{
//  @Autowired
//  private JWTSecurityFilter jwtFilter;

//how the user is identified
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception
  {
    PasswordEncoder encoder = PasswordEncoderFactories
        .createDelegatingPasswordEncoder();

    auth.inMemoryAuthentication()
        .withUser("admin")
        .password(encoder.encode("admin"))
        .roles("ADMIN");
  }

//The routes/ways that must be protected and how
  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http = http.csrf().disable();

    http = http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

//    http.authorizeRequests()
////        .antMatchers("")
////        .authenticated()
//        .antMatchers("/register")
//        .permitAll()
//        .antMatchers("/loginEmployee")
//        .permitAll();


    //    http.addFilterBefore(
//        jwtFilter, UsernamePasswordAuthenticationFilter.class
//    );


  }



}
