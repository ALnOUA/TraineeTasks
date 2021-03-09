package com.ua.services;

import com.ua.ProductConverter;
import com.ua.dto.ProductDto;
import com.ua.model.Product;
import com.ua.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
