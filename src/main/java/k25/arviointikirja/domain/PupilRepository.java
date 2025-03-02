package k25.arviointikirja.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PupilRepository extends JpaRepository<Pupil, Long> {
    List<Pupil> findByPupilClass_classId(Long classId); 

    
}
