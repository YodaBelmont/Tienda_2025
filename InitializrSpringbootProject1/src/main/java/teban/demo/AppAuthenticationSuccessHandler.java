/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teban.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;




@Component
public class AppAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectUrl = "/";
        
        for(GrantedAuthority authority: authorities){
            String role = authority.getAuthority();
            if(role.equals("ROLE_ADMIN")){
                redirectUrl = "/admin/dashboard";
                break;
            }else if (role.equals("ROLE_VENDEDOR")){
                redirectUrl = "/ventas";
                break;
            }else if (role.equals("ROLE_USER")){
                redirectUrl = "/home";
                break;
            }           
        }
        response.sendRedirect(redirectUrl);
    }   
}
