package shop.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Currency implements Serializable {
     long internationalCode;
    String name;
    long course;
    double multiplicity;

    public Currency(long i, String name, long course, double multiplicity) {
        this.internationalCode=i;
        this.name=name;
        this.course=course;
        this.multiplicity=multiplicity;
    }
}
