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
    
    private String serverIP = "192.168.219.108";              //String serverIP = "127.0.0.1";
    private int port = 4800;   //int port = 9999;
    private String directory;
    private String fileName = "/home/ubuntu/Downloads/checkpoint.tar";              //String FileName = "test.mp4";
    
    public SenderThread(String serverIP, int port, String directory, String fileName) {
    	this.serverIP = serverIP;
    	this.port = port;
    	this.directory = directory;
    	this.fileName = fileName;
    }

    public void run() {
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
        System.out.println("Average transfer speed: " + transferSpeed + " KB/s");
    }
        
}