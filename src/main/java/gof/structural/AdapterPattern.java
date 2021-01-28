package gof.structural;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AdapterPattern {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole();
        hole.setRadius(1);
        SquarePeg peg = new SquarePeg();
        peg.setWidth(3);
        SquarePegAdapter squarePegAdapter = new SquarePegAdapter(peg);
        System.out.println(hole.fits(squarePegAdapter));
    }
}

@Getter
@Setter
@NoArgsConstructor
class RoundHole{
    private int radius;
    RoundHole(int radius){
        this.radius = radius;
    }

    public boolean fits(RoundPeg roundPeg){
        return this.getRadius()>= roundPeg.getRadius();

    }

}

@Getter
@Setter
@NoArgsConstructor
class SquarePeg{
    private int width;

    SquarePeg(int width){
        this.width=width;
    }

}

@Getter
@NoArgsConstructor
class RoundPeg{
    private int radius;

    RoundPeg(int radius){
        this.radius= radius;
    }
}

class SquarePegAdapter extends RoundPeg {
    SquarePeg squarePeg;

    SquarePegAdapter(SquarePeg squarePeg) {
        this.squarePeg = squarePeg;
    }
    public int getRadius(){
        return (int) (squarePeg.getWidth() * Math.sqrt(2)/2);
    }
}
