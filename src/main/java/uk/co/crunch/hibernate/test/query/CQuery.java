package uk.co.crunch.hibernate.test.query;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import uk.co.crunch.hibernate.test.entity.C;

@Component
public class CQuery {
    private static final String C_QUERY = "select c from C c where c.b.id = :bId ";

    @PersistenceContext
    private EntityManager entityManager;

    public C searchByB(Long bId) {
        return entityManager.createQuery(C_QUERY, C.class)
                .setParameter("bId", bId)
                .getSingleResult();
    }
}
