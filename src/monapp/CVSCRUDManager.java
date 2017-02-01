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
	EntityManager em;

	@Override
	public List<CV> searchCV() {
		return em.createQuery("Select c From CV c", CV.class).getResultList();
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
	public CV readCV(int idCv) {
		return em.find(CV.class, idCv);
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

}