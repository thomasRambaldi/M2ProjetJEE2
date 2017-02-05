package monapp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "cv")
@SessionScoped
public class CVControler {

	@EJB
	private ICVSCRUD cvm;

	private Activity theActivity = new Activity();

	private CV theCV = new CV();

	@PostConstruct
	public void init()  {
		System.out.println("Create " + this);
		CV cv1 = new CV();
		cv1.setName("THOMAS RAMBALDI");
		Activity act = new Activity();
		act.setTitle("Master 2 informatique");
		act.setNature(Nature.FORMATION);
		act.setYear(2010);
		act.setWeb("https://www.linkedin.com/home?trk=nav_responsive_tab_home");
		act.setDescription("Site effectue a partir du cahier des charges de la JAM");
		Activity act2 = new Activity();
		act2.setTitle("Candidature de stage Atos");
		act2.setNature(Nature.FORMATION);
		act2.setYear(2011);
		act2.setWeb("https://www.google.com/home?trk=nav_responsive_tab_home");
		act2.setDescription("Site de sopra steria");
		List<Activity> listActivities = new ArrayList<>();
		listActivities.add(act);
		listActivities.add(act2);
		cv1.setActivities(listActivities);
		//cvm.createCV(cv1);
	}

	public List<CV> getCVs(boolean activities) throws SQLException {
		return cvm.searchCV(activities);
	}

	public CV getTheCV() {
		return theCV ;
	}

	public Activity getTheActivity() {
		return theActivity ;
	}


	public String showCV(Integer idCv, boolean activities) {
		System.out.println(idCv);
		theCV = cvm.readCV(idCv, activities);
		return "showCV";
	}

	public String create(){
		cvm.createCV(theCV);
		return "showCV";
	}
	
	public String save() throws SQLException {
		System.out.println(theActivity.getTitle());
		cvm.updateCV(theCV);
		return "showCV";
	}

	public String newCV() {
		theCV = new CV();
		return "editCV";
	}

	public String remove(){
		cvm.deleteCV(theCV);
		return "index";
	}

	public String saveActivity() throws SQLException {
		theCV.getActivities().add(theActivity);
		save();
		return "showCV";
	}


	public String editActivity(Integer index) {
		theActivity = theCV.getActivities().get(index);
		return "editActivity";
	}

	public String newActivity() {
		theActivity = new Activity();
		return "createActivity";
	}

	public String removeActivity(Integer index) throws SQLException{
		theCV.getActivities().remove(index.intValue());
		cvm.updateCV(theCV);
		return "showCV";
	}

	public void getActivitiesTitle(CV cv){
		for(int i = 0 ; i < cv.getActivities().size() ; i++){
			((ArrayList<Activity>) cv.getActivities()).get(i).getTitle();
		}
	}
}
