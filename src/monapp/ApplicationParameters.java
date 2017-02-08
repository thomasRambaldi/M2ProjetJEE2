package monapp;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@ManagedBean(name = "params", eager = false)
@ApplicationScoped
public class ApplicationParameters {

    Map<String, Nature> natures = new LinkedHashMap<>();
    private Date date;
    private String search;
    
    private List<Person> resultSearchPerson = null;
    private List<CV> resultSearchCV = null;
    
    @PersistenceContext(unitName = "myData")
	private EntityManager em;
    
    @PostConstruct
    void init() {
        natures.put("EXP", Nature.EXPERIENCE);
        natures.put("FRM", Nature.FORMATION);
        natures.put("HOB", Nature.HOBBIES);
        natures.put("LANG", Nature.LANGUAGES);
        natures.put("OT", Nature.OTHER);
        System.out.println("Init " + this);
    }

    public Map<String, Nature> getNatures() {
        return natures;
    }
    
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Person> getResultSearchPerson() {
		return resultSearchPerson;
	}

	public void setResultSearchPerson(List<Person> resultSearchPerson) {
		this.resultSearchPerson = resultSearchPerson;
	}

	public List<CV> getResultSearchCV() {
		return resultSearchCV;
	}

	public void setResultSearchCV(List<CV> resultSearchCV) {
		this.resultSearchCV = resultSearchCV;
	}

	public String searchValue(){
		resultSearchPerson = searchPerson(search);
		resultSearchCV = searchCV(search);
		return "resultSearch";
	}
	
	public List<Person> searchPerson(String s) {
//		 OR p.firstName LIKE CONCAT('%',:search,'%') OR p.lastName LIKE CONCAT('%',:search,'%')"
		Query query = em.createQuery("Select p From Person p where p.firstName like '%:search%' ", Person.class);
		query.setParameter("search", s);
		query.executeUpdate();
		return query.getResultList();
	}
	
	public List<CV> searchCV(String s) {
		Query query = em.createQuery("Select c From CV c"
				+ " WHERE name LIKE CONCAT('%',:search,'%')", CV.class);  
		query.setParameter("search", s);
		query.executeUpdate();
		return query.getResultList();
	}

}