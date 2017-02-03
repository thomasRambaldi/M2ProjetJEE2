package monapp;

import java.util.List;

import javax.ejb.Stateful;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.UIDefaults.ActiveValue;


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
	public void updateCV(CV cv, String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteCV(CV cv) {
		cv = em.merge(cv);
		em.remove(cv);		
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
	public Activity createActivity(CV cv, Activity activity){
		cv.getActivities().add(activity);
		System.out.println("------->");
		System.out.println(activity.getTitle());
		System.out.println(activity.getDescription());
		System.out.println(activity.getWeb());
		System.out.println(activity.getYear());
		System.out.println(activity.getNature());
		return activity;
	}

	@Override
	public void deleteActivity(CV cv, Integer id) {
		cv = em.merge(cv);
		
		for(int i = 0 ; i < cv.getActivities().size() ; i++){
			if(cv.getActivities().get(i).getId() == id)
				cv.getActivities().remove(i).getId();
		}
	}

}