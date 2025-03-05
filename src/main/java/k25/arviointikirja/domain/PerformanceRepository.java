package k25.arviointikirja.domain;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PerformanceRepository extends CrudRepository<Performance, Long> {
    List<Performance> findByPupilPupilId(Long pupilId);
    List<Performance> findByPupil(Pupil pupil);
}
