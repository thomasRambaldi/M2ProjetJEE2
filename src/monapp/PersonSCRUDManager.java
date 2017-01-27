package monapp;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "pers", description = "Representation d'une personne")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class PersonSCRUDManager implements PersonSCRUD{

	@PersistenceContext(unitName = "myData")
	EntityManager em;

	@Override
	public List<Person> searchPerson() {
        return em.createQuery("Select p From Person p", Person.class).getResultList();
	}

	@Override
	public Person createPerson(Person p) {
		if (p.getEmail() == null) {
            em.persist(p);
        } else {
            p = em.merge(p);
        }
        return p;
	}

	@Override
	public Person readPerson(String email) {
		return em.find(Person.class, email);
	}
	
	@Override
	public void updatePerson(Person p, String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(Person p) {
		p = em.merge(p);
		em.remove(p);
	}	
}
