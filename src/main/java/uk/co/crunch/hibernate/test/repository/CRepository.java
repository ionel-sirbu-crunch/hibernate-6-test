package uk.co.crunch.hibernate.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uk.co.crunch.hibernate.test.entity.C;

public interface CRepository extends JpaRepository<C, Long> {

    @Query("select c from C c where c.b.id = :bId ")
    C searchByAId(@Param("bId") Long bId);
}
