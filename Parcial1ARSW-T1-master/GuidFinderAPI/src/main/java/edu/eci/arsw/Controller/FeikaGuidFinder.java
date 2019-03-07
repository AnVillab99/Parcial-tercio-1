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


//@Service
public class FeikaGuidFinder {

	private static UUID[] guids;
	int count;

	public FeikaGuidFinder() throws Exception {
		
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
