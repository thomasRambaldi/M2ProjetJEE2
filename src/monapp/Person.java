package monapp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Entity
@Table(schema="projetjee2")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "firstName")
	@NotNull(message="Veuillez saisir votre pr�nom")
	@Size( min=4, max=50, message = "Le pr�nom doit avoir au moins 4 caract�res" )
	@Pattern( regexp = "^[A-Za-z-]+$", message = "Merci de saisir un pr�nom valide" )
	private String firstName;

	@Column(name = "lastName")
	@NotNull(message="Veuillez saisir votre nom")
	@Size( min=4, max=50, message = "Le nom doit avoir au moins 4 caract�res" )
	@Pattern( regexp = "^[A-Za-z-]+$", message = "Merci de saisir un nom valide" )
	private String lastName;

	@Id
	@Column(name = "email")
	@NotNull(message="Veuillez saisir votre email")
	@Size( min=2, max=200, message = "Le prenom doit avoir au moins 4 caracteres" )
	@Pattern( regexp = "^[A-Za-z0-9-_.]+@[A-Za-z-.]+.[A-Za-z]{2,3}$", message = "Merci de saisir un email valide" )
	private String email;

	@Column(name = "web")
	@Pattern( regexp = "^([A-Za-z0-9-_.]+.[A-za-z]{2,3})?$", message = "Merci de saisir une URL valide" )
	private String web;

	@Column(name = "birthday")
//	@Pattern( regexp = "^([0-9]{2}/[0-9]{2}/[0-9]{4})?$", message = "Merci de saisir une date de naissance valide" )
	private Date birthday;
	
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
 
	
//	@Column(name = "birthday")
//	private Date birthday;
//	<h:outputText value="Date de naissance" />
//	<p:calendar id="birthday" value="#{c.birthday}"
//		pattern="dd/MM/yyyy">
//		<f:convertDateTime pattern="dd/MM/yyyy" />
//		<f:ajax event="blur" render="birthdayText" />
//	</p:calendar>
//	<h:message id="birthdayText" for="birthday" style="color:red" />
//	<p:dialog modal="true" resizable="false" header="Values"
//	widgetVar="dlg" showEffect="fold">
//	<p:panelGrid id="display" columns="2" columnClasses="label,value">
//		<h:outputText value="#{p.birthday}">
//			<f:convertDateTime pattern="MM/dd/yyyy" />
//		</h:outputText>
//	</p:panelGrid>
//</p:dialog>

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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
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
