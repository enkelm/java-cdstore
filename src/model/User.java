package model;

import java.io.Serializable;

public class User implements Serializable{
	
	private String Username;
	private String Email;
	private String Password;
	private int Age;
	private String Gender;
	private double Salary;
	private int userID;
	private int ROLE;
	
	
	
	public User(String Username, String Email, String Password, int Age, String Gender, double Salary, int userID,int ROLE) {
		this.Username = Username;
		this.Email = Email;
		this.Password = Password;
		this.Age = Age;
		this.Gender = Gender;
		this.Salary = Salary;
		this.userID = userID;
		this.ROLE = ROLE;
	}
	
	
	
	public String getUsername() {
		return Username;
	}
	
	
	public void setUsername(String Username) {
		this.Username = Username;
	}
	
	
	public String getPassword() {
		return Password;
	}
	
	
	public void setPassword(String Password) {
		this.Password = Password;
	}
	
	
	public String getEmail() {
		return Email;
	}



	public void setEmail(String email) {
		this.Email = email;
	}



	public int getAge() {
		return Age;
	}



	public void setAge(int age) {
		Age = age;
	}



	public String getGender() {
		return Gender;
	}



	public void setGender(String gender) {
		Gender = gender;
	}



	public double getSalary() {
		return Salary;
	}



	public void setSalary(double salary) {
		Salary = salary;
	}



	public int getUserID() {
		return userID;
	}



	public void setUserID(int userID) {
		this.userID = userID;
	}



	public int getROLE() {
		return ROLE;
	}



	public void setROLE(int ROLE) {
		this.ROLE = ROLE;
	}



	@Override
	public String toString() {
		return "User [Username=" + Username + ", Email=" + Email + ", Password=" + Password + ", Age=" + Age
				+ ", Gender=" + Gender + ", Salary=" + Salary + ", userID=" + userID + ", ROLE=" + ROLE + "]";
	}
	
	


}
