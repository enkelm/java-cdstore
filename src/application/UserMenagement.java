package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.AccessUser;
import model.User;

public class UserMenagement {

	public static GridPane nextPage() {
		AccessUser UserList = new AccessUser();
//		System.out.println(UserList.getUsers().toString());

		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setPrefSize(900, 600);

		final Label label = new Label("User List");
		label.setFont(new Font("Arial", 20));

		TableView<User> userTable = new TableView<User>();
		userTable.setPlaceholder(new Label("No users store"));
		userTable.setEditable(true);

		TableColumn<User, Integer> IDColumn = new TableColumn<>("User ID");
		IDColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("userID"));
		IDColumn.setMinWidth(50);

		TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
		usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		usernameColumn.setCellFactory(TextFieldTableCell.<User>forTableColumn());
		usernameColumn.setMinWidth(100);

		usernameColumn.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {

			@Override
			public void handle(CellEditEvent<User, String> e) {
				User user = e.getRowValue();
				user.setUsername(e.getNewValue());
				int i = user.getUserID();
				UserList.getUsers().remove(i);
				UserList.getUsers().add(i, user);
				UserList.setInFile();
			}

		});

		TableColumn<User, String> emailColumn = new TableColumn<>("Email");
		emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		emailColumn.setCellFactory(TextFieldTableCell.<User>forTableColumn());
		emailColumn.setMinWidth(200);

		emailColumn.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {

			@Override
			public void handle(CellEditEvent<User, String> e) {
				User user = e.getRowValue();
				user.setEmail(e.getNewValue());
				int i = user.getUserID();
				UserList.getUsers().remove(i);
				UserList.getUsers().add(i, user);
				UserList.setInFile();
			}

		});

		TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
		passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
		passwordColumn.setCellFactory(TextFieldTableCell.<User>forTableColumn());
		passwordColumn.setMinWidth(100);

		passwordColumn.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {

			@Override
			public void handle(CellEditEvent<User, String> e) {
				User user = e.getRowValue();
				user.setPassword(e.getNewValue());
				int i = user.getUserID();
				UserList.getUsers().remove(i);
				UserList.getUsers().add(i, user);
				UserList.setInFile();
			}

		});

		TableColumn<User, Integer> ageColumn = new TableColumn<User, Integer>("Age");
		ageColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("age"));
		ageColumn.setCellFactory(TextFieldTableCell.<User, Integer>forTableColumn(new IntegerStringConverter()));
		ageColumn.setMinWidth(50);

		ageColumn.setOnEditCommit(new EventHandler<CellEditEvent<User, Integer>>() {

			@Override
			public void handle(CellEditEvent<User, Integer> e) {
				User user = e.getRowValue();
				user.setAge(e.getNewValue());
				int i = user.getUserID();
				UserList.getUsers().remove(i);
				UserList.getUsers().add(i, user);
				UserList.setInFile();
			}

		});

		TableColumn<User, String> genderColumn = new TableColumn<>("Gender");
		genderColumn.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
		genderColumn.setCellFactory(
				ComboBoxTableCell.<User, String>forTableColumn("Male", "Female", "Non Binary", "Other"));
		genderColumn.setMinWidth(100);

		genderColumn.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {

			@Override
			public void handle(CellEditEvent<User, String> e) {
				User user = e.getRowValue();
				user.setGender(e.getNewValue());
				int i = user.getUserID();
				UserList.getUsers().remove(i);
				UserList.getUsers().add(i, user);
				UserList.setInFile();

			}

		});

		TableColumn<User, Double> salaryColumn = new TableColumn<>("Salary");
		salaryColumn.setCellValueFactory(new PropertyValueFactory<User, Double>("salary"));
		salaryColumn.setCellFactory(TextFieldTableCell.<User, Double>forTableColumn(new DoubleStringConverter()));
		salaryColumn.setMinWidth(100);

		salaryColumn.setOnEditCommit(new EventHandler<CellEditEvent<User, Double>>() {

			@Override
			public void handle(CellEditEvent<User, Double> e) {
				User user = e.getRowValue();
				user.setSalary(e.getNewValue());
				int i = user.getUserID();
				UserList.getUsers().remove(i);
				UserList.getUsers().add(i, user);
				UserList.setInFile();
			}

		});

		TableColumn<User, Integer> roleColumn = new TableColumn<>("Role");
		roleColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("ROLE"));
		roleColumn.setCellFactory(ComboBoxTableCell.<User, Integer>forTableColumn(0, 1, 2));
		roleColumn.setMinWidth(100);

		roleColumn.setOnEditCommit(new EventHandler<CellEditEvent<User, Integer>>() {

			@Override
			public void handle(CellEditEvent<User, Integer> e) {
				User user = e.getRowValue();
				user.setSalary(e.getNewValue());
				int i = user.getUserID();
				UserList.getUsers().remove(i);
				UserList.getUsers().add(i, user);
				UserList.setInFile();

			}

		});

		for (int i = 0; i < UserList.getUsers().size(); i++) {
			userTable.getItems().add((User) UserList.getUsers().get(i));
		}

		userTable.getColumns().addAll(IDColumn, usernameColumn, emailColumn, passwordColumn, ageColumn, genderColumn,
				salaryColumn, roleColumn);

		Button addUserButton = new Button("Add User");
		Button deleteButton = new Button("Delete User");

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, userTable, addUserButton, deleteButton);
		gp.add(vbox, 0, 0);

		addUserButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				Stage newStage = new Stage();
				Scene scene = new Scene(UserAdd.nextPage());
				newStage.setScene(scene);
				// ((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
				newStage.show();

			}

		});

		Alert noSelection = new Alert(AlertType.INFORMATION);
		noSelection.setTitle("Error");
		noSelection.setHeaderText("Please select a user to be deleted");

		deleteButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				int i = -1;
				i = userTable.getSelectionModel().getSelectedIndex();
				if (i < 0) {
					noSelection.show();
				}
				if (i >= 0) {
					UserList.getUsers().remove(i);
					int size = UserList.getUsers().size();
					userTable.getItems().remove(i);
					userTable.getSelectionModel().clearSelection();

					for (int j = i; j < size; j++) {
						int ID = UserList.getUsers().get(j).getUserID();
						int newID = ID - 1;
						UserList.getUsers().get(j).setUserID(newID);
					}
					UserList.setInFile();
				}
			}

		});

		return gp;
	}

}



//TableViewSelectionModel<User> selectionModel = userTable.getSelectionModel();
//selectionModel.setSelectionMode(SelectionMode.SINGLE);
//
//ObservableList<User> selectedItems = selectionModel.getSelectedItems();
//
//selectedItems.addListener(new ListChangeListener<User>() {
//	@Override
//	public void onChanged(Change<? extends User> change) {
//		selectionModel.select(1);
//		System.out.println("Selection changed: " + change.getList());
//		System.out.println("0");
//	}
//});