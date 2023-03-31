package jobaskronom.demo.jobs;

import jobaskronom.demo.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class CompanyJob {

    private final CompanyService companyService;


    @Scheduled(cron ="${crontab}")
    public void updateCompaniesWithDaData() {
        companyService.updateAllExistingCompaniesWithDaData();
    }
}
