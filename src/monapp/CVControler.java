package monapp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

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
	
	public String createPersonCv(Person p){
		System.out.println(theCV.getId());
		cvm.createPersonCV(theCV, p);
		return "userAccount";
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
	
	public String createCV() {
		theCV = new CV();
		return "createCV";
	}

	public String remove(){
		cvm.deleteCV(theCV);
		return "index";
		
	}
	
	public String remove(Person p){
		CV cv = new CV();
		cv.setId(p.getCv().getId());
//		cv.setName(p.getCv().getName());
//		p.setCv(null);
		
		cvm.removePersonCV(p);
		theCV=null;
		cvm.deleteCV(cv);
		return "userAccount";
	}

	public String saveActivity() throws SQLException {
		theCV.getActivities().add(theActivity);
		save();
		return "showCV";
	}
	
	public String saveActivity(CV c, Person p) throws SQLException {
		c.getActivities().add(theActivity);
		saveUserCv(p);
		return "userCV";
	}
	
	public String saveUserCv(Person user){
		CV cv = user.getCv();
		if(cv.getActivities() != null && !theActivity.isEmpty() && ! cv.getActivities().contains(theActivity)){
			cv.getActivities().add(theActivity);
		}	
		cvm.updatePerson(cv,user);
		return "editCV";
	}
	


	public String editActivity(Integer index) {
		theActivity = theCV.getActivities().get(index);
		return "editActivity";
	}
	
	public String editActivity(CV cv,Integer index) {
		theActivity = cv.getActivities().get(index);
		return "editActivity";
	}

	public String newActivity() {
		theActivity = new Activity();
		return "createActivity";
	}
	
	public String newActivity(CV c) {
		if(c.getActivities() == null)
			c.setActivities(new ArrayList<>());
		theActivity = new Activity();
		return "editActivity";
	}

	public String removeActivity(Integer index) throws SQLException{
		theCV.getActivities().remove(index.intValue());
		cvm.updateCV(theCV);
		return "showCV";
	}
	
	public String removeActivity(Person p,Integer index) throws SQLException{
		p.getCv().getActivities().remove(index.intValue());
		theActivity = new Activity();
		saveUserCv(p);
		return "userCV";
	}

	public void getActivitiesTitle(CV cv){
		for(int i = 0 ; i < cv.getActivities().size() ; i++){
			((ArrayList<Activity>) cv.getActivities()).get(i).getTitle();
		}
	}
	
	/* Fonctions pour l'ajax*/
	public String saveTheCv(Person p){
		cvm.updatePerson(theCV,p);
		return "userCV";
	}
	
	public void removeActivityTheCv(Integer index){
		if(this.index != null && this.index.equals(index))
			theActivity=null;
		theCV.getActivities().remove(index.intValue());
		index = null;
		System.out.println("INDEX SET TO NULL");
	}
	
	private Integer index = null;
	
	public void editActivityTheCv(Integer index){
//		Activity a = theCV.getActivities().get(index.intValue());
//		a.setTitle(theActivity.getTitle());
//		a.setNature(theActivity.getNature());
//		a.setYear(theActivity.getYear());
//		a.setDescription(theActivity.getDescription());
//		a.setWeb(theActivity.getWeb());
		System.out.println(index);
		theActivity = new Activity();
		theActivity = theCV.getActivities().get(index.intValue());
		this.index=new Integer(index);
	}
	
	public void saveTheActivityTheCv(){
		if(index == null){
			theCV.getActivities().add(theActivity);
			System.out.println("NEW ACTIVTY ADDED");
		}	
		else{
			System.out.println("ACTIVTY CHANGED");
			theCV.getActivities().set(index.intValue(), theActivity);
		
		}
		index=null;
		theActivity = null;
	}
	
	public String editTheCv(Person p){
		theCV = new CV();
		theCV.setName(p.getCv().getName());
		theCV.setId(p.getCv().getId());
		theCV.setActivities(p.getCv().getActivities());
		index=null;
		theActivity = null;
		return "edittCv";
	}
	
	public void newActivityTheCv(){
		theActivity = new Activity();
		index=null;
	}
	
	public void redirect(ComponentSystemEvent event){
		if(theActivity != null || theActivity.getTitle().isEmpty()){
			FacesContext fc = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
			nav.performNavigation("hello.xhtml");
		}
	}
}
