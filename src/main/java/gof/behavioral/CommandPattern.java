package gof.behavioral;

import java.util.ArrayList;
import java.util.List;

public class CommandPattern {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        receiver.addCommand(new MouseClick());
        receiver.addCommand(new MousePress());
        receiver.runCommand();
    }

}

interface Command{
    void execute();
}

class MouseClick implements Command {

    @Override
    public void execute() {
        System.out.println("click command");
    }

}

class MousePress implements Command {

    @Override
    public void execute() {
        System.out.println("press command");
    }
}

class Receiver {
    List<Command> commands = new ArrayList<>();
    void addCommand(Command command){
        commands.add(command);
    }
    void runCommand(){
        for (Command command : commands) {
            command.execute();
        }
    }
}
