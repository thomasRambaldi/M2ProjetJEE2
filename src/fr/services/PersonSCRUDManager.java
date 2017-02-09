package fr.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.beans.IPersonSCRUD;
import fr.beans.Person;

@Stateful(name = "pers", description = "Representation d'une personne")
//@TransactionManagement(TransactionManagementType.CONTAINER)
//@Interceptors({AuthenticateManager.class})
public class PersonSCRUDManager implements IPersonSCRUD{

	@PersistenceContext(unitName = "myData")
	private EntityManager em;
	
	

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
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = null;
//		try {
//			date = dateFormat.parse(p.getBirthday());
//			p.setBirthday( dateFormat.format(date) );
//		} catch (Exception e) {
//			System.err.println("Format de date invalide. Usage : dd/MM/YYYY");
//			System.err.println(e.getMessage());
//		}
		if(readPerson(p.getEmail()) == null){
			em.persist(p);
		}else{
			em.merge(p);
		}
		return p;
	}

	@Override
	public Person readPerson(String email) {
		Person p = em.find(Person.class, email);
		if(p == null)
			return p;
		if(p.getCv() != null){
			p.getCv().getName();
			p.getCv().getId();
			if(p.getCv().getActivities() != null)
				p.getCv().getActivities().size();
		}
		return p;
	}

	@Override
	public void updatePerson(Person p) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = null;
//		try {
//			date = dateFormat.parse(p.getBirthday());
//			p.setBirthday( dateFormat.format(date) );
//		} catch (Exception e) {
//			System.err.println("Format de date invalide. Usage : dd/MM/YYYY");
//			System.err.println(e.getMessage());
//		}
		p = em.merge(p);
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
