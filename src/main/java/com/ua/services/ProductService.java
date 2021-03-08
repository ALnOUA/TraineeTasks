package com.ua.services;

import java.util.List;

import com.ua.repositories.ProductRepository;
import com.ua.ProductConverter;
import com.ua.dto.ProductDto;
import com.ua.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductConverter productConverter;
	@Autowired
	private ProductRepository repo;

	public List<ProductDto> listAll() {
		 return productConverter.listProductDto(repo.findAll());
	}




	public void save(Product product) {
		repo.save(product);
	}
	
	public Object get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
