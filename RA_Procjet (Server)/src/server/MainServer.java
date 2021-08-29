package server;

public class MainServer {
	public static void main(String[] args) {
		RaspManageThread rasp1 = new RaspManageThread(4800, "123");
		rasp1.start();
	}
	
}
