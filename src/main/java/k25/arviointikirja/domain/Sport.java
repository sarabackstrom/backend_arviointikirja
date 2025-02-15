package k25.arviointikirja.domain;
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
    private List<Lession> lessions;

}
