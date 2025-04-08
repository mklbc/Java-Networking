import java.io.*;
import java.net.*;

public class NetworkingClientAddition {
    public static void main(String[] args) {
        Socket client = null;
        int portnumber = 5678; // Use the same port number as the server

        try {
            // Create a client socket
            client = new Socket(InetAddress.getLocalHost(), portnumber);
            System.out.println("Client socket is created " + client);

            // Create output stream
            OutputStream clientOut = client.getOutputStream();
            PrintWriter pw = new PrintWriter(clientOut, true);

            // Create input stream
            InputStream clientIn = client.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));

            // Read two numbers from the user
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the first number:");
            int num1 = Integer.parseInt(stdIn.readLine().trim());
            System.out.println("Enter the second number:");
            int num2 = Integer.parseInt(stdIn.readLine().trim());

            // Send numbers to the server
            pw.println(num1);
            pw.println(num2);

            // Receive and print the sum from the server
            String sumMessage = br.readLine();
            System.out.println("Server response: " + sumMessage);

            // Close sockets
            pw.close();
            br.close();
            client.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
