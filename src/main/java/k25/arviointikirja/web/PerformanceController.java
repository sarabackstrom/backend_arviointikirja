package k25.arviointikirja.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import k25.arviointikirja.domain.LessonRepository;
import k25.arviointikirja.domain.PerformanceRepository;
import k25.arviointikirja.domain.PupilClassRepository;
import k25.arviointikirja.domain.PupilRepository;

@Controller
public class PerformanceController {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private PupilClassRepository pupilClassRepository;

    @Autowired
    private PupilRepository pupilRepository;

    @Autowired
    private LessonRepository lessionRepository;

}
