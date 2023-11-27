package fin.kda.project.controller;

import fin.kda.project.dto.ReportDto;
import fin.kda.project.dto.course.Course;
import fin.kda.project.dto.financeOperation.FinanceOperationCreateDto;
import fin.kda.project.dto.financeOperation.FinanceOperationReportDto;
import fin.kda.project.entity.FinanceEntity;
import fin.kda.project.service.CentralRussianBankService;
import fin.kda.project.service.FinService;
//import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FinController {

    private final FinService finService;

    private final CentralRussianBankService centralRussianBankService;

    @Autowired
    public FinController(FinService finService, CentralRussianBankService centralRussianBankService) {
        this.finService = finService;
        this.centralRussianBankService = centralRussianBankService;
    }

    @ApiOperation("saving the course for today")
    @GetMapping("/saveCurrencies")
    public List<Course> saveValuteCurrencies() throws Exception {
        return finService.addCourse(centralRussianBankService.getCurrenciesFromCbr());
    }

    @ApiOperation("assign a financial operation")
    @PostMapping(value = "/addFinanceOperation")
    public String addFinanceOperation(@Valid @RequestBody final FinanceOperationCreateDto operation) {
        return finService.addOperation(operation);
    }

    @ApiOperation("download a report with certain parameters")
    @PostMapping(value = "/report")
    public List<FinanceOperationReportDto> report(@Valid @RequestBody final ReportDto reportDto) {
        return finService.getReport(reportDto);
    }
}
