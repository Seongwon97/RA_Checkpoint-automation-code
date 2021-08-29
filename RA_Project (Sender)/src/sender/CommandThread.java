// Terminal창에 명령을 내려주는 Thread입니다.
package sender;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandThread extends Thread{
	
	public void run() {
		System.out.println("Hello, World!");
	    String s;
	    Process p;
	    try {
	    	//이 변수에 명령어를 넣어주면 된다. 
	        String[] cmd = {"/bin/sh","-c","docker start 4bb2c7ac8c17"};
	        p = Runtime.getRuntime().exec(cmd);
	        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        while ((s = br.readLine()) != null)
	            System.out.println(s);
	        p.waitFor();
	        System.out.println("exit: " + p.exitValue());
	        p.destroy();
	    } catch (Exception e) {
	    }
	}

}
