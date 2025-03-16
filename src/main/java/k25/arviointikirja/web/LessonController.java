package k25.arviointikirja.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import k25.arviointikirja.domain.Lesson;
import k25.arviointikirja.domain.LessonRepository;
import k25.arviointikirja.domain.Pupil;
import k25.arviointikirja.domain.PupilClassRepository;
import k25.arviointikirja.domain.SportRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;






@Controller
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private PupilClassRepository pupilClassRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    UserService userService;

//show all lessons
@GetMapping("/lessonlist")
public String lessonlist(Model model) {
    model.addAttribute("lessons", lessonRepository.findAll());
    Pupil authenticatedPupil = userService.getAuthenticatedPupil();
        model.addAttribute("authenticatedPupil", authenticatedPupil);
    return "lessonlist";
}
    

@PreAuthorize("hasAuthority('ADMIN')")
//add lesson
@GetMapping("/addLesson")
public String addLesson(Model model) {
    model.addAttribute("lesson", new Lesson());
    model.addAttribute("pupilclasses", pupilClassRepository.findAll());
    model.addAttribute("sports", sportRepository.findAll());
    return "addLesson";
}

//save lesson
@PostMapping("/saveLesson")
public String saveLesson(@Valid @ModelAttribute Lesson lesson, BindingResult bindingResult, Model model) {
    if(bindingResult.hasErrors()){
        model.addAttribute("pupilclasses", pupilClassRepository.findAll());
        model.addAttribute("sports", sportRepository.findAll());
        return "addLesson";
    }
    lessonRepository.save(lesson);
    return "redirect:lessonlist";
}

@PostMapping("/saveEditedLesson")
public String saveEditedLesson(@ModelAttribute Lesson lesson) {
    lessonRepository.save(lesson);
    return "redirect:lessonlist";
}

@PreAuthorize("hasAuthority('ADMIN')")
@GetMapping("/editLesson/{id}")
public String editLesson(@PathVariable("id") Long id, Model model) {
    Optional<Lesson> lesson = lessonRepository.findById(id);
    if (lesson.isPresent()) {
        model.addAttribute("lesson", lesson.get()); // En osannut itse ratkaista, miksi Edit ei toiminut, joten tekoäly neuvoi laittamaan optionalin, jotta päivämäärä saatiin säilymään
    } else {
        return "redirect:/lessonlist"; // Jos ei löydy, palataan listaan
    }
    model.addAttribute("pupilclasses", pupilClassRepository.findAll());
    model.addAttribute("sports", sportRepository.findAll());
    return "editlesson";
}

/*Jos oppitunti poistetaan niin suoritukset katoaa, ei voi poistaa!
@GetMapping("/deleteLesson/{id}")
public String deleteLesson(@PathVariable("id") Long LessonId, Model model) {
    lessonRepository.deleteById(LessonId);
    return "redirect:/lessonlist";
}*/





}
