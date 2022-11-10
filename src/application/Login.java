package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import model.AccessUser;
import model.User;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Login {

	public static GridPane login() {

		AccessUser UserList = new AccessUser();

		GridPane gp = new GridPane();
		gp.setPrefSize(500, 600);
		gp.setAlignment(Pos.CENTER); // pozicioni i nodes vendoset n qender
		gp.setPadding(new Insets(50, 50, 50, 50)); // gp nis 50 pixel brenda scene (drejtim kunder orar)
		gp.setVgap(10);
		gp.setHgap(10);

		Text title = new Text("Login Screen");
		gp.add(title, 0, 0);

		Label usernameLabel = new Label("Username");
		TextField usernameField = new TextField();
		usernameField.setPrefHeight(10);

		Label passwordLabel = new Label("Password");
		PasswordField passwordField = new PasswordField();

		Button loginButton = new Button("Login");

		gp.add(usernameLabel, 0, 1);
		gp.add(usernameField, 1, 1);
		gp.add(passwordLabel, 0, 2);
		gp.add(passwordField, 1, 2);
		gp.add(loginButton, 1, 3);

		Alert noUser = new Alert(AlertType.INFORMATION);
		noUser.setTitle("Error");
		noUser.setHeaderText("Enter a Username");

		Alert noPass = new Alert(AlertType.INFORMATION);
		noPass.setTitle("Error");
		noPass.setHeaderText("Enter a Password");

		Alert noInfo = new Alert(AlertType.INFORMATION);
		noInfo.setTitle("Error");
		noInfo.setHeaderText("Enter username and password to login.");

		Alert noMatch = new Alert(AlertType.INFORMATION);
		noMatch.setTitle("Error");
		noMatch.setHeaderText("The User/Password does not match.\n Please enter another combination.");

		loginButton.setOnAction(new EventHandler<ActionEvent>() { // login me buton

			@Override
			public void handle(ActionEvent e) {
				String username = usernameField.getText();
				String password = passwordField.getText();
//				System.out.println(username + " " + password);
				if (usernameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
					noInfo.show();
				} else if (usernameField.getText().isEmpty()) {
					noUser.show();
				} else if (passwordField.getText().isEmpty()) {
					noPass.show();
				}
				if (!(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())) {
					if (username.equals("admin") && password.equals("admin")) {
						Stage newStage = new Stage();
						Scene scene = new Scene(MainView.nextPage());
						newStage.setScene(scene);
						((Stage) (((Node) e.getSource()).getScene().getWindow())).close();
						newStage.show();
					}
				}

				int cnt = 0;

				for (int i = 0; i < UserList.getUsers().size(); i++) {
					if (!(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())) {
						String savedUsername = UserList.getUsers().get(i).getUsername();
						String savedPassword = UserList.getUsers().get(i).getPassword();
						int savedROLE = UserList.getUsers().get(i).getROLE();
						System.out.println(savedUsername+" "+savedPassword+" "+savedROLE);
						if (username.equals(savedUsername) && password.equals(savedPassword)) {
							if(savedROLE == 0) {
								Stage newStage = new Stage();
								Scene scene = new Scene(MainView.nextPage());
								newStage.setScene(scene);
								((Stage) (((Node) e.getSource()).getScene().getWindow())).close();
								newStage.show();
							}
							if(savedROLE == 1) {
								Stage newStage = new Stage();
								Scene scene = new Scene(MenagerView.nextPage());
								newStage.setScene(scene);
								((Stage) (((Node) e.getSource()).getScene().getWindow())).close();
								newStage.show();
							}
							if(savedROLE == 2) {
								Stage newStage = new Stage();
								Scene scene = new Scene(CreateBill.nextPage());
								newStage.setScene(scene);
								((Stage) (((Node) e.getSource()).getScene().getWindow())).close();
								newStage.show();
							}
							
						} else if (i == UserList.getUsers().size()) {
							noMatch.show();
						}
					}
				}
				if (cnt == UserList.getUsers().size()) {
					noMatch.show();
				}
			}
		});

		passwordField.setOnKeyPressed(new EventHandler<KeyEvent>() { // login kur shtyp enter tek pass

			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					String username = usernameField.getText();
					String password = passwordField.getText();
//					System.out.println(username + " " + password);
					if (usernameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
						noInfo.show();
					} else if (usernameField.getText().isEmpty()) {
						noUser.show();
					} else if (passwordField.getText().isEmpty()) {
						noPass.show();
					}
					if (!(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())) {
						if (username.equals("admin") && password.equals("admin")) {
							Stage newStage = new Stage();
							Scene scene = new Scene(MainView.nextPage());
							newStage.setScene(scene);
							((Stage) (((Node) ke.getSource()).getScene().getWindow())).close();
							newStage.show();
						}

					}

					int cnt = 0;

					for (int i = 0; i < UserList.getUsers().size(); i++) {
						if (!(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())) {
							String savedUsername = UserList.getUsers().get(i).getUsername();
							String savedPassword = UserList.getUsers().get(i).getPassword();
							int savedROLE = UserList.getUsers().get(i).getROLE();
							if (username.equals(savedUsername) && password.equals(savedPassword)) {
								if(savedROLE == 0) {
									Stage newStage = new Stage();
									Scene scene = new Scene(MainView.nextPage());
									newStage.setScene(scene);
									((Stage) (((Node) ke.getSource()).getScene().getWindow())).close();
									newStage.show();
								}
								if(savedROLE == 1) {
									Stage newStage = new Stage();
									Scene scene = new Scene(MenagerView.nextPage());
									newStage.setScene(scene);
									((Stage) (((Node) ke.getSource()).getScene().getWindow())).close();
									newStage.show();
								}
								if(savedROLE == 2) {
									Stage newStage = new Stage();
									Scene scene = new Scene(CreateBill.nextPage());
									newStage.setScene(scene);
									((Stage) (((Node) ke.getSource()).getScene().getWindow())).close();
									newStage.show();
								}
							} else if (i == UserList.getUsers().size()) {
								noMatch.show();
							}
						}
					}
					if (cnt == UserList.getUsers().size()) {
						noMatch.show();
					}
				}
			}
		});

		usernameField.setOnKeyPressed(new EventHandler<KeyEvent>() { // login kur shtyp enter

			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					String username = usernameField.getText();
					String password = passwordField.getText();
//					System.out.println(username + " " + password);
					if (usernameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
						noInfo.show();
					} else if (usernameField.getText().isEmpty()) {
						noUser.show();
					} else if (passwordField.getText().isEmpty()) {
						noPass.show();
					}
					if (!(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())) {
						if (username.equals("admin") && password.equals("admin")) {
							Stage newStage = new Stage();
							Scene scene = new Scene(MainView.nextPage());
							newStage.setScene(scene);
							((Stage) (((Node) ke.getSource()).getScene().getWindow())).close();
							newStage.show();
						}

					}

					int cnt = 0;

					for (int i = 0; i < UserList.getUsers().size(); i++) {
						if (!(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())) {
							String savedUsername = UserList.getUsers().get(i).getUsername();
							String savedPassword = UserList.getUsers().get(i).getPassword();
							int savedROLE = UserList.getUsers().get(i).getROLE();
//							System.out.println(savedUsername+" "+savedPassword);
							if (username.equals(savedUsername) && password.equals(savedPassword)) {
								if(savedROLE == 0) {
									Stage newStage = new Stage();
									Scene scene = new Scene(MainView.nextPage());
									newStage.setScene(scene);
									((Stage) (((Node) ke.getSource()).getScene().getWindow())).close();
									newStage.show();
								}
								if(savedROLE == 1) {
									Stage newStage = new Stage();
									Scene scene = new Scene(MenagerView.nextPage());
									newStage.setScene(scene);
									((Stage) (((Node) ke.getSource()).getScene().getWindow())).close();
									newStage.show();
								}
								if(savedROLE == 2) {
									Stage newStage = new Stage();
									Scene scene = new Scene(CreateBill.nextPage());
									newStage.setScene(scene);
									((Stage) (((Node) ke.getSource()).getScene().getWindow())).close();
									newStage.show();
								}
							}
							cnt++;
						}
					}
					if (cnt == UserList.getUsers().size()) {
						noMatch.show();
					}
				}
			}
		});
		return gp;
	}
}
