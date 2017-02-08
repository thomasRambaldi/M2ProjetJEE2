package monapp;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
public class Activity implements Serializable{
	
	private static final long serialVersionUID = 3595117949735258943L;

	@NotNull(message="Veuillez saisir une ann�e pour votre activit�e")
	private Integer year;
	
	@NotNull
	private Nature nature;
	
	@NotNull(message="Veuillez saisir titre pour votre activit�e")
	@Size( min=5, max=100, message = "L'ann�e doit avoir caract�res" )
	@Pattern( regexp = "^[a-zA-Z0-9\\s]+$" , message = "Merci de saisir un titre valide" )
	private String title;
	
	@Size( min=0, max=500, message = "La description doit avoir au minimum 0 caract�res et au maximum 500" )
	private String description;
	
	@Size( min=0, max=200, message = "L'URL doit avoir au minimum 0 caract�res et au maximum 500" )
	private String web;
	
	public Activity() {
		
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}


	public Nature getNature() {
		return nature;
	}

	public void setNature(Nature nature) {
		this.nature = nature;
	}
	
	public boolean isEmpty(){
		return title == null;
	}
	
}
