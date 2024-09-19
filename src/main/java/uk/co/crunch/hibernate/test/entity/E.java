package uk.co.crunch.hibernate.test.entity;

import jakarta.persistence.*;

@Entity
public class E {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private C c;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }
}
