package controler;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import fr.beans.IPersonSCRUD;
import fr.beans.Person;
import monapp.Cryptography;

@ManagedBean(name = "person")
@SessionScoped
public class PersonControler {

	@EJB
	private IPersonSCRUD pm;

	private Person thePerson  = new Person();
	
	boolean inscription= false;
	
	@ManagedProperty(value="#{authController}")
	private AuthenticateController authController;
	//	private boolean isShowedCv = false;

	public AuthenticateController getAuthController() {
		return authController;
	}

	public void setAuthController(AuthenticateController authControler) {
		this.authController = authControler;
	}

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
		System.out.println("WEB = "+thePerson.getWeb());
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
	
	public void initInscription(ComponentSystemEvent event){
			if(authController.isLogin())
				thePerson=new Person();
	}

	public String inscription(){
		FacesContext ct = FacesContext.getCurrentInstance();
		if(pm.readPerson(thePerson.getEmail()) != null){
			FacesMessage msg = new FacesMessage("Cet email est deja pris");
			ct.addMessage("test:email", msg);
			ct.validationFailed();
			return "inscription";
		}
		if(ct.isValidationFailed())
			return "inscription";
		
		try {
			thePerson.setPassword(Cryptography.crypt(thePerson.getPassword()));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		pm.createPerson(thePerson);
		thePerson = null;
		return "hello";
	}
	
}