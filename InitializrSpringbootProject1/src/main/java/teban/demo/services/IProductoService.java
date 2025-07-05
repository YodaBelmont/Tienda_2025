/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package teban.demo.services;

import java.util.List;
import java.util.Optional;
import teban.demo.entities.Producto;

public interface IProductoService {
    public List<Producto> findAll();
    public Producto save (Producto producto);
    public Optional<Producto> getById(Long Id);
    public Optional<Producto> delete(Long Id);
    public Optional<Producto> update(Long Id, Producto producto);
    
}
