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
}
