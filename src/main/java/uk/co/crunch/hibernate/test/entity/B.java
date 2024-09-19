package uk.co.crunch.hibernate.test.entity;

import jakarta.persistence.*;

@Entity
public class B {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "b")
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
