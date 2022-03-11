package com.example.ecommerceweb.service;

import com.example.ecommerceweb.model.Product;
import com.example.ecommerceweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
    public Product getProductById(Long id) throws UserNotFoundException {
        Optional<Product> result = productRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any product with id" + id);
    }
    public List<Product> getAllProductsByCategoryId(int id){
        return productRepository.findAllByCategoryId(id);
    }

    public List<Product> getAllProductsByName(String name){
        List<Product> result = productRepository.findAllByNameContains(name);
        if(result != null){
            return result;
        }else{
            System.out.println("Can not find product by name");
            return null;
        }
    }
    public List<Product> sortProductByPriceAsc(){
        List<Product> result = productRepository.findAll(Sort.by("price").ascending().and(Sort.by("name")));
        if(result != null){
            return result;
        }else{
            System.out.println("Can not find product by name");
            return null;
        }
    }
    public List<Product> sortProductByPriceDesc(){
        List<Product> result = productRepository.findAll(Sort.by("price").descending().and(Sort.by("name")));
        if(result != null){
            return result;
        }else{
            System.out.println("Can not find product by name");
            return null;
        }
    }
}
