package es.udc.tfg.app.util.conversors;

import es.udc.tfg.app.model.category.Category;
import es.udc.tfg.app.rest.dtos.CategoryDto;

public class CategoryConversor {

	public static CategoryDto toCategoryDto(Category category) {
		return new CategoryDto(category.getId(), category.getName(), category.getDescription(),
				category.getCreateDate(), category.getCreator().getId());

	}

}
