package k25.arviointikirja.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

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
    private List<Performance> performances;

    public Pupil() {}


    
}
