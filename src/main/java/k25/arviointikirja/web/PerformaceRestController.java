package k25.arviointikirja.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import k25.arviointikirja.domain.Performance;
import k25.arviointikirja.domain.PerformanceRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class PerformaceRestController {

    private PerformanceRepository pRepository;

    public PerformaceRestController(PerformanceRepository pRepository) {
        this.pRepository = pRepository;
    }


    @GetMapping("/performances")
    public @ResponseBody List<Performance> performaceListRest() {
        return (List<Performance>) pRepository.findAll();
    }

    //Find pupil's performances by pupilId
    @GetMapping("/performances/pupil/{id}")
    public @ResponseBody List<Performance> getPupilsPerformances(@PathVariable Long id) {
        return pRepository.findByPupilPupilId(id);
    }

    //Delete Performance
    @DeleteMapping("/performances/{id}")
    public Iterable<Performance> deletePerformance(@PathVariable Long id){
        pRepository.deleteById(id);
        return pRepository.findAll();
    }

    //Edit Performance
    @PutMapping("performances/{id}")
   Performance editPerformance (@RequestBody Performance editedPerformance, @PathVariable Long id) {
        editedPerformance.setPerformanceId(id);
        return pRepository.save(editedPerformance);
    }

}
