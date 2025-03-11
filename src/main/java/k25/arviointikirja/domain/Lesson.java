package k25.arviointikirja.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lessonId;
    @Column(columnDefinition = "TEXT")
    private String lessonDescription;
    
    @NotNull(message = "Päivämäärä ei voi olla tyhjä. Valitse päivämäärä valikosta.")
    private LocalDate lessonDay;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "classId")
    private PupilClass pupilClass;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    private List<Performance> performances = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sportId")
    private Sport sport;

    public Lesson() {}

    public Lesson(String lessonDescription, LocalDate lessonDay, PupilClass pupilClass, Sport sport) {
        this.lessonDescription = lessonDescription;
        this.lessonDay = lessonDay;
        this.pupilClass = pupilClass;
        this.sport = sport;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public LocalDate getLessonDay() {
        return lessonDay;
    }

    public void setLessonDay(LocalDate lessonDay) {
        this.lessonDay = lessonDay;
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

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Override
    public String toString() {
        return "Lesson [lessonId=" + lessonId + ", lessonDescription=" + lessonDescription + ", lessonDay="
                + lessonDay + ", sport=" + sport + "]"; // Ei viittauksia PupilClass:iin
    }
    
  
    
}
