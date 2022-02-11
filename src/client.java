import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner scanner = new Scanner(System.in);
        final Scanner scanner2 = new Scanner(System.in);
        try {
            clientSocket = new Socket("localhost", 5001);

            System.out.println("Enter client name:");
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Thread sender = new Thread(new Runnable() {
                String msg;
                @Override
                public void run(){
                    while (true) {
                        msg = scanner.nextLine();
                        out.println(msg);
                        out.flush();
                    }
                }
            });
            sender.start();
            Thread receiver = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        while (!Objects.equals(msg, null)){
                            System.out.println("Server said: "+ msg);
                            msg = in.readLine();

                        }
                        System.out.println("Server disconnected");
                        out.close();
                        clientSocket.close();
                        System.exit(0);

                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });
            receiver.start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
