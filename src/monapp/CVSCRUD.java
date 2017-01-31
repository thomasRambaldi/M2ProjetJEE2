package monapp;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CVSCRUD {

	public List<CV> searchCV();
	public CV createCV(CV cv);
	public CV readCV(int idCv);
	public void updateCV(CV cv, String id);
	public void deleteActivity(CV cv);
}
