package fin.kda.project.repo;

import fin.kda.project.entity.FinanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinanceOperationRepo extends JpaRepository<FinanceEntity, Long> {

    List<FinanceEntity> findAllByDateBetween(LocalDate start, LocalDate end);
}
