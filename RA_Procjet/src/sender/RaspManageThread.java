package sender;

public class RaspManageThread extends Thread{
	
	private int portNum;
	private String fileLocation;
	
	
	public boolean isNormalTerminate; // 정상중지 되었는지 알리는 변수
	private int progress; // 현재 일한 양을 나타내주는 변수
	public boolean isTerminate = false; 
	
	

	
	public RaspManageThread(int portNum, String fileLocation) {
		this.portNum = portNum;
		this.fileLocation = fileLocation;
	}

	public int getProgress() {
		return progress;
	}



	public void run() {
		boolean isTimeOut = false;
		
		System.out.println("Start Server");
		while (!isTimeOut) {
			ReceiveThread th1 = new ReceiveThread();
			th1.setFilePosition("D:\\Programming code\\eclipse-workspace\\RA_Procjet\\src\\sender\\" + fileLocation + "\\");
			th1.setPortNum(portNum);
			
			th1.start();
			
			// thread가 종료될 때까지 현재 thread wait상태로 대기
			try {
				th1.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			if(th1.getIsTimeOut()) isTimeOut = true;
			
			
			
			// 추후 receiveThread에서 값을 받아와서 progress진행이 4를 넘으면 thread중단.
			if(progress >= 4) {
				break;
			}
		}
		System.out.println("Close Server");
				
	}

}
