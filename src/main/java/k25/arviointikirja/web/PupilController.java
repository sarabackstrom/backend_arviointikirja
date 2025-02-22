package k25.arviointikirja.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import k25.arviointikirja.domain.Pupil;
import k25.arviointikirja.domain.PupilClassRepository;
import k25.arviointikirja.domain.PupilRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PupilController {
    
    @Autowired
    private PupilRepository pupilRepository;

    @Autowired
    private PupilClassRepository pupilClassRepository;


    //Add new pupil

    @GetMapping(value = "/addPupil")
    public String addPupil(Model model){
        model.addAttribute("pupil", new Pupil());
        model.addAttribute("pupilclasses", pupilClassRepository.findAll());
        return "addpupil"; 
    }

    //Save new student, PITÄISIKÖ OLLA REDERICT
    @PostMapping("/savePupil")
    public String savePupil(@ModelAttribute Pupil pupil){
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
    
    
    
}
