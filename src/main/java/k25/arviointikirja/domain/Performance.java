package k25.arviointikirja.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long performanceId;
    private int skills;
    private int effort;
    private String shortDescription;
    private boolean attendance;

    @ManyToOne
    @JoinColumn(name = "lessonId")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "pupilId")
    private Pupil pupil;

    public Performance() {}

    public Performance(int skills, int effort, String shortDescription, boolean attendance, Lesson lesson,
            Pupil pupil) {
        this.skills = skills;
        this.effort = effort;
        this.shortDescription = shortDescription;
        this.attendance = attendance;
        this.lesson = lesson;
        this.pupil = pupil;
    }

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
    }

    public int getSkills() {
        return skills;
    }

    public void setSkills(int skills) {
        this.skills = skills;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Pupil getPupil() {
        return pupil;
    }

    public void setPupil(Pupil pupil) {
        this.pupil = pupil;
    }

    @Override
    public String toString() {
        return "Performance [performanceId=" + performanceId + ", skills=" + skills + ", effort=" + effort
                + ", shortDescription=" + shortDescription + ", attendance=" + attendance + ", lesson=" + lesson
                + ", pupil=" + pupil + "]";
    }


}
