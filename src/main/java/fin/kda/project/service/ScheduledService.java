package fin.kda.project.service;

import fin.kda.project.controller.FinController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledService {

    private final FinController finController;

    @Autowired
    public ScheduledService(FinController finController) {
        this.finController = finController;
    }

    @Scheduled(cron = "0 0 20 * * ?")
    public void scheduleTaskEvening() throws Exception {
        finController.saveValuteCurrencies();
    }

    @Scheduled(cron = "0 0 10 * * ?")
    public void scheduleTaskMorning() throws Exception {
        finController.saveValuteCurrencies();
    }
}
