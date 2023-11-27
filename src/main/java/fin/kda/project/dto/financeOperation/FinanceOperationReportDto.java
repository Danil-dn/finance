package fin.kda.project.dto.financeOperation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class FinanceOperationReportDto extends FinanceOperationDto {

    LocalDate date;
}
