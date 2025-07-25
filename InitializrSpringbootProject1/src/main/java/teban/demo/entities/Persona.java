/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teban.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(length = 100, nullable = false)
    private String nombre;
    
    @Column(length = 100, nullable = false)
    private String apellido;
    
    @NotBlank
    @Email
    @Column(length = 191, nullable = false)
    private String email;
    
    @Column(length = 30)
    private String telefono;
    
    @Column(length = 225)
    private String direccion;
    
    @NotBlank
    @Column(length = 100, nullable = false)
    private String contraseña;    
    
    @Column(nullable = false)
    private int active = 1;
    
    @Column(length = 255)
    private String roles = "";
    
    @Column(length = 500)
    private String permisos = "";
    
    @Transient
    public boolean isEnabled(){
        return this.active ==1;
    }
    
    @Transient
    public List<String> getRoleList(){
        if(roles == null || roles.isBlank()) return new ArrayList<>();
        return Arrays.stream(roles.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
    
    @Transient
    public List<String> getPermissionList(){
        if(permisos == null || permisos.isBlank()) return new ArrayList<>();
        return Arrays.stream(permisos.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
    
    public void addRole(String roles){
        if(roles == null || roles.isBlank()) return;
        List<String> list = getRoleList();
        String r = roles.trim().toUpperCase();
        if(!list.contains(r)){
            list.add(r);
            this.roles = String.join(",", list);
        }       
    }
    public void addPermissions(String permisos){
        if(permisos == null || permisos.isBlank()) return;
        List<String> list = getPermissionList();
        String p = permisos.trim();
        if(!list.contains(p)){
            list.add(p);
            this.permisos = String.join(",", list);
        }    
    }
    

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }           
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }
    
    
    
}
