package gof.creational;

import lombok.extern.java.Log;

class Factory{
    public static void main(String[] args) {
        ButtonFactory buttonFactory = new ButtonFactory();
        Button windowsButton = buttonFactory.create("Windows");
        Button htmlButton = buttonFactory.create("Html");
        windowsButton.onClick();
        htmlButton.onClick();
    }
}

interface Button{
    void onClick();
}
@Log
class WindowsButton implements Button {

    public void onClick() {
        log.info("Windows button action");
    }
}

@Log
class HtmlButton implements Button {

    public void onClick() {
        log.info("Html button action");
    }
}

class ButtonFactory {
    public Button create(String typeOfButton){
        switch (typeOfButton) {
            case "Windows" :
                return new WindowsButton();
            case "Html":
                return new HtmlButton();
            default:
                return null; }
    }
}