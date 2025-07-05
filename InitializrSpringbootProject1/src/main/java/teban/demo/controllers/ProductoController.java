/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teban.demo.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teban.demo.entities.Producto;
import teban.demo.services.ProductoService;


@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    ProductoService productoServiceManager;
    @GetMapping()
    public List<Producto> listarProductos(){
        return this.productoServiceManager.findAll();
    }
    @GetMapping("/{id}")
    public Producto obtenerProductoId( @PathVariable Long id){
        return this.productoServiceManager.getById(id).get();
    }
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Producto> encontrado = productoServiceManager.getById(id);
        if (encontrado.isPresent()) {
            return ResponseEntity.ok(encontrado.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping()
    public ResponseEntity<Producto> save(@RequestBody Producto producto){
        Producto newProducto = productoServiceManager.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProducto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> deleteProducto(@PathVariable Long id){
        Optional<Producto> optionalProducto = this.productoServiceManager.delete(id);
        if(optionalProducto.isPresent()){
            return ResponseEntity.ok(optionalProducto.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id,
                                            @RequestBody Producto producto) {
        // Verifica si el producto existe
        Optional<Producto> productoOptional = this.productoServiceManager.getById(id);
        if (productoOptional.isPresent()) {
            Producto existingProducto = productoOptional.get();
            // Actualiza los valores del producto existente con los nuevos datos
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setPrecio(producto.getPrecio());
            // Agrega más campos si los hay
            // Llama al servicio para actualizar el producto
            Optional<Producto> optionalProducto = this.productoServiceManager.update(id, existingProducto);
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalProducto.get());
        }
        // Si el producto no existe, devuelve un 404
        return ResponseEntity.notFound().build();
    }
}