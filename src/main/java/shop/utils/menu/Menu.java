package shop.utils.menu;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuEntry> entries = new ArrayList();
    private boolean isExit = false;

    public Menu() {
        entries.add(new MenuEntry("Exit") {
            @Override
            public void run() {
                isExit = true;
            }
        });
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!isExit) {
            printMenu();
            try {
                String line = reader.readLine();
                try {
                    int choice = Integer.parseInt(line);
                    if(!(choice<1 || choice>entries.size())) {
                        MenuEntry entry = entries.get(choice - 1);
                        entry.run();
                    }
                    else System.out.println("Wrong input data");
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void printMenu()  {
        for (int i=0;i<entries.size();i++) {
            int k=i;
            System.out.println(k+1+": "+ entries.get(i).getTitle());
        }

    }

    public void addEntry(MenuEntry entry) {
        this.entries.add(entry);
    }
    public void removeEntry(MenuEntry entry){
        this.entries.remove(entry);
    }
}