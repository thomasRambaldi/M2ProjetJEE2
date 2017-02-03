package monapp;

import java.util.List;

import javax.ejb.Stateful;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful(name = "pers", description = "Representation d'une personne")
//@TransactionManagement(TransactionManagementType.CONTAINER)
//@Interceptors({AuthenticateManager.class})
public class PersonSCRUDManager implements PersonSCRUD{

	@PersistenceContext(unitName = "myData")
	private EntityManager em;

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
		p = em.find(Person.class, id);
		p.setEmail(p.getEmail());
		p.setBirthday(p.getBirthday());
		p.setFirstName(p.getFirstName());
		p.setLastName(p.getLastName());
		p.setWeb(p.getWeb());
		p.setPassword(p.getPassword());
	}

	@Override
	public void deletePerson(Person p) {
		p = em.merge(p);
		em.remove(p);
	}	
	
	@AroundInvoke
	public Object interceptor(InvocationContext context) throws Exception {
	   String methodName = context.getMethod().getName();
	   System.err.println("appel de " + methodName);
	   for (Object param : context.getParameters()) {
	      System.err.println("param = " + param.toString());
	   }
	   return context.proceed();
	}
}
