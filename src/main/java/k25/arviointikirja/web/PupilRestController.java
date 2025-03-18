package k25.arviointikirja.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import k25.arviointikirja.domain.Pupil;
import k25.arviointikirja.domain.PupilRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PupilRestController {

    private PupilRepository pRepository;

    public PupilRestController(PupilRepository pRepository) {
        this.pRepository = pRepository;
    }

    @GetMapping("/pupils")
    public @ResponseBody List<Pupil> pupilListRest() {
        return (List<Pupil>) pRepository.findAll();
    }

    @GetMapping("/pupils/{id}")
    public @ResponseBody Optional<Pupil> findPupilRest(@PathVariable("id") Long pupilId) {
        return pRepository.findById(pupilId);
    }

    @PostMapping("/pupils")
    public @ResponseBody Pupil saveNewPupil(@RequestBody Pupil pupil) {
        return pRepository.save(pupil);
    }

}
