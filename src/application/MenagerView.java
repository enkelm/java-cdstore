package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.AccessUser;

public class MenagerView {
	
	public static GridPane nextPage() {
		AccessUser UserList = new AccessUser();
		
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setPrefSize(1000, 800);
		gp.setVgap(10);
		gp.setHgap(10);



		Button button1 = new Button("CD Menagement");
		gp.add(button1, 0, 0);
		
		Button button2 = new Button("Generate a Bill");
		gp.add(button2, 0, 1);
		//button3.setVisible(false);  
		
		Button logout = new Button("Logout");
		gp.add(logout, 0, 6);


		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				Stage newStage = new Stage();
				Scene scene = new Scene(CDMenagement.nextPage());
				newStage.setScene(scene);
				// ((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
				newStage.show();

			}

		});
		
		button2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				Stage newStage = new Stage();
				Scene scene = new Scene(CreateBill.nextPage());
				newStage.setScene(scene);
				// ((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
				newStage.show();

			}

		});
		
		logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				Stage newStage = new Stage();
				Scene scene = new Scene(Login.login());
				newStage.setScene(scene);
				((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
				newStage.show();

			}

		});

		return gp;
	}

}
