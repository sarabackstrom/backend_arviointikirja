package k25.arviointikirja.domain;

import java.util.ArrayList;
import java.util.List;

public class PerformanceCreationDto {

    private List<Performance> performances;

    public PerformanceCreationDto(){
        this.performances = new ArrayList<>();
    }

    public void addPerformance(Performance performance){
        this.performances.add(performance);
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }


}
