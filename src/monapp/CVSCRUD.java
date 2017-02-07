package monapp;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CVSCRUD {

	public List<CV> searchCV(boolean activities);
	public CV createCV(CV cv);
	public CV readCV(int idCv, boolean activities);
	public void updateCV(CV cv);
	public void deleteCV(CV cv);
	public void createPersonCV(CV cv, Person p);
	public void updatePerson(CV cv, Person p);
	public void removePersonCV(Person p);

}
