package fin.kda.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "financial_operation")
public class FinanceEntity extends BaseEntity{

    @Column(name = "description_operation")
    private String description;

    @Column(name = "sum_operation")
    private Double sum;

    @Column(name = "date_operation")
    private LocalDate date;
}
