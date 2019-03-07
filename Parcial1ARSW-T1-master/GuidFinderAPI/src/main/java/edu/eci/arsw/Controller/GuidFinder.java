package edu.eci.arsw.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.eci.arsw.Threads.CountThread;
import edu.eci.arsw.Threads.MainThread;

@Service
public class GuidFinder {

	private static UUID[] guids;
	int count;

	public GuidFinder() throws Exception {
		getGuids();
	}

	public static UUID[] getGuids() throws Exception {

		if (guids == null) {
			System.out.println("es nulo");
			FileInputStream fi;

			fi = new FileInputStream(new File("guids.eci"));

			ObjectInputStream oi = new ObjectInputStream(fi);

			guids = (UUID[]) oi.readObject();

			oi.close();
			fi.close();
		}
		return guids;

	}

	public int countGuids(UUID guidToFind) {

		count = 0;
		/*
		 * List<CountThread> threads = new ArrayList<CountThread>();
		 * 
		 * 
		 * int leng = guids.length;
		 * 
		 * int div = (int) guids.length / 4; int o = 0; for (int i = 0; i < 4; i++) {
		 * CountThread h = null; if (o + div < leng) { h = new CountThread(o, o + div,
		 * this, guidToFind, guids); threads.add(h);
		 * 
		 * } else { h = new CountThread(o, leng, this, guidToFind, guids);
		 * threads.add(h); } //h.start(); }
		 * 
		 * MainThread mt = new MainThread(threads,this); mt.start(); /*try {
		 * 
		 * for (CountThread t : threads) {
		 * 
		 * 
		 * } } catch (Exception e) { }
		 */

		for (UUID uuid : guids) {
			if (uuid.equals(guidToFind)) {
				count++;
			}

		}

		return count;

	}

	public void sumar(int a) {
		count += a;
	}

}
