package gof.behavioral;

import lombok.extern.java.Log;

@Log
public class StrategyPattern {
    public static void main(String[] args) {
        double startPrice = 100.0;
        ContextStrategy halfPriceStrategy = new ContextStrategy(new HalfPriceStrategy());
        ContextStrategy doublePriceStrategy = new ContextStrategy(new DoublePriceStrategy());
        log.info(String.valueOf(halfPriceStrategy.getPrice(startPrice)));
        log.info(String.valueOf(doublePriceStrategy.getPrice(startPrice)));
    }
}

interface Strategy{
    double getPrice(double startPrice);
}

class HalfPriceStrategy implements Strategy {

    @Override
    public double getPrice(double startPrice) {
        return startPrice/2;
    }
}

class DoublePriceStrategy implements Strategy {

    @Override
    public double getPrice(double startPrice) {
        return startPrice*2;
    }
}

class ContextStrategy {
    Strategy strategy;
    public ContextStrategy(Strategy strategy){
        this.strategy=strategy;
    }
    public double getPrice(double price){
        return strategy.getPrice(price);
    }
}
