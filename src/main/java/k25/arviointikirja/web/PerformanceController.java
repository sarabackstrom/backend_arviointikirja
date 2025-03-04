package k25.arviointikirja.web;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import k25.arviointikirja.domain.Lesson;
import k25.arviointikirja.domain.LessonRepository;
import k25.arviointikirja.domain.Performance;
import k25.arviointikirja.domain.PerformanceCreationDto;
import k25.arviointikirja.domain.PerformanceRepository;
import k25.arviointikirja.domain.Pupil;
import k25.arviointikirja.domain.PupilClass;
import k25.arviointikirja.domain.PupilClassRepository;
import k25.arviointikirja.domain.PupilRepository;
import k25.arviointikirja.domain.PupilsCreationDto;
import k25.arviointikirja.domain.SportRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
public class PerformanceController {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private PupilClassRepository pupilClassRepository;

    @Autowired
    private PupilRepository pupilRepository;

    @Autowired
    private LessonRepository lessonRepository;


    //add performance
    @GetMapping("/create")
    public String showPerformancesForm(@RequestParam(required = false) Long pupilId, Model model) {

        
        List<Pupil> pupils = pupilRepository.findAll();
        
        // Luo PerformanceCreationDto, joka sisältää kaikki suoritukset
        PerformanceCreationDto performanceForm = new PerformanceCreationDto();
    
        // Luodaan tyhjät suoritukset, joihin lisätään oppilaan id
        for (Pupil pupil : pupils) {
            Performance performance = new Performance();
            List<Lesson> lessons = lessonRepository.findByPupilClass_classId(pupil.getPupilClass().getClassId());
            pupil.setLessons(lessons);
            performance.setPupil(pupil);
            performanceForm.addPerformance(performance); // Lisätään suoritus PerformanceCreationDto:n
        }
    
        // Lisätään PerformanceCreationDto malliin --> apuna käytetty: https://www.baeldung.com/thymeleaf-list
        model.addAttribute("form", performanceForm);
        model.addAttribute("pupils", pupils);
        
        return "addperformance";
    }
    

  @PostMapping("/savePerformances")
  public String savePerformances(@ModelAttribute PerformanceCreationDto form, Model model) {
      performanceRepository.saveAll(form.getPerformances());
      model.addAttribute("performances", performanceRepository.findAll());
      model.addAttribute("pupils", pupilRepository.findAll());
      return "redirect:/pupillist";
  }
  

}
