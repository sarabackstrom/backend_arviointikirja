package k25.arviointikirja.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import k25.arviointikirja.domain.Lesson;
import k25.arviointikirja.domain.LessonRepository;
import k25.arviointikirja.domain.Performance;
import k25.arviointikirja.domain.PerformanceCreationDto;
import k25.arviointikirja.domain.PerformanceRepository;
import k25.arviointikirja.domain.Pupil;
import k25.arviointikirja.domain.PupilClassRepository;
import k25.arviointikirja.domain.PupilRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PerformanceController {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private PupilRepository pupilRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private PupilClassRepository pupilClassRepository;

    @Autowired
    UserService uService;

    // Lähteenä käytetty sivua: https://www.baeldung.com/thymeleaf-list
    @GetMapping("/create")
    public String showPerformancesForm(@RequestParam(required = false) Long classId, Model model) {
        List<Pupil> pupils = new ArrayList<>();
        List<Lesson> lessons = new ArrayList<>();

        if (classId != null) {
            // Haetaan oppilaat ja oppitunnit valitun luokan perusteella
            pupils = pupilRepository.findByPupilClass_classId(classId);
            lessons = lessonRepository.findByPupilClass_classId(classId);
        }

        // Luodaan PerformanceCreationDto, joka sisältää suoritukset oppilaille
        PerformanceCreationDto performanceForm = new PerformanceCreationDto();

        for (Pupil pupil : pupils) {
            Performance performance = new Performance();
            performance.setPupil(pupil);
            performanceForm.addPerformance(performance);
        }

        model.addAttribute("form", performanceForm);
        model.addAttribute("pupils", pupils);
        model.addAttribute("lessons", lessons);
        model.addAttribute("pupilClasses", pupilClassRepository.findAll());
        model.addAttribute("selectedClassId", classId);

        return "addperformance";
    }

    @GetMapping("/pupilAddPerformance")
    public String pupilAddsPerformance(Model model) {
        Pupil authenticatedPupil = uService.getAuthenticatedPupil();
        model.addAttribute("authenticatedPupil", authenticatedPupil);
        model.addAttribute("lessons", lessonRepository.findAll());
        model.addAttribute("performance", new Performance());
        return "pupilAddPerformance";
    }

    /*
     * @GetMapping("/pupillist")
     * public String pupilList(Model model) {
     * model.addAttribute("pupils", pupilRepository.findAll());
     * Pupil authenticatedPupil = userService.getAuthenticatedPupil();
     * model.addAttribute("authenticatedPupil", authenticatedPupil);
     * return "pupillist";
     * }
     */

    @PostMapping("/savePupilPerformance")
    public String savePupilPerformance(@ModelAttribute Performance performance, @RequestParam Long lessonId) {
        Pupil authenticatedPupil = uService.getAuthenticatedPupil();

        if (authenticatedPupil != null) {
            performance.setPupil(authenticatedPupil);
        }

        if (performance.getPupil() == null || performance.getPupil().getPupilId() == null) {
            System.out.println("Pupil ID puuttuu!");
            return "redirect:/pupillist";
        }

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Oppitunti ei löydy"));
        performance.setLesson(lesson);

        performance.setPupilAddsPerformance(true);

        performanceRepository.save(performance);
        return "redirect:/pupillist";
    }

    @PostMapping("/savePerformances")
    public String savePerformances(@ModelAttribute PerformanceCreationDto form,
            @RequestParam Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();

        for (Performance performance : form.getPerformances()) {
            performance.setLesson(lesson);
            performanceRepository.save(performance);
        }

        return "redirect:/pupillist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editPerformance/{id}")
    public String editPerformance(@PathVariable("id") Long performanceId, Model model) {
        Optional<Performance> performance = performanceRepository.findById(performanceId);
        if (performance.isPresent()) {
            model.addAttribute("performance", performance.get()); // En osannut itse ratkaista, miksi Edit ei toiminut,
                                                                  // joten tekoäly neuvoi laittamaan optionalin, jotta
                                                                  // päivämäärä saatiin säilymään
        } else {
            return "redirect:/showPupilPerformances/{id}";
        }
        return "editPerformance";
    }

    @GetMapping("/showPupilPerformances/{id}")
    public String showPupilPerformances(@PathVariable("id") Long pupilId, Model model) {
        Optional<Pupil> pupil = pupilRepository.findById(pupilId);

        if (pupil.isPresent()) {
            List<Performance> performances = performanceRepository.findByPupil(pupil.get());

            // taidot-keskiarvo ope
            double averageSkills = performances.stream()
                    .filter(performance -> !performance.isPupilAddsPerformance())
                    .mapToDouble(Performance::getSkills)
                    .filter(skill -> skill > 0)
                    .average()
                    .orElse(0.0);

            // työskentely-keskiarvo ope
            double averageEffort = performances.stream()
                    .filter(performance -> !performance.isPupilAddsPerformance())
                    .mapToDouble(Performance::getEffort)
                    .filter(effort -> effort > 0)
                    .average()
                    .orElse(0.0);

            /*työskentely ja taidot yhdistetty keskiarvo painoarvolla 50% työskentely ja 50% taidot ope */
            double averageEffortAndSkills = (averageEffort + averageSkills) / 2;

              // taidot-keskiarvo oppilas
              double averageSkills2 = performances.stream()
              .filter(performance -> performance.isPupilAddsPerformance())
              .mapToDouble(Performance::getSkills)
              .filter(skill -> skill > 0)
              .average()
              .orElse(0.0);

      // työskentely-keskiarvo oppilas
      double averageEffort2 = performances.stream()
              .filter(performance -> performance.isPupilAddsPerformance())
              .mapToDouble(Performance::getEffort)
              .filter(effort -> effort > 0)
              .average()
              .orElse(0.0);

      /*työskentely ja taidot yhdistetty keskiarvo painoarvolla 50% työskentely ja 50% taidot oppilas */
      double averageEffortAndSkills2 = (averageEffort2 + averageSkills2) / 2;

            model.addAttribute("performances", performances);
            model.addAttribute("pupil", pupil.get());
            model.addAttribute("averageSkills", averageSkills);
            model.addAttribute("averageEffort", averageEffort);
            model.addAttribute("averageEffortAndSkills", averageEffortAndSkills);
            model.addAttribute("averageSkills2", averageSkills2);
            model.addAttribute("averageEffort2", averageEffort2);
            model.addAttribute("averageEffortAndSkills2", averageEffortAndSkills2);

        } else {
            model.addAttribute("performances", new ArrayList<Performance>());
        }

        return "pupilperformances";
    }

    @PostMapping("/saveEditedPerformance")
    public String saveEditedPerformance(@ModelAttribute Performance performance) {
        performanceRepository.save(performance);
        return "redirect:/showPupilPerformances/" + performance.getPupil().getPupilId();
    }

}
