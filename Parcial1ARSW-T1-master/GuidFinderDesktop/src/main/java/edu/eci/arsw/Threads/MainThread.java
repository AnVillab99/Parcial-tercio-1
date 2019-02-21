package edu.eci.arsw.Threads;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import edu.eci.arsw.GuidFinderDesktop.GuidFinder;

public class MainThread extends Thread {
	private List<CountThread> threads;
	private GuidFinder gui;
	public boolean alive;
	private clock t;
	public detector det;
	int contados = 0;

	int count = 0;

	public MainThread(List<CountThread> th, GuidFinder gui) {
		threads = th;
		this.gui = gui;
		t = new clock(this);
		det = new detector(this, t);

		alive = true;
		det.start();

	}

	public void run() {
		dormir();
		System.out.println("1dormir ya");;
		t.start();
		System.out.println("iniciara");
		for (CountThread ct : threads) {
			ct.start();
		}
		while (alive) {

			if (contados == 4) {
				System.out.println(count);
				contados = 0;
				count = 0;
			}

		}
	}

	public void sumar(int s) {
		count += s;
		contados += 1;
	}

	public synchronized void dormir() {

		// try {
		for (CountThread ct : threads) {
			ct.dormir();
		}
		// this.wait();

		/*
		 * } catch (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

	public void awake() {
		System.out.println("desperto");
		// this.notifyAll();
		// this.notify();

		despertarHilos();

	}

	void despertarHilos() {
		for (CountThread ct : threads) {
			ct.awake();
			System.out.println("1 hilo");
		}
	}
}
