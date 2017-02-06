package monapp;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(schema="projetjee2")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "firstName")
	@NotNull(message="Veuillez saisir votre prénom")
	@Size( min=4, max=50, message = "Le prénom doit avoir au moins 4 caractères" )
	@Pattern( regexp = "^[A-Za-z-]+$", message = "Merci de saisir un prénom valide" )
	private String firstName;

	@Column(name = "lastName")
	@NotNull(message="Veuillez saisir votre nom")
	@Size( min=4, max=50, message = "Le nom doit avoir au moins 4 caractères" )
	@Pattern( regexp = "^[A-Za-z-]+$", message = "Merci de saisir un nom valide" )
	private String lastName;

	@Id
	@Column(name = "email")
	@NotNull(message="Veuillez saisir votre email")
	@Size( min=2, max=200, message = "Le prénom doit avoir au moins 4 caractères" )
	@Pattern( regexp = "^[A-Za-z0-9-_.]+@[A-Za-z-.]+.[A-Za-z]{2,3}$", message = "Merci de saisir un email valide" )
	private String email;

	@Column(name = "web")
	@Pattern( regexp = "^(www.[A-Za-z0-9-_.]+.[A-za-z]{2,3})?$", message = "Merci de saisir une URL valide" )
	private String web;

	@Column(name = "birthday")
	@Pattern( regexp = "^([0-9]{2}/[0-9]{2}/[0-9]{4})?$", message = "Merci de saisir une date de naissance valide" )
	private String birthday;

	@Column(name = "password")
	@NotNull(message="Veuillez saisir votre mot de passe")
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	private CV cv;

	public Person(){

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CV getCv() {
		return cv;
	}

	public void setCv(CV cv) {
		this.cv = cv;
	}

}
