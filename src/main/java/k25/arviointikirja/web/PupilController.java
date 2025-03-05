package k25.arviointikirja.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import k25.arviointikirja.domain.Performance;
import k25.arviointikirja.domain.PerformanceRepository;
import k25.arviointikirja.domain.Pupil;
import k25.arviointikirja.domain.PupilClassRepository;
import k25.arviointikirja.domain.PupilRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;


@Controller
public class PupilController {
    
    @Autowired
    private PupilRepository pupilRepository;

    @Autowired
    private PupilClassRepository pupilClassRepository;

@Autowired
private PerformanceRepository performanceRepository;

    //Add new pupil
    @GetMapping(value = "/addPupil")
    public String addPupil(Model model){
        model.addAttribute("pupil", new Pupil());
        model.addAttribute("pupilclasses", pupilClassRepository.findAll());
        return "addpupil"; 
    }

    //Save new student, PITÄISIKÖ OLLA REDERICT
    @PostMapping("/savePupil")
    public String savePupil(@Valid @ModelAttribute Pupil pupil, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("pupilclasses", pupilClassRepository.findAll()); 
            return "addpupil";
        }
        pupilRepository.save(pupil);
        return "redirect:pupillist";
    }

    //Show all pupils
    @GetMapping("/pupillist")
    public String pupilList(Model model) {
        model.addAttribute("pupils", pupilRepository.findAll());
        return "pupillist";
    }

    //Delete pupil
    @GetMapping("/delete/{id}")
    public String deletePupil(@PathVariable("id") Long pupilId, Model model) {
        pupilRepository.deleteById(pupilId);
        return "redirect:../pupillist";
    }
    
    //show performance by pupilId
/*@GetMapping("/showPupilPerformances/{pupilId}")
public String showPerformances(@PathVariable Long pupilId, Model model) {
    List<Performance> performances = performanceRepository.findByPupilPupilId(pupilId);
    model.addAttribute("performances", performances);
    return "pupilperformances";
}*/

    
}
