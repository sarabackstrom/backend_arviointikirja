package k25.arviointikirja.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Pupil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pupilId;
    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn (name = "classId")
    private PupilClass pupilClass;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pupil")
    private List<Performance> performances = new ArrayList<>();

    public Pupil() {}

    public Pupil(String firstName, String lastName, PupilClass pupilClass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pupilClass = pupilClass;
    }

    public Long getPupilId() {
        return pupilId;
    }

    public void setPupilId(Long pupilId) {
        this.pupilId = pupilId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PupilClass getPupilClass() {
        return pupilClass;
    }

    public void setPupilClass(PupilClass pupilClass) {
        this.pupilClass = pupilClass;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    @Override
    public String toString() {
        return "Pupil [pupilId=" + pupilId + ", firstName=" + firstName + ", lastName=" + lastName + ", pupilClass="
                + pupilClass + ", performances=" + performances + "]";
    }

    //Add Performancea varten, vain ajonaikaiseen käyttöön tietokannassa

    @Transient
    private List<Lesson> lessons = new ArrayList<>();

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

}
