// Terminal창에 명령을 내려주는 Thread입니다.
package sender;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandThread extends Thread{
	private int count;
	
	public CommandThread(int count) {
		this.count = count;
	}
	
	public void run() {
		System.out.println("Start makeing checkpoint"+count);
	    String s;
	    Process p;
	    // checkpoint파일 생성
	    try {
	        String[] cmd = {"/bin/sh","-c","docker checkpoint create --leave-running=true --checkpoint-dir=/tmp/checkpoint counter checkpoint"+count};
	        p = Runtime.getRuntime().exec(cmd);
	        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        while ((s = br.readLine()) != null)
	            System.out.println(s);
	        p.waitFor();
	        System.out.println("exit: " + p.exitValue());
	        System.out.println("Complete making checkpoint"+count);
	        p.destroy();
	    } catch (Exception e) {
	    	
	    }
	    
	    // Checkpoint파일 압축
	    try {
	        String[] cmd = {"/bin/sh","-c","docker checkpoint create --leave-running=true --checkpoint-dir=/tmp/checkpoint counter checkpoint"+count};
	        p = Runtime.getRuntime().exec(cmd);
	        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        while ((s = br.readLine()) != null)
	            System.out.println(s);
	        p.waitFor();
	        System.out.println("exit: " + p.exitValue());
	        System.out.println("Complete making checkpoint"+count);
	        p.destroy();
	    } catch (Exception e) {
	    }
	}

}
