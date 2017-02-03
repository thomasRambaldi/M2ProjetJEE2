package monapp;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "params", eager = false)
@ApplicationScoped
public class ApplicationParameters {

    Map<String, Nature> natures = new LinkedHashMap<>();

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

}