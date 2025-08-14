/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teban.demo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teban.demo.entities.Persona;
import teban.demo.entities.Producto;
import teban.demo.repositories.PersonaRepository;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> findAll() {
        return (List<Persona>) this.personaRepository.findAll();
    }

    @Override
    public Persona save(Persona persona) {

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = persona.getContraseña(); // tu contraseña en texto plano
        String hashedPassword = encoder.encode(rawPassword);
        persona.setContraseña(hashedPassword);

        persona.setRoles("ADMIN");
        persona.setPermisos("ADMIN");

        return personaRepository.save(persona);
    }

    public Optional<Persona> getById(Long id) {
        return personaRepository.findById(id);
    }

    @Transactional
    public Optional<Persona> delete(Long Id) {
        Optional<Persona> persona = this.getById(Id);
        if (persona.isPresent()) {
            this.personaRepository.deleteById(Id);
        }
        return persona;
    }
}
