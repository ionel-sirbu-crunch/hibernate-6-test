package uk.co.crunch.hibernate.test.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
public class C {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = true)
    private B b;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, unique = true)
    private D d;

    @OneToMany(mappedBy = "c", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.MERGE})
    private List<E> es;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }

    public List<E> getEs() {
        return es;
    }

    public void setEs(List<E> es) {
        this.es = es;
    }
}
