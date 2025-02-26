package k25.arviointikirja.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import k25.arviointikirja.domain.LessonRepository;
import k25.arviointikirja.domain.Performance;
import k25.arviointikirja.domain.PerformanceRepository;
import k25.arviointikirja.domain.Pupil;
import k25.arviointikirja.domain.PupilClassRepository;
import k25.arviointikirja.domain.PupilRepository;
import k25.arviointikirja.domain.SportRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    @Autowired
    private SportRepository sportRepository;

    //add performance
    @GetMapping("/addPerformance")
    public String addPerformance(Model model) {
        model.addAttribute("performance", new Performance());
        model.addAttribute("lessons", lessonRepository.findAll());
        model.addAttribute("pupils", pupilRepository.findAll());
        model.addAttribute("pupilClasses", pupilClassRepository.findAll());
        return "addperformance";
    }

    

}
