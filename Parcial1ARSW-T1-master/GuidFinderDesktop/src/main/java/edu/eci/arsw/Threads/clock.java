package edu.eci.arsw.Threads;

public class clock extends Thread{
	MainThread main;
	private long d;
	private long c;
	public boolean alive=true;
	public clock(MainThread mainThread) {
		main=mainThread;
	}
	
	public void run() {
		int y =1;

		long c =System.currentTimeMillis();
		long d = c+10000;
		
		while(alive) {
			
			if(System.currentTimeMillis()==d) {
				main.dormir();
				dormir();
				
				
				y++;
				c =System.currentTimeMillis();
				d = c+10000;
			}
			
		}
	}
	
	public synchronized void reset() {
		c =System.currentTimeMillis();
		d = c+10000;
	}
	
	public void muerte() {
		alive=false;
	}
	
	
	public synchronized void dormir() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void awake() {
		this.notify();
	}
}
