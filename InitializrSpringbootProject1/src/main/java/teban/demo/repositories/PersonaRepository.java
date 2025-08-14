/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package teban.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import teban.demo.entities.Persona;


/**
 *
 * @author PC
 */
public interface PersonaRepository extends CrudRepository<Persona, Long>{
    Persona findByNombre(String nombre);
    
}
