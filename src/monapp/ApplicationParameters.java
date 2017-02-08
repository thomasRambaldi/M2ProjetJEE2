package monapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "params", eager = false)
@ApplicationScoped
public class ApplicationParameters {

    Map<String, Nature> natures = new LinkedHashMap<>();
    private Date date;
    
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
    
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}