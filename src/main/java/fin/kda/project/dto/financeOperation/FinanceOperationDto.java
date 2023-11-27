package fin.kda.project.dto.financeOperation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class FinanceOperationDto {

    @NotNull
    @ApiModelProperty(name = "description of the currency operation", example = "test valute operation", required = true)
    private String description;

    @NotNull
    @ApiModelProperty(name = "transaction amount in rubles", example = "15000", required = true)
    private double sum;
}
