package shop.model;

import lombok.Data;

@Data
public class CurrencyPicker {
    public static Context context;
    public Context choosePrice(String currency) throws Exception {
        Context context = null;
        switch (currency.toLowerCase()){
            case "usd": context = new Context(new Dollar());break;
            case "eur": context = new Context(new Euro());break;
            default:
                System.out.println("Not supported currency");
                throw new Exception("Not supported currency");
        }
        return context;
    }

    public interface CurrencyStrategy{
        long getPrice(long price);
    }


    class Dollar implements CurrencyStrategy{

        @Override
        public long getPrice(long price) {
            return price*8L;
        }
    }
    class Euro implements CurrencyStrategy{

        @Override
        public long getPrice(long price) {
            return price*8;
        }
    }

     public class Context{
        CurrencyStrategy currencyStrategy;
        public Context(CurrencyStrategy currencyStrategy){
            this.currencyStrategy=currencyStrategy;
        }

        public long getPrice(long price){
            return currencyStrategy.getPrice(price);
        }
    }
}
