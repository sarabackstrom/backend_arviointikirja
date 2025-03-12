package k25.arviointikirja;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

   @Autowired
   private UserDetailsService userDetailsService;

public WebSecurityConfig(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
}

  private static final AntPathRequestMatcher[] WHITE_LIST_URLS = {
        new AntPathRequestMatcher("/api/pupils**")
  };

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
				authorize -> authorize
				.requestMatchers("/css/**").permitAll()
				.requestMatchers(WHITE_LIST_URLS).permitAll()
                .requestMatchers("/create").hasAuthority("ADMIN")
				.anyRequest().authenticated())
				.formLogin(formlogin -> 
					formlogin.loginPage("/login")
					.defaultSuccessUrl("/pupillist", true)
					.permitAll())
				.logout(logout -> logout.permitAll())
				.csrf(csrf -> csrf.disable()); // not for production, just for development

		return http.build();
  }

   @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
