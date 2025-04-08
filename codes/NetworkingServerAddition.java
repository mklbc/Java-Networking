import java.net.*;
import java.io.*;

public class NetworkingServerAddition {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client;

        int portnumber = 5678; // Choose a port number

        try {
            server = new ServerSocket(portnumber);
            System.out.println("ServerSocket is created " + server);

            while (true) {
                System.out.println("Waiting for connect request...");
                client = server.accept();
                System.out.println("Connect request is accepted...");

                // Read numbers from the client
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));

                int num1 = Integer.parseInt(br.readLine());
                int num2 = Integer.parseInt(br.readLine());

                // Calculate the sum
                int sum = num1 + num2;

                // Send the sum back to the client
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);
                pw.println("Sum: " + sum);

                // Close sockets
                client.close();
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
