
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.PrintStream;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
			
			Scanner kb = new Scanner(System.in);
			OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os, true, "UTF-8");
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
			String returnMessage;
			String message;
			while(true) {
				System.out.print("Client> ");
				message = kb.nextLine();
				out.printf(message + "%n");
				returnMessage = br.readLine();
				if(message.equals("exit")) {
					break;
				} else{
					System.out.println(returnMessage);
				}
			}
        }
    }
}















