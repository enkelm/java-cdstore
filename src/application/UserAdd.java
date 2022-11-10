package application;

import model.AccessUser;
import model.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class UserAdd {

//	public UserAdd(String Username, String Email, String Password, int Age, String Gender, double Salary, int userID, int ROLE) {
//		super(Username, Email, Password, Age, Gender, Salary, userID, ROLE);
//	}

	public static GridPane nextPage() {

		AccessUser UserList = new AccessUser();
		System.out.println(UserList.getUsers().toString());

		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setPrefSize(500, 500);
		gp.setPadding(new Insets(50, 50, 50, 50));
		gp.setVgap(10);
		gp.setHgap(10);

		Label usernameLabel = new Label("Username");
		TextField usernameField = new TextField();

		Label emailLabel = new Label("Email");
		TextField emailField = new TextField();

		Label passwordLabel = new Label("Password");
		TextField passwordField = new TextField();

		Label ageLabel = new Label("Age");
		TextField ageField = new TextField();

		Label genderLabel = new Label("Gender");
		ObservableList<String> genderOptions = FXCollections.observableArrayList("Male", "Female", "Non Binary",
				"Other");
		final ComboBox genderComboBox = new ComboBox(genderOptions);
		genderComboBox.setPromptText("Select a gender");

		Label salaryLabel = new Label("Salary");
		TextField salaryField = new TextField();

		Label roleLabel = new Label("Role");
		ObservableList<String> roleOptions = FXCollections.observableArrayList("Cashier", "Menager", "Admin");
		final ComboBox roleComboBox = new ComboBox(roleOptions);
		roleComboBox.setPromptText("Select a role"); // no selection promt
		// roleComboBox.getValue() merr cfar zgjidhet n String nga lista ne rradh
		// ekzekutimi

		gp.add(usernameLabel, 0, 1);
		gp.add(usernameField, 1, 1);
		gp.add(emailLabel, 0, 2);
		gp.add(emailField, 1, 2);
		gp.add(passwordLabel, 0, 3);
		gp.add(passwordField, 1, 3);
		gp.add(ageLabel, 0, 4);
		gp.add(ageField, 1, 4);
		gp.add(genderLabel, 0, 5);
		gp.add(genderComboBox, 1, 5);
		gp.add(roleLabel, 0, 6);
		gp.add(roleComboBox, 1, 6);
		gp.add(salaryLabel, 0, 7);
		gp.add(salaryField, 1, 7);		
		
		BooleanBinding bb = new BooleanBinding() {
		    {
		        super.bind(usernameField.textProperty(),
		                emailField.textProperty(),
		                passwordField.textProperty(),
		                ageField.textProperty(),
		                salaryField.textProperty());
		    }

		    @Override
		    protected boolean computeValue() {
		        return (usernameField.getText().isEmpty()
		                || emailField.getText().isEmpty()
		                || passwordField.getText().isEmpty()
		                || ageField.getText().isEmpty()
		                || salaryField.getText().isEmpty());
		    }
		};

		Button saveButton = new Button("Save User");
		gp.add(saveButton, 1, 8);
		saveButton.disableProperty().bind(bb);

		

		Alert noInfo = new Alert(AlertType.INFORMATION);
		noInfo.setTitle("Error");
		noInfo.setHeaderText("Please ender all necessary information");

		saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				String username = usernameField.getText();
				String email = emailField.getText();
				String password = passwordField.getText();
				int age = Integer.parseInt(ageField.getText());
				String gender = (String) genderComboBox.getValue();
				double salary = Double.parseDouble(salaryField.getText());
				int ID = UserList.getUsers().size();
				int ROLE = -1;

				if (roleComboBox.getValue().equals("Cashier")) {
					ROLE = 2;
				} else if (roleComboBox.getValue().equals("Menager")) {
					ROLE = 1;
				} else if (roleComboBox.getValue().equals("Admin")) {
					ROLE = 0;
				} else if (roleComboBox.getValue().equals(null)) {
					noInfo.show();
				}

				User user = new User(username, email, password, age, gender, salary, ID, ROLE);

				UserList.getUsers().add(user);
				UserList.setInFile();

				usernameField.clear();
				emailField.clear();
				passwordField.clear();
				ageField.clear();
				salaryField.clear();
				usernameField.requestFocus();

//				System.out.println(username + " " + email + " " + password + " " + age + " " + gender + " " + salary + " " + ID + " " + ROLE );							

			}

		});

		return gp;
	}

}
