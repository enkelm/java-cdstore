package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class AccessCD implements Serializable{

	private ArrayList<CD> CDs = new ArrayList<>();

	public AccessCD() {
		getFromFile();
	}
	public ArrayList<CD> getCDs() {
		return CDs;
	}

	public void setCDs(ArrayList<CD> CDs) {
		this.CDs = CDs;
	}

	public void getFromFile() {

		try {
			FileInputStream fileIn = new FileInputStream("C:\\Users\\User\\eclipse\\java-2021-09\\OPP Project\\src\\CDInfo.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			CDs = (ArrayList<CD>) in.readObject();
			in.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void setInFile() {
		try {
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\User\\eclipse\\java-2021-09\\OPP Project\\src\\CDInfo.ser");
			try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
				out.writeObject(CDs);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println(" User File Not Found");
		}
	}

}
