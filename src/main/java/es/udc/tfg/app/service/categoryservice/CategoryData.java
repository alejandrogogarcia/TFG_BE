package es.udc.tfg.app.service.categoryservice;

public class CategoryData {

	private String name;

	private String description;
	
	private Long creatorId;

	public CategoryData() {
	}

	public CategoryData(String name, String description, Long creatorId) {
		this.name = name;
		this.description = description;
		this.creatorId = creatorId;
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

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

}
