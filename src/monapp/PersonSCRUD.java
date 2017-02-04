package monapp;

import java.util.List;

import javax.ejb.Local;

@Local
public interface PersonSCRUD {
	public List<Person> searchPerson()  ;
	public Person createPerson(Person p);
	public Person readPerson(String email);
	public void updatePerson(Person p);
	public void deletePerson(Person p);
}
