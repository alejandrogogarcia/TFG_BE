package es.udc.tfg.app.rest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.udc.tfg.app.model.category.Category;
import es.udc.tfg.app.rest.dtos.CategoryDto;
import es.udc.tfg.app.service.categoryservice.CategoryData;
import es.udc.tfg.app.service.categoryservice.CategoryService;
import es.udc.tfg.app.util.conversors.CategoryConversor;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryData categoryData)
			throws InputValidationException, InstanceNotFoundException {

		Category category = categoryService.createCategory(categoryData);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/").build().toUri();

		return ResponseEntity.created(location).body(CategoryConversor.toCategoryDto(category));

	}

	@PutMapping("/{categoryId}/update")
	public ResponseEntity<Void> createCategory(@PathVariable Long categoryId, @RequestBody CategoryData categoryData)
			throws InstanceNotFoundException, InputValidationException {

		categoryService.updateCategory(categoryId, categoryData);

		return new ResponseEntity<Void>(HttpStatus.OK);

	}
}
