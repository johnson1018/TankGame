import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TankServer {
	
	public static final int TCP_PORT = 8888;
	List<Client> clients = new ArrayList<Client>();
	
	public void start() {
		try {
			ServerSocket ss = new ServerSocket(TCP_PORT);
			while(true) {
				Socket s = ss.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String IP = s.getInetAddress().getHostAddress();
				int udpPort = dis.readInt();
				Client c = new Client(IP, udpPort);
				clients.add(c);
				s.close();
System.out.println("A Client Connect! Addr- " + s.getInetAddress() + ":" + s.getPort());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new TankServer().start();
	}
	
	private class Client {
		String IP;
		int udpPort;
		
		public Client(String IP, int udpPort) {
			this.IP = IP;
			this.udpPort = udpPort;
		}
	}
}
