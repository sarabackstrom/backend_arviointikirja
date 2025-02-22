package k25.arviointikirja.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import k25.arviointikirja.domain.Lesson;
import k25.arviointikirja.domain.LessonRepository;
import k25.arviointikirja.domain.PupilClassRepository;
import k25.arviointikirja.domain.SportRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
public class LessionController {

    @Autowired
    private LessonRepository lessionRepository;

    @Autowired
    private PupilClassRepository pupilClassRepository;

    @Autowired
    private SportRepository sportRepository;

//show all lessons
@GetMapping("/lessons")
public String lessonlist(Model model) {
    model.addAttribute("lessons", lessionRepository.findAll());
    return "lessonlist";
}
    

//add lesson
@GetMapping("/addLesson")
public String addLesson(Model model) {
    model.addAttribute("lesson", new Lesson());
    model.addAttribute("pupilclasses", pupilClassRepository.findAll());
    model.addAttribute("sports", sportRepository.findAll());
    return "addlesson";
}

//save lesson
@PostMapping("/saveLesson")
public String saveLesson(@ModelAttribute Lesson lesson) {
    lessionRepository.save(lesson);
    return "redirect:lessons";
}


}
