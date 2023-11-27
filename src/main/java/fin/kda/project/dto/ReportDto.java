package fin.kda.project.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ReportDto {

    @ApiModelProperty(name = "Сurrency name", example = "USD", required = true)
    @NotNull
    String valute;

    @ApiModelProperty(name = "Start date", example = "2023-11-17", required = true)
    @NotNull
    LocalDate startDate;

    @ApiModelProperty(name = "End date", example = "{this -> current date}", required = true)
    @NotNull
    LocalDate endDate;
}
