package monapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		resultSearchPerson = new ArrayList<Person>(searchPerson(search));
		resultSearchCV = new ArrayList<CV>(searchCV(search));
		if(resultSearchPerson.size() == 0)
			resultSearchPerson = null;
		if(resultSearchCV.size() == 0)
			resultSearchCV = null;
		return "resultSearch";
	}

	public List<Person> searchPerson(String s) {
		TypedQuery<Person> query = em.createQuery(
				"SELECT p FROM Person p WHERE p.firstName LIKE :search OR p.lastName LIKE :search OR CONCAT(p.firstName,' ',p.lastName) LIKE :search", Person.class);
		return query.setParameter("search", "%"+s+"%").getResultList();
	}

	public List<CV> searchCV(String s) {
		TypedQuery<CV> query = em.createQuery(
				"SELECT c FROM CV c WHERE c.name LIKE :search", CV.class);
		return query.setParameter("search", "%"+s+"%").getResultList();
	}
	
}