/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package teban.demo.services;

import java.util.List;
import java.util.Optional;
import teban.demo.entities.Persona;


/**
 *
 * @author PC
 */
public interface IPersonaService {
    public List<Persona> findAll();
    public Persona save (Persona id);
    public Optional<Persona> getById(Long Id);
    public Optional<Persona> delete(Long Id);
    public Optional<Persona> update(Long Id, Persona persona);
    public Persona getByNombre(String nombre);
    
}
