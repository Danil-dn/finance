package fin.kda.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Course")
public class CourseEntity extends BaseEntity {

    @Column(name = "currency")
    private String currency;

    @Column(name = "val")
    private Double val;

    @Column(name = "local_date")
    private LocalDate date;
}
