package k25.arviointikirja.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Long> {

    List<Lesson> findByPupilClass_classId(Long classId);
}
