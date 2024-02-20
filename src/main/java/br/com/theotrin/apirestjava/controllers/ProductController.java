package br.com.theotrin.apirestjava.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.theotrin.apirestjava.models.Product;
import br.com.theotrin.apirestjava.repositories.ProductRepository;
import br.com.theotrin.apirestjava.utils.ResponseHandle;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository; //instanciando o product repository
    
    //exibir uma coleção de produtos
    @GetMapping("")
    public List<Product> listar(){
        return productRepository.findAll();
    };
    //consultar um produto
    @GetMapping("/{id}")
    public ResponseEntity<Object> obter(@PathVariable Integer id){
       Optional<Product> product =  productRepository.findById(id);

       if(!product.isPresent()){
            return ResponseHandle.generate("Produto não encontrado :/", HttpStatus.NOT_FOUND);
       }

       return new ResponseEntity<Object>(product.get(), HttpStatus.OK);

    };
    //cadastrar um produto
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Product product){

        if(product.getName() == null) {
            return ResponseHandle.generate("É preciso informar um nome para o produto", HttpStatus.BAD_REQUEST);
        }

        if(product.getPrice() == null) {
            return ResponseHandle.generate("É preciso informar o preço do produto", HttpStatus.BAD_REQUEST);
        }

        Product newProduct = productRepository.save(product);


        return new ResponseEntity<Object>(newProduct, HttpStatus.CREATED);
    }
    //editar um produto
    @PutMapping("/{id}")
    public ResponseEntity<Object>  update(@PathVariable Integer id, @RequestBody Product product) {
        
        Optional<Product> oldProduct =  productRepository.findById(id);

       if(!oldProduct.isPresent()){
            return ResponseHandle.generate("Produto não encontrado :/", HttpStatus.NOT_FOUND);
       }

        if(product.getName() == null) {
            return ResponseHandle.generate("É preciso informar um nome para o produto", HttpStatus.BAD_REQUEST);
        }

        if(product.getPrice() == null) {
            return ResponseHandle.generate("É preciso informar o preço do produto", HttpStatus.BAD_REQUEST);
        }

        Product updateProduct = oldProduct.get();

        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setDescription(product.getDescription());
        
        productRepository.save(updateProduct);

        return ResponseEntity.noContent().build();
    }
    //excluir um produto
    @DeleteMapping("/{id}")

    public ResponseEntity<Object>  delete(@PathVariable Integer id, Product product){
        
        Optional<Product> deletedProduct =  productRepository.findById(id);

        if(!deletedProduct.isPresent()){
             return ResponseHandle.generate("Produto não encontrado :/", HttpStatus.NOT_FOUND);
        }
 
        productRepository.delete(deletedProduct.get());

        return ResponseEntity.noContent().build();

    }


}
