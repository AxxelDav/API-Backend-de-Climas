package com.abm.usuarios.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService; //userDetails sirve para traerte datos de un usuario, porque tiene el metodo "loadByUsername"

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { //Este metodo es para codificar la contraseña
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  //con este metodo voy a validar si los datos que estoy obteniendo en el metodo de arriba, son validos o no
      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/abm/usuario/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
        }


//
//
//
//                anyRequest() //Permito a todos los usuarios usar el ABMControlador (fijate la ruta) (cualquier endpoint)
//                .authenticated().and().httpBasic(); //Pero a los endpoint de otro controlador (ejemplo: CiudadControlador), a quienes les puse @PreAutorize, a esos si pido que se authentiquen con AUTHENTICACION BASICA (sin token ni nada)

//                .antMatchers("/ciudad/detalle/ciudadesYtemp") //Otra forma limitar con el tipo de ROL, para no usar el @PreAutorize en cada endpoint
////                    .hasAnyRole("USER", "ADMIN")
//                      .hasRole("ADMIN")
//                .antMatchers("/ciudad/detalle/ciudades")
//                      .hasRole("USER")
//                .antMatchers().authenticated();

}
