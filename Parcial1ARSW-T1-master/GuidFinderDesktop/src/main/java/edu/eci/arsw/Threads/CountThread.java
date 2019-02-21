/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.Threads;

import java.util.UUID;

import edu.eci.arsw.GuidFinderDesktop.GuidFinder;

/**
 *
 * @author hcadavid
 */

public class CountThread extends Thread {
	private int i;
	private int j;
	private GuidFinder main;
	private UUID guid;
	private UUID[] guids;
	private int count = 0;
	boolean alive=true;
	private MainThread mt;

	public CountThread(int o, int leng, GuidFinder guidFinder, UUID guidToFind, UUID[] guids) {
		i = o;
		j = leng;
		main = guidFinder;
		guid = guidToFind;
		this.guids = guids;
	}

	public void run() {
		count=0;
		while (alive) {
			for (int u = i; u < j; u++) {

				if (guids[u].equals(guid)) {
					count++;
				}

			}
			mt.sumar(count);
			dormir();
		}

	}

	public synchronized void dormir() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void awake() {
		this.notify();
	}
	
	public void matar() {
		alive=false;
	}
	
	public void setmainThread(MainThread M) {
		mt=M;
	}
}
