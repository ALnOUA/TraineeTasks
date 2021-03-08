package com.ua.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("Food")
public class Food extends Product {
    private int expirationDays;
    private String useBeforeData;

}
