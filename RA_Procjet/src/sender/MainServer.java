package sender;

public class MainServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start Server");
		ServerThread th1 = new ServerThread();
		th1.setFilePosition("D:\\\\\\\\Programming code\\\\\\\\eclipse-workspace\\\\\\\\RA_Procjet\\\\\\\\src\\\\\\\\sender\\\\\\\\");
		th1.setPortNum(4999);
		
		th1.start();
	}

}
