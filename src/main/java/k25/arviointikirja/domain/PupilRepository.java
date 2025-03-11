package k25.arviointikirja.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PupilRepository extends CrudRepository<Pupil, Long> {
    List<Pupil> findByPupilClass_classId(Long classId); 

    
}
