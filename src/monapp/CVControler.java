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
	private CVSCRUD cvm;

	private Activity theActivity = new Activity();

	private CV theCV = new CV();

	@PostConstruct
	public void init()  {
		System.out.println("Create " + this);
		CV cv1 = new CV();
		cv1.setName("THOMAS RAMBALDI");
		Activity act = new Activity();
		act.setId(1);
		act.setTitle("Master 2 informatique");
		act.setNature(Nature.FORMATION);
		act.setYear(2010);
		act.setWeb("https://www.linkedin.com/home?trk=nav_responsive_tab_home");
		act.setDescription("Site effectue partir du cahier des charges de la JAM");
		Activity act2 = new Activity();
		act2.setId(2);
		act2.setTitle("Candidature de stage Atos");
		act2.setNature(Nature.FORMATION);
		act2.setYear(2011);
		act2.setWeb("https://www.google.com/home?trk=nav_responsive_tab_home");
		act2.setDescription("Site de sopra steria");
		List<Activity> listActivities = new ArrayList<>();
		listActivities.add(act);
		listActivities.add(act2);
		cv1.setActivities(listActivities);
		cvm.createCV(cv1);
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
		theCV = cvm.readCV(idCv, activities);
		return "showCV";
	}


	public String save() throws SQLException {
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
		System.out.println("------->" + theActivity);
		theCV.getActivities().add(theActivity);
		save();
		return "showCV";
	}


	public String editActivity(Integer index) {
		System.out.println("************>" + index);
		for(int i = 0 ; i < theCV.getActivities().size() ; i++){
			if( theCV.getActivities().get(i).getId() == index){
				theActivity = theCV.getActivities().get(i);
				cvm.updateActivity(theCV, theActivity, index);
			}
		}
		return "editActivity";
	}
	
	public String newActivity() {
		theActivity = new Activity();
		theActivity.setId( theCV.getActivities().size() + 1 );
		return "editActivity";
	}

	public String removeActivity(Integer id){
		System.out.println(id);
		cvm.deleteActivity(theCV, id);
		return "cvs";
	}

	public void getActivitiesTitle(CV cv){
		for(int i = 0 ; i < cv.getActivities().size() ; i++){
			((ArrayList<Activity>) cv.getActivities()).get(i).getTitle();
		}
	}
}
