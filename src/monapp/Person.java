package monapp;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(schema="projetjee2")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "firstName")
	@NotNull
	@Pattern(regexp="^([a-zA-Z-_]*$")
	private String firstName;

	@Column(name = "lastName")
	@Pattern(regexp="^([a-zA-Z-_]*$")
	@NotNull
	private String lastName;
	
	@Id
	@Column(name = "email")
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-_]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message="Adresse mail invalide : exemple@exemple.com")
	private String email;
	
	@Column(name = "web")
//	@Pattern(regexp="^([a-zA-Z0-9.-][a-zA-Z0-9.-]*.[a-zA-Z])?$", message="Site web invalide : exemple.com")
	private String web;
	
	@Column(name = "birthday")
	private String birthday;
	
	@NotNull
	@Size(min=6)
	@Column(name = "password")
	private String password;
	
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
}
