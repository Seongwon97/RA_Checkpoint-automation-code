package sender;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiveThread extends Thread {
	public static final int DEFAULT_BUFFER_SIZE = 10000;
	
	public boolean isTimeOut = false;
	private int portNum;
	private String filePosition;
	public int progress = 0;
	
	
	public boolean getIsTimeOut() {
		return isTimeOut;
	}


	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}


	public void setFilePosition(String filePosition) {
		this.filePosition = filePosition;
	}

	
	public void run() {
        try {
        	String fileName;
        	Date now = new Date(System.currentTimeMillis()); // 저장할 파일 명을 현재 시간으로 하기 위해 추가
        	SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss"); 
        	
            ServerSocket server = new ServerSocket(portNum);
            System.out.println("This server is listening... (Port: " + portNum  + ")");
            
            Socket socket = server.accept();  //새로운 연결 소켓 생성 및 accept대기
            InetSocketAddress isaClient = (InetSocketAddress) socket.getRemoteSocketAddress();
             
            System.out.println("A client("+isaClient.getAddress().getHostAddress()+
                    " is connected. (Port: " +isaClient.getPort() + ")");
            
            
        	fileName = fourteen_format.format(now); 
        	FileOutputStream fos = new FileOutputStream(filePosition + fileName+".zip");
        	InputStream is = socket.getInputStream();
        	
        	double startTime = System.currentTimeMillis(); 
        	byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        	int readBytes;
        	while ((readBytes = is.read(buffer)) != -1) {
        		fos.write(buffer, 0, readBytes);
        	}      
        	double endTime = System.currentTimeMillis();
        	double diffTime = (endTime - startTime)/ 1000;;
        	System.out.println("time: " + diffTime+ " second(s)");
        	
        	is.close();
        	fos.close();
            
            socket.close();
            server.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
