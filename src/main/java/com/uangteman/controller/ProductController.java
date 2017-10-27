package com.uangteman.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uangteman.dto.Result;
import com.uangteman.dto.SearchForm;
import com.uangteman.dto.SearchPriceForm;
import com.uangteman.entity.Category;
import com.uangteman.entity.Product;
import com.uangteman.repo.ProductRepo;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepo repo;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insertOrUpdate(@Valid @RequestBody Product product, Errors errors) throws Exception {
		Result result = new Result();
		if(errors.hasErrors()) {
			for(ObjectError err: errors.getAllErrors()) {
				result.getMessage().add(err.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(result);
		}
		Product output = repo.save(product);
		result.setPayload(output);
		return ResponseEntity.ok(result);
	}
//	public Product insertOrUpdate(@RequestBody Product product) {
//		return repo.save(product);
//	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Product> findAll() {
		return repo.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/byprice")
	public List<Product> findByPriceRange(@RequestBody SearchPriceForm form) {
		return repo.findByPriceRange(form.getMin(), form.getMax());
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/bycategory")
	public List<Product> findByCategory(@RequestBody Category category) {
		return repo.findByCategory(category);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/byname")
	public List<Product> findByName(@RequestBody SearchForm form) {
		return repo.findByName("%"+form.getName()+"%");
	}
	
	
}
