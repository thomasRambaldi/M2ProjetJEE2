package monapp;

import java.util.List;

import javax.ejb.Stateful;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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
		em.merge(cv);
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
	public void deleteActivity(CV cv, Integer id) {
		cv = em.merge(cv);
		
		for(int i = 0 ; i < cv.getActivities().size() ; i++){
			if(cv.getActivities().get(i).getId() == id)
				cv.getActivities().remove(i).getId();
		}
	}
	
	@Override
	public void updateActivity(CV cv, Activity activity, Integer oldId){
		System.out.println("____________> " + activity.getTitle());
		for(int i = 0 ; i < cv.getActivities().size() ; i++){
			if(cv.getActivities().get(i).getId() == oldId){
//				Activity act = em.merge(activity);
				activity.setId(activity.getId());
				activity.setNature(activity.getNature());
				activity.setDescription(activity.getDescription());
				activity.setWeb(activity.getWeb());
				activity.setYear(activity.getYear());
			}
		}
	}

}