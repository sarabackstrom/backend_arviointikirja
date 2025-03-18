package k25.arviointikirja.web;

import java.util.List;

import org.springframework.stereotype.Controller;

import k25.arviointikirja.domain.Lesson;
import k25.arviointikirja.domain.LessonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LessonRestController {

    private LessonRepository lRepository;

    public LessonRestController(LessonRepository lRepository) {
        this.lRepository = lRepository;
    }

    @GetMapping("/lessons")
    public @ResponseBody List<Lesson> lessonListRest() {
        return (List<Lesson>) lRepository.findAll();
    }

}
