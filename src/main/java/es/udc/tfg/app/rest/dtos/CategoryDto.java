package es.udc.tfg.app.rest.dtos;

import java.util.Calendar;

import es.udc.tfg.app.model.user.User;

public class CategoryDto {

	private Long id;
	private String name;
	private String description;
	private Calendar createDate;
	private Long creatorId;
	
	public CategoryDto() {
	}

	public CategoryDto(Long id, String name, String description, Calendar createDate, Long creatorId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.createDate = createDate;
		this.creatorId = creatorId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	
	
	
	

}
