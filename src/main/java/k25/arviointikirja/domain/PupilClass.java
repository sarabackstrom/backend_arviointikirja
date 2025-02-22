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
public class PupilClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long classId;
    private int startYear;
    private String classCode;
    private String className;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pupilClass")
    private List<Pupil> pupils = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pupilClass")
    private List<Lesson> lessons = new ArrayList<>();

    public PupilClass() {}

    public PupilClass(int startYear, String classCode, String className) {
        this.startYear = startYear;
        this.classCode = classCode;
        this.className = className;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(List<Pupil> pupils) {
        this.pupils = pupils;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "PupilClass [classId=" + classId + ", startYear=" + startYear + ", classCode=" + classCode
                + ", className=" + className + "]"; // Ei viittauksia lessons ja pupils
    }
    

    
}
