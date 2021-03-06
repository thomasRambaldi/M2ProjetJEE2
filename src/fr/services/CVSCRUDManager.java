package fr.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.beans.Activity;
import fr.beans.CV;
import fr.beans.CVSCRUD;
import fr.beans.Person;


@Stateful(name = "act", description = "Representation d'une activitee")
public class CVSCRUDManager implements CVSCRUD{

	@PersistenceContext(unitName = "myData")
	private  EntityManager em;
	
	@Override
	public List<CV> searchCV(boolean activities) {
		List<CV> cvs = em.createQuery("Select c From CV c", CV.class).getResultList();
		if(activities)
			cvs.get(0).getActivities().size();
		return cvs;
	}

	@Override
	public CV createCV(CV cv) {
		if (cv.getId() == 0) {
			cv = em.merge(cv);
		} else {
			em.persist(cv);
		}
		return cv;
	}


	@Override
	public CV readCV(int idCv, boolean activities) {
		CV c =  em.find(CV.class, idCv);
		if(activities)
			c.getActivities().size();
		return c;
	}

	@Override
	public void updateCV(CV cv) {
		cv.getActivities().size();
		cv = em.merge(cv);
	}

	@Override
	public void deleteCV(CV cv) {
//				CV c =  em.find(CV.class, cv.getId());
//				em.remove(c);
//		if (!em.contains(cv)){
//			cv = em.merge(cv);
//		}
		cv.setActivities(new ArrayList<Activity>());
		cv = em.merge(cv);
		em.remove(cv);
//		Query query = em.createQuery("delete from CV c where c.id = :idCv");
//		query.setParameter("idCv", cv.getId());
//		query.executeUpdate();
//		em.flush();
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

	@Override
	public Person createPersonCV(CV cv, Person p) {
		p.setCv(cv);
		p=em.merge(p);
		return p;
	}

	@Override
	public void updatePerson(CV cv, Person p){
		if(cv.getActivities() != null)
			cv.getActivities().size();
		System.out.println("BEFORE SAVE Id thCV = "+cv.getId());
		p.setCv(cv);
		p=em.merge(p);
	//	em.merge(p);
	}
	
	@Override
	public void removePersonCV(Person p){
//		cv.getActivities().size();
		p.setCv(null);
		p=em.merge(p);
		Person p1 = em.find(Person.class, p.getEmail());
		p1.setCv(null);
	}


}