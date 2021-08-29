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
	    	// container의 자동 실행
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
	    
	    // checkpoint내용을 tar파일로 압축이 가능하다면 일정 간격별로 sender를 실행하게 구현
		SenderThread sender = new SenderThread("192.168.219.108", 4800, "/tmp/checkpoint/", "checkpoint"+count);
		sender.setCount(count);
		sender.start();
	}

}
