package uk.co.crunch.hibernate.test.query;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import uk.co.crunch.hibernate.test.entity.A;

import java.util.List;

@Component
public class AQuery {
    private static final String A_QUERY = "select a from A a where a.b.id = :bId and a.id >= :minId";

    @PersistenceContext
    private EntityManager entityManager;

    public List<A> searchByCriteria(Long bId, Long minId) {
        return entityManager.createQuery(A_QUERY, A.class)
                .setParameter("bId", bId)
                .setParameter("minId", minId)
                .getResultList();
    }
}
