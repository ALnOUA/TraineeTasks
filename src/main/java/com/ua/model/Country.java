package com.ua.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long country_id;
    private String name;
    @OneToMany(mappedBy="country", fetch=FetchType.EAGER)
    private Set<Product> products;
}
