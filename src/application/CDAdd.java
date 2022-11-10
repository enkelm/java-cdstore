package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.AccessCD;
import model.AccessUser;
import model.CD;
import model.User;

public class CDAdd {

	public static GridPane nextPage() {
		AccessCD cdList = new AccessCD();
		System.out.println(cdList.getCDs().toString());

		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setPrefSize(500, 500);
		gp.setPadding(new Insets(50, 50, 50, 50));
		gp.setVgap(10);
		gp.setHgap(10);

		Label albumLabel = new Label("Album");
		TextField albumField = new TextField();

		Label artistLabel = new Label("Artist");
		TextField artistField = new TextField();

		Label genreLabel = new Label("Genre");
		ObservableList<String> genreOptions = FXCollections.observableArrayList("Pop", "Rock", "Jazz", "R&B");
		final ComboBox genreComboBox = new ComboBox(genreOptions);
		genreComboBox.setPromptText("Select a genre");

		Label stockLabel = new Label("In Stock");
		TextField stockField = new TextField();

		Label priceLabel = new Label("Price");
		TextField priceField = new TextField();

		gp.add(albumLabel, 0, 1);
		gp.add(albumField, 1, 1);
		gp.add(artistLabel, 0, 2);
		gp.add(artistField, 1, 2);
		gp.add(genreLabel, 0, 3);
		gp.add(genreComboBox, 1, 3);
		gp.add(stockLabel, 0, 4);
		gp.add(stockField, 1, 4);
		gp.add(priceLabel, 0, 5);
		gp.add(priceField, 1, 5);
		
		BooleanBinding bb = new BooleanBinding() {
		    {
		        super.bind(albumField.textProperty(),
		                artistField.textProperty(),
		                stockField.textProperty(),
		                priceField.textProperty());
		    }

		    @Override
		    protected boolean computeValue() {
		        return (albumField.getText().isEmpty()
		                || artistField.getText().isEmpty()
		                || stockField.getText().isEmpty()
		                || priceField.getText().isEmpty());
		    }
		};

		Button saveButton = new Button("Save CD");
		gp.add(saveButton, 1, 6);
		saveButton.disableProperty().bind(bb);
		
		

		saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				int cdID = cdList.getCDs().size();
				String album = albumField.getText();
				String artist = artistField.getText();
				String genre = (String) genreComboBox.getValue();
				int stock = Integer.parseInt(stockField.getText());
				int price = Integer.parseInt(priceField.getText());

				CD cd = new CD(cdID, album, artist, genre, stock, price);

				cdList.getCDs().add(cd);
				cdList.setInFile();
				
				albumField.clear();
				artistField.clear();
				stockField.clear();
				priceField.clear();
				albumField.requestFocus();

			}

		});

		return gp;
	}

}
