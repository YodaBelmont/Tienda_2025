/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teban.demo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teban.demo.entities.Producto;
import teban.demo.repositories.ProductoRepository;

/**
 *
 * @author PC
 */
@Service
    public class ProductoService implements IProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Override
    @Transactional
    public Producto save(Producto producto) {
        return this.productoRepository.save(producto);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>) this.productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> getById(Long Id) {
        return this.productoRepository.findById(Id);
    }

    @Override
    @Transactional
    public Optional<Producto> delete(Long Id) {
        Optional<Producto> producto = this.getById(Id);
        if(producto.isPresent()){
            this.productoRepository.deleteById(Id);
        }
        return producto;
    }

    @Override
    public Optional<Producto> update(Long Id, Producto producto) {
        Optional<Producto> producto1 = this.getById(Id);
        if(producto1.isPresent()){
            Producto prod = producto1.orElseThrow();
            prod.setNombre(producto.getNombre());
            prod.setPrecio(prod.getPrecio());
        }
        return Optional.of(this.productoRepository.save(producto));
    }
}
