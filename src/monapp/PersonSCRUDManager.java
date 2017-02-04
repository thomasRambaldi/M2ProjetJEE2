package monapp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
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
	EntityManager em;

	@Override
	public List<Person> searchPerson() {
		//		Connection connection = em.unwrap(Connection.class);  
		//		try {
		//			DatabaseMetaData metaData = connection.getMetaData();
		//			System.out.println(metaData.getDatabaseProductName());
		//			System.out.println(metaData.getUserName());
		//			System.out.println(metaData.getURL());
		//		} catch (SQLException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

		return em.createQuery("Select p From Person p", Person.class).getResultList();
	}

	@Override
	public Person createPerson(Person p) {
		em.persist(p);
		return p;
	}

	@Override
	public Person readPerson(String email) {
		return em.find(Person.class, email);
	}

	@Override
	public void updatePerson(Person p) {
		p = em.merge(p);
//		p = em.find(Person.class, id);
//		p.setEmail(p.getEmail());
//		p.setBirthday(p.getBirthday());
//		p.setFirstName(p.getFirstName());
//		p.setLastName(p.getLastName());
//		p.setWeb(p.getWeb());
//		p.setPassword(p.getPassword());
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
