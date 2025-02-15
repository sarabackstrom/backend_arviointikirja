package k25.arviointikirja.domain;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pupilClass")
    private List<Pupil> pupils;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teachingGroup")
    private List<Lession> lessions;
}
