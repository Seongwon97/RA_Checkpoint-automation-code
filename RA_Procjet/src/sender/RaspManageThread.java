package sender;

public class RaspManageThread extends Thread{
	private boolean isNormalTerminate; // 정상중지 되었는지 알리는 변수
	private int progress; // 현재 일한 양을 나타내주는 변수
	
	
	
	public boolean getIsNormalTerminate() {
		return isNormalTerminate;
	}



	public int getProgress() {
		return progress;
	}



	public void run() {
		boolean isTimeOut = false;
		
		System.out.println("Start Server");
		while (!isTimeOut) {
			ReceiveThread th1 = new ReceiveThread();
			th1.setFilePosition("D:\\\\\\\\Programming code\\\\\\\\eclipse-workspace\\\\\\\\RA_Procjet\\\\\\\\src\\\\\\\\sender\\\\\\\\");
			th1.setPortNum(4999);
			
			th1.start();
			
			// thread가 종료될 때까지 현재 thread wait상태로 대기
			try {
				th1.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(th1.getIsTimeOut()) isTimeOut = true;
			
		}
		
		
		
	}


}
