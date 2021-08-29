// Server에게 체크포인트 파일을 보내주는 Thread입니다.
package sender;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
 
public class SenderThread extends Thread {
    public static final int DEFAULT_BUFFER_SIZE = 10000;
    
    private String serverIP;
    private int port;
    private String directory;
    private String fileName;              
    private int count;
    
    public SenderThread(String serverIP, int port, String directory, String fileName, int count) {
    	this.serverIP = serverIP;
    	this.port = port;
    	this.directory = directory;
    	this.fileName = fileName;
    	this.count = count;
    }
    
    public void run() {
    	// command thread를 먼저 호출시켜 checkpoint파일 생성을 기다리고
    	// 파일 생성이 완료되면 아래의 코드로 전송 수행
    	CommandThread command = new CommandThread(count);
		command.start();
		try {
			command.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    	
    	File file = new File(directory + fileName);
        if (!file.exists()) {
            System.out.println("File not Exist.");
            System.exit(0);
        }
         
        long fileSize = file.length();
        long totalReadBytes = 0;
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int readBytes;
        double startTime = 0;
        
    	try {
            FileInputStream fis = new FileInputStream(file);
            Socket socket = new Socket(serverIP, port);
            if(!socket.isConnected()){
                System.out.println("Socket Connect Error.");
                System.exit(0);
            }
             
            startTime = System.currentTimeMillis();
            OutputStream os = socket.getOutputStream();
            while ((readBytes = fis.read(buffer)) > 0) {
                os.write(buffer, 0, readBytes);
                totalReadBytes += readBytes;
                System.out.println("In progress: " + totalReadBytes + "/"
                        + fileSize + " Byte(s) ("
                        + (totalReadBytes * 100 / fileSize) + " %)");
            }
             
            System.out.println("File transfer completed.");
            fis.close();
            os.close();
            socket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
        double endTime = System.currentTimeMillis();
        double diffTime = (endTime - startTime)/ 1000;;
        double transferSpeed = (fileSize / 1000)/ diffTime;
         
        System.out.println("time: " + diffTime+ " second(s)");
        System.out.println("Average transfer speed: " + transferSpeed + " KB/s\n");
    }
        
}