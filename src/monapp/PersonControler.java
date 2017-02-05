package monapp;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "person")
@SessionScoped
public class PersonControler {

	@EJB
	private IPersonSCRUD pm;

	private Person thePerson  = new Person();

	@PostConstruct
	public void init()  {
		System.out.println("Create " + this);
		Person p1 = new Person();
		p1.setFirstName("Martin");
        p1.setLastName("Langevin");
        p1.setEmail("test@test.fr");
        try {
			p1.setPassword( Cryptography.crypt("azerty"));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        p1.setBirthday("28/03/1992");
        p1.setWeb("www.google.fr");
        CV cv1 = new CV();
        cv1.setId(0);
		cv1.setName("Candidature de stage");
		Activity act = new Activity();
		act.setTitle("Master 2 informatique");
		act.setNature(Nature.FORMATION);
		act.setYear(2010);
		act.setWeb("https://www.linkedin.com/home?trk=nav_responsive_tab_home");
		act.setDescription("Site effectue a partir du cahier des charges de la JAM");
		Activity act2 = new Activity();
		act2.setTitle("Candidature de stage Atos");
		act2.setNature(Nature.FORMATION);
		act2.setYear(2011);
		act2.setWeb("https://www.google.com/home?trk=nav_responsive_tab_home");
		act2.setDescription("Site de sopra steria");
		List<Activity> listActivities = new ArrayList<>();
		listActivities.add(act);
		listActivities.add(act2);
		cv1.setActivities(listActivities);
		p1.setCv(cv1);
        pm.createPerson(p1);
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
		
		try {
			thePerson.setPassword(Cryptography.crypt(thePerson.getPassword()));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		pm.createPerson(thePerson);
		return "hello";
	}
}