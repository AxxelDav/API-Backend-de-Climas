package com.abm.usuarios.seguridad;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint { //esta clase generar un error de usuario NO autorizado

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException { //Aqui vamos a manejar los errores de que un usuario NO está autorizado
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage()); //envio un mensaje de error de que un usuario NO está autorizado
    }


}
