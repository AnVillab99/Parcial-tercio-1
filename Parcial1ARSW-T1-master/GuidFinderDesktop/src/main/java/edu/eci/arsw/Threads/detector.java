package edu.eci.arsw.Threads;

import java.io.InputStream;
import java.util.Scanner;

public class detector extends Thread{
	
	MainThread main;
	clock c;
	boolean alive =true;
	public detector(MainThread mainThread,clock cl) {
		main=mainThread;
		c=cl;
	}
	
	
	public void run() {	
		Scanner sc = new Scanner(System.in);
		while(alive) {
			if(sc.nextLine()!="") {
				System.out.println("detecto");
				c.reset();
				//synchronized(main) {main.notify();}
				main.awake();
			}
			
		}
	}
	
	public void muerte() {
		alive=false;
	}
}
