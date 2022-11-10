package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainViewContainer extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		GridPane gp = new GridPane();
		gp = (GridPane) Login.login();
		
		
        Image icon = new Image("icon.jpg");
        Scene scene = new Scene(gp);
		stage.getIcons().add(icon);
		stage.setResizable(false); 
		stage.setTitle("CD STORE");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main (String[] args) {
		
//		User user1 = new User("enkel", "enkel", "enkel", 19, "male", 30000, 1, 0);
//		User user2 = new User("alan", "alan", "alan", 20, "sigma", 30000, 2, 0);
//		
//		UserList.getUsers().add(user1);
//		UserList.add(user2);
//		
//		try {
//			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\User\\eclipse\\java-2021-09\\OPP Project\\src\\UserInfo.ser");
//			ObjectOutputStream out = new ObjectOutputStream(fileOut);
//			out.writeObject(UserList);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		
		
		launch(args);
	}


}
