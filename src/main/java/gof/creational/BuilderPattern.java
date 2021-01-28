package gof.creational;

import lombok.Getter;
import lombok.Setter;

public class BuilderPattern {
    public static void main(String[] args) {
        SportCar sportCar = new SportCar.Builder().setColor("green").setHorsePower(250).setSeats(4).build();
        System.out.println(sportCar.getColor()+sportCar.getHorsePower()+sportCar.getSeats());
    }
}

@Getter
@Setter
class SportCar{
    private int seats;
    private int horsePower;
    private String color;

    private SportCar(Builder builder){
        this.color = builder.color;
        this.horsePower = builder.horsePower;
        this.seats = builder.seats;

    }
    static class Builder{
        private int seats;
        private int horsePower;
        private String color;

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }
        public Builder setSeats(int seats) {
            this.seats = seats;
            return this;
        }
        public Builder setHorsePower(int horsePower) {
            this.horsePower = horsePower;
            return this;
        }
        public SportCar build(){
            return new SportCar(this);
        }

    }
}