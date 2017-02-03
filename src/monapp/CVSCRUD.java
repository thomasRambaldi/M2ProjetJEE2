package monapp;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CVSCRUD {

	public List<CV> searchCV(boolean activities);
	public CV createCV(CV cv);
	public CV readCV(int idCv, boolean activities);
	public void updateCV(CV cv, String id);
	public void deleteCV(CV cv);

	public Activity createActivity(CV cv, Activity activity);
	public void deleteActivity(CV cv, Integer id);
}
