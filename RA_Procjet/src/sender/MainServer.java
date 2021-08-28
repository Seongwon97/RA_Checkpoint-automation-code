package sender;

public class MainServer {
	public static void main(String[] args) {
		RaspManageThread rasp1 = new RaspManageThread(4999, "123");
		rasp1.start();
	}
	
}
