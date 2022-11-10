package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class AccessUser implements Serializable {

	private ArrayList<User> users = new ArrayList<>();

	public AccessUser() {
		getFromFile();
	}
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public void getFromFile() {

		try {
			FileInputStream fileIn = new FileInputStream("C:\\Users\\User\\eclipse\\java-2021-09\\OPP Project\\src\\UserInfo.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			users = (ArrayList<User>) in.readObject();
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
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\User\\eclipse\\java-2021-09\\OPP Project\\src\\UserInfo.ser");
			try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
				out.writeObject(users);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println(" User File Not Found");
		}
	}
	

}
