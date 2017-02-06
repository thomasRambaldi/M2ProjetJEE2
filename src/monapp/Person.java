package monapp;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema="projetjee2")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "firstName")
	@NotNull
	private String firstName;

	@Column(name = "lastName")
	@NotNull
	private String lastName;

	@Id
	@Column(name = "email")
	private String email;

	@Column(name = "web")
	private String web;

	@Column(name = "birthday")
	private String birthday;

	@NotNull
	@Column(name = "password")
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
