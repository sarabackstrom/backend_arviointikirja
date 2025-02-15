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
    @JoinColumn(name = "lessionId")
    private Lession lession;

    @ManyToOne
    @JoinColumn(name = "pupilId")
    private Pupil pupil;

}
