package sender;

public class MainSender {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SenderThread sender = new SenderThread("192.168.219.108", 4800, "/home/ubuntu/Downloads/", "checkpoint.tar");
		
		sender.start();
	}

}
