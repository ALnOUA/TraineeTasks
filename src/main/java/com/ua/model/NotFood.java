package com.ua.model;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("NotFood")
public class NotFood extends Product {
    private String name;
    private String material;

}