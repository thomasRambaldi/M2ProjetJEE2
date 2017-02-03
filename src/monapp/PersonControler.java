package monapp;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "person")
@SessionScoped
public class PersonControler {

	@EJB
	private PersonSCRUD pm;

	private Person thePerson  = new Person();

    @PostConstruct
    public void init()  {
        System.out.println("Create " + this);
        if (pm.searchPerson().size() == 0) {
            Person p1 = new Person();
            p1.setEmail("test@test.fr");
            p1.setFirstName("Martin");
            p1.setLastName("Langevin");
            p1.setPassword("azerty");
        	try {
    			p1.crypterPWD();
    		} catch (UnsupportedEncodingException e) {
    			e.printStackTrace();
    		} catch (NoSuchAlgorithmException e) {
    			e.printStackTrace();
    		}
            p1.setBirthday("28/03/1992");
            p1.setWeb("www.google.fr");
            pm.createPerson(p1);
        }
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

    public String save() throws SQLException {
    	try {
			thePerson.crypterPWD();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	pm.createPerson(thePerson);
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

}