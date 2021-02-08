package chat.server;

import chat.client.ClientWindow;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Server server;
    private PrintWriter outMessage;
    private Scanner inMessage;
    private static final String HOST = "localhost";
    private static final int PORT = 3443;
    private Socket clientSocket = null;
    private static int clients_count = 0;

    public ClientHandler(Socket socket, Server server) {
        try {
            clients_count++;
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                server.sendMessageToAllClients("Новый участник вошёл в чат!");
                server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
                break;
            }

            while (true) {
                if (inMessage.hasNext()) {
                    String clientMessage = inMessage.nextLine();
                    int indexOfDelimiter = clientMessage.lastIndexOf(":");

                    if (clientMessage.equalsIgnoreCase(clientMessage.substring(0,indexOfDelimiter+2)+"/end")) {
                        server.sendMessageToAllClients(clientMessage.substring(0,indexOfDelimiter)+" left the chat");
                        System.out.println(clientMessage.substring(0,indexOfDelimiter)+" left the chat");
                        close();
                        break;
                    }
                    System.out.println(clientMessage);
                    server.sendMessageToAllClients(clientMessage);

                    getAnswer(clientMessage,server, indexOfDelimiter);



                }
                Thread.sleep(100);
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
            this.close();
        }
    }
    public void sendMsg(String msg) {
        try {
            outMessage.println(msg);
            outMessage.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void close() {
        // удаляем клиента из списка
        server.removeClient(this);
        clients_count--;
        server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
    }
    public void getAnswer(String clientMessage, Server server, int indexOfDelimiter){

        if (clientMessage.equalsIgnoreCase(clientMessage.substring(0,indexOfDelimiter+2)+"hello")){
            server.sendMessageToAllClients("Server: Hello!");
            System.out.println("Server: Hello!");
        }
        if (clientMessage.equalsIgnoreCase(clientMessage.substring(0,indexOfDelimiter+2)+"how are you?")){
            server.sendMessageToAllClients("Server: Good! And you?");
            System.out.println("Server: Good! And you?");
        }
        if (clientMessage.equalsIgnoreCase(clientMessage.substring(0,indexOfDelimiter+2)+"what time is it?")){
            server.sendMessageToAllClients("Server: "+ LocalTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            System.out.println("Server: "+ LocalTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        }
        if (clientMessage.equalsIgnoreCase(clientMessage.substring(0,indexOfDelimiter+2)+"buy")){
            server.sendMessageToAllClients("Server: Have a nice day!");
            System.out.println("Server: Have a nice day!");
        }
        if (clientMessage.equalsIgnoreCase(clientMessage.substring(0,indexOfDelimiter+2)+"what is your name?")){
            server.sendMessageToAllClients("Server: My name is Siri");
            System.out.println("Server: My name is Siri");
        }
    }
}
