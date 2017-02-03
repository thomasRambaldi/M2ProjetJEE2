package monapp;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Activity implements Serializable{
	
	private static final long serialVersionUID = 3595117949735258943L;

	@NotNull
	private Integer id;
	
	@NotNull
	private int year;
	
	@NotNull
	private Nature nature;
	
	@NotNull
	private String title;
	
	private String description;
	
	private String web;
	
	public Activity() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String webSite) {
		this.web = webSite;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Nature getNature() {
		return nature;
	}

	public void setNature(Nature nature) {
		this.nature = nature;
	}
}
