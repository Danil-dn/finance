package fin.kda.project.dto.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Course {

    private String currency;

    private double val;

    private LocalDate date;
}
