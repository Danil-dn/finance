package fin.kda.project.service;

import fin.kda.project.dto.ReportDto;
import fin.kda.project.dto.ValuteCursOnDate;
import fin.kda.project.dto.course.Course;
import fin.kda.project.dto.financeOperation.FinanceOperationCreateDto;
import fin.kda.project.dto.financeOperation.FinanceOperationReportDto;
import fin.kda.project.entity.CourseEntity;
import fin.kda.project.entity.FinanceEntity;
import fin.kda.project.repo.CouresRepo;
import fin.kda.project.repo.FinanceOperationRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FinService {

    final CouresRepo couresRepo;

    final FinanceOperationRepo financeRepo;

    @Autowired
    public FinService(CouresRepo couresRepo, FinanceOperationRepo financeRepo) {
        this.couresRepo = couresRepo;
        this.financeRepo = financeRepo;
    }

    public List<Course> addCourse(List<ValuteCursOnDate> currenciesFromCbr) {
        List<CourseEntity> list = couresRepo.findAllByDate(LocalDate.now());

        if (!list.isEmpty()) {
            list.forEach(course -> currenciesFromCbr.forEach(currencies -> {
                if (course.getCurrency().equals(currencies.getChCode())) {
                    course.setVal(currencies.getCourse());
                }
            }));
            return couresRepo.saveAll(list).stream().map(this::buildDto).collect(Collectors.toList());
        }

        return couresRepo.saveAll(currenciesFromCbr.stream().map(this::buildEntity).collect(Collectors.toList())).stream().map(this::buildDto).collect(Collectors.toList());
    }

    public String addOperation(FinanceOperationCreateDto operation) {
        FinanceEntity financeEntity = new FinanceEntity();
        financeEntity.setSum(operation.getSum());
        financeEntity.setDate(LocalDate.now());
        financeEntity.setDescription(operation.getDescription());
        financeRepo.save(financeEntity);
        return "success";

    }

    private CourseEntity buildEntity(ValuteCursOnDate valuteCursOnDate) {
        CourseEntity course = new CourseEntity();
        course.setCurrency(valuteCursOnDate.getChCode());
        course.setVal(valuteCursOnDate.getCourse());
        course.setDate(LocalDate.now());
        return course;
    }

    private Course buildDto(CourseEntity courseEntity) {
        return new Course(courseEntity.getCurrency(), courseEntity.getVal(), courseEntity.getDate());
    }

    public List<FinanceOperationReportDto> getReport(ReportDto reportDto) {
        return reportList(financeRepo.findAllByDateBetween(reportDto.getStartDate(), reportDto.getEndDate()),couresRepo.findAllByDateBetween(reportDto.getStartDate(), reportDto.getEndDate()).stream().filter(courseEntity -> courseEntity.getCurrency().equals(reportDto.getValute())).collect(Collectors.toList()));
    }

    public List<FinanceOperationReportDto> reportList(List<FinanceEntity> financelist, List<CourseEntity> courseList){
        List<FinanceOperationReportDto> finalList = new ArrayList<>();

        courseList.forEach(course -> financelist.forEach(finance -> {
            if (course.getDate().equals(finance.getDate())) {
                FinanceOperationReportDto financeOperationReportDto = new FinanceOperationReportDto();
                financeOperationReportDto.setSum(finance.getSum() / course.getVal());
                financeOperationReportDto.setDate(finance.getDate());
                financeOperationReportDto.setDescription(finance.getDescription());
                finalList.add(financeOperationReportDto);
            }
        }));

        return finalList;
    }
}
