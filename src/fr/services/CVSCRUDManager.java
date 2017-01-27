package fr.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.beans.CV;

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
		if (cv.getId() == -1) {
            em.persist(cv);
        } else {
            cv = em.merge(cv);
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
	public void deleteActivity(CV cv) {
		cv = em.merge(cv);
		em.remove(cv);		
	}
	

}