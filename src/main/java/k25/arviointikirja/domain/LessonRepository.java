package k25.arviointikirja.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByPupilClass_classId(Long classId);
}
