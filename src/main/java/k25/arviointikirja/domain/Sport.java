package k25.arviointikirja.domain;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sportId;
    private String name;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "sport")
    private List<Lesson> lessons = new ArrayList<>();

    public Sport() {}

    public Sport(String name) {
        this.name = name;
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
public String toString() {
    return "Sport [sportId=" + sportId + ", name=" + name + "]"; // Ei viittauksia Lesson-olioihin
}


}
