package k25.arviointikirja.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Lession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lessionId;
    @Column(columnDefinition = "TEXT")
    private String lessionDescription;

    @ManyToOne
    @JoinColumn(name = "classId")
    private PupilClass teachingGroup;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lession")
    private List<Performance> performances;

    @ManyToOne
    @JoinColumn(name = "sportId")
    private Sport sport;
}
