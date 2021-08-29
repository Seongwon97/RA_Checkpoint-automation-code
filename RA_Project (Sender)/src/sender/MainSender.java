package sender;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainSender {

	public static void main(String[] args) {
		int count = 0;
		// TODO Auto-generated method stub
		System.out.println("Container Start");
	    String s;
	    Process p;
	    try {
	    	//이 변수에 명령어를 넣어주면 된다. 
	        String[] cmd = {"/bin/sh","-c","docker start counter"};
	        p = Runtime.getRuntime().exec(cmd);
	        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        while ((s = br.readLine()) != null)
	            System.out.println(s);
	        p.waitFor();
	        System.out.println("exit: " + p.exitValue());
	        p.destroy();
	    } catch (Exception e) {
	    }
	    
	    
		SenderThread sender = new SenderThread("192.168.219.108", 4800, "/tmp/checkpoint/", "checkpoint"+count, count);
		
		sender.start();
	}

}
