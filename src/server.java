import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {

    public static String standardize(String s) {
        s = s.trim();
        s = s.replaceAll(" \\s+ ", " ");
        //String sub_string = s.substring(1, s.length() - 1).toLowerCase();
        s = s.toLowerCase();
        s = s.substring(0,1).toUpperCase() + s.substring(1, s.length());

        return s;


    }

    public static String string_check(String s){

        if (s.length() > 4 && s.substring(0,5).equals("echo\"")){
            s = s.substring(5, s.length()-1);
           // return s2;
            // return "aaaa";
        }
        else if (s.length() > 12 && s.substring(0,12).equals("standardize\"")){
            //s2 = s.substring(12, s.length()-1).toUpperCase();
            s = s.substring(12, s.length()-1);
            s = s.trim();
            s = s.replaceAll(" \\s+ ", " ");
            //String sub_string = s.substring(1, s.length() - 1).toLowerCase();
            s = s.toLowerCase();
            s = s.substring(0,1).toUpperCase() + s.substring(1, s.length());

        }
        else{
            s = null;
        }
        return s;


    }

    public static void main(String[] args){
        final ServerSocket serverSocket;
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);

        try{
            serverSocket = new ServerSocket(5001);
            clientSocket = serverSocket.accept();




            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String client_name = in.readLine();
            out.println("Hello " + client_name);
            System.out.println("Connected " + client_name);

/*
            Thread sender = new Thread(new Runnable(){
                String msg;
                @Override
                public void run(){
                    while (true){
                        msg = sc.nextLine();


                        String result = string_check(msg);



                        out.println(result);
                        out.flush();
                    }
                }
            });
            sender.start();

            */

            Thread receive = new Thread(new Runnable(){
                String msg;
                @Override
                public void run(){
                    try {
                        msg = in.readLine();
                        while (msg != "#"){


                            System.out.println("Client said: "+ msg);
                            String result = string_check(msg);


                            out.println(result);
                            out.flush();
                            msg = in.readLine();
                        }
                        System.out.println("Client disconnected");
                        out.close();
                        clientSocket.close();
                        serverSocket.close();

                    }catch (IOException e){
                        e.printStackTrace();

                    }
                }
            });
            receive.start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
