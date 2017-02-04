package monapp;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "person")
@SessionScoped
public class PersonControler {

	@EJB
	private PersonSCRUD pm;

	private Person thePerson  = new Person();

	@PostConstruct
	public void init()  {
		System.out.println("Create " + this);
	}

	public List<Person> getPersons() throws SQLException {
		return pm.searchPerson();
	}

	public Person getThePerson() {
		return thePerson ;
	}

    
	public String show(String email) {
		thePerson  = pm.readPerson(email);
		return "showPerson";
	}

	public String remove(){
		pm.deletePerson(thePerson);
		return "index";
	}

	public String newPerson() {
		thePerson = new Person();
		return "editPerson";
	}

	public String inscription(){
		FacesContext ct = FacesContext.getCurrentInstance();
		if(pm.readPerson(thePerson.getEmail()) != null){
			FacesMessage msg = new FacesMessage("Cet email est déjà pris");
			ct.addMessage("test:email", msg);
			ct.validationFailed();
			return "inscription";
		}
		if(ct.isValidationFailed())
			return "inscription";
		pm.createPerson(thePerson);
		return "hello";
	}
}