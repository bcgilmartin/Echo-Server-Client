
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public final class EchoServer {

    public static void main(String[] args) throws Exception {
		
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
					Socket socket = serverSocket.accept();
					Runnable runner = new ParameterRun(socket);
					new Thread(runner).start();
            }
        }
    }
	public static class ParameterRun implements Runnable {

		private Socket socket;
	
		public ParameterRun(Socket ThreadSocket) {
			socket = ThreadSocket;
		}

		public void run() {
			try {
			
				String address = socket.getInetAddress().getHostAddress();
				System.out.printf("Client connected: %s%n", address);
						
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				OutputStream os = socket.getOutputStream();
				PrintStream out = new PrintStream(os, true, "UTF-8");
				String message;
				boolean on = true;
				while(on) {
					message = br.readLine();
					if(message.equals("exit")) {
						System.out.printf("Client disconnected: %s%n", address);
						out.printf("%n");
						on = false;
					}
					else {
						out.printf("Server> %s%n", message);
					}
				}
			} catch(Exception E){E.printStackTrace();}
			
		}
	}
}
