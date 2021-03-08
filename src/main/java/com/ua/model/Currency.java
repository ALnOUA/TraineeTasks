package com.ua.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Currency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long currency_id;
    private String internationalCode;
    private String name;
    private long course;
    private double multiplicity;
    @OneToMany(mappedBy="country", fetch=FetchType.EAGER)
    private Set<Product> products;

    public Currency(String code, String name, long course, double multiplicity) {
        this.internationalCode=code;
        this.name=name;
        this.course=course;
        this.multiplicity=multiplicity;
    }
}
