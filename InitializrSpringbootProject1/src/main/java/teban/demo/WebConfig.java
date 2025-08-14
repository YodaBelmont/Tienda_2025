/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teban.demo;

// Importaciones necesarias
import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Bean que define el locale (idioma) por defecto en la sesión del usuario
    @Bean
    public SessionLocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es")); // Establece el idioma por defecto a español
        return slr;
    }

    // Bean que permite cambiar el idioma mediante un parámetro en la URL (por ejemplo, ?lang=en)
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang"); // El idioma se cambia usando el parámetro "lang"
        return lci;
    }

    // Agrega el interceptor de cambio de idioma a la configuración de Spring MVC
    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    // Define controladores de vista simples sin lógica de negocio (solo redirección de rutas)
    @Override
    public void addViewControllers(ViewControllerRegistry registro) {
        registro.addViewController("/").setViewName("personas"); // Ruta raíz ("/") muestra la vista "personas"
        registro.addViewController("/login");                    // Ruta "/login" usa la vista por defecto "login"
        registro.addViewController("/errores/403").setViewName("/errores/403"); // Ruta para acceso denegado
    }
}

