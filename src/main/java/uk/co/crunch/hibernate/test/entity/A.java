package uk.co.crunch.hibernate.test.entity;

import jakarta.persistence.*;

@Entity
public class A {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private B b;

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
}
