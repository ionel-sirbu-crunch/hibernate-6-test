package uk.co.crunch.hibernate.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uk.co.crunch.hibernate.test.entity.A;

import java.util.List;

public interface ARepository extends JpaRepository<A, Long> {

    @Query("select a from A a where a.b.id = :bId and a.id >= :minId")
    List<A> searchByCriteria(@Param("bId") Long bId, @Param("minId") Long minId);
}
