package fin.kda.project.repo;

import fin.kda.project.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CouresRepo extends JpaRepository<CourseEntity, Long> {

    List<CourseEntity> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    List<CourseEntity> findAllByDate(LocalDate localDate);
}
