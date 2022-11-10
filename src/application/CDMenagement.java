package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.AccessCD;
import model.CD;

public class CDMenagement {

	public static GridPane nextPage() {
		AccessCD cdList = new AccessCD();
//		System.out.println(cdList.getCDs().toString());

		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setPrefSize(800, 600);

		final Label label = new Label("CD List");
		label.setFont(new Font("Arial", 20));

		TableView<CD> cdTable = new TableView<CD>();
		cdTable.setPlaceholder(new Label("No CDs stored"));
		cdTable.setEditable(true);

		TableColumn<CD, Integer> IDColumn = new TableColumn<>("CD ID");
		IDColumn.setCellValueFactory(new PropertyValueFactory<CD, Integer>("cdID"));
		IDColumn.setMinWidth(50);

		TableColumn<CD, String> albumColumn = new TableColumn<>("Album");
		albumColumn.setCellValueFactory(new PropertyValueFactory<CD, String>("album"));
		albumColumn.setCellFactory(TextFieldTableCell.<CD>forTableColumn());
		albumColumn.setMinWidth(150);
		
		albumColumn.setOnEditCommit(new EventHandler<CellEditEvent<CD, String>>() {

			@Override
			public void handle(CellEditEvent<CD, String> e) {
				CD cd = e.getRowValue();
				cd.setAlbumName(e.getNewValue());
				int i = cd.getCdID();
				cdList.getCDs().remove(i);
				cdList.getCDs().add(i, cd);
				cdList.setInFile();
			}

		});

		TableColumn<CD, String> artistColumn = new TableColumn<>("Artist");
		artistColumn.setCellValueFactory(new PropertyValueFactory<CD, String>("artist"));
		artistColumn.setCellFactory(TextFieldTableCell.<CD>forTableColumn());
		artistColumn.setMinWidth(150);
		
		artistColumn.setOnEditCommit(new EventHandler<CellEditEvent<CD, String>>() {

			@Override
			public void handle(CellEditEvent<CD, String> e) {
				CD cd = e.getRowValue();
				cd.setArtist(e.getNewValue());
				int i = cd.getCdID();
				cdList.getCDs().remove(i);
				cdList.getCDs().add(i, cd);
				cdList.setInFile();
			}

		});

		TableColumn<CD, String> genreColumn = new TableColumn<>("Genre");
		genreColumn.setCellValueFactory(new PropertyValueFactory<CD, String>("genre"));
		genreColumn.setCellFactory(ComboBoxTableCell.<CD, String>forTableColumn("Pop", "Rock", "Jazz", "R&B"));
		genreColumn.setMinWidth(100);
		
		genreColumn.setOnEditCommit(new EventHandler<CellEditEvent<CD, String>>() {

			@Override
			public void handle(CellEditEvent<CD, String> e) {
				CD cd = e.getRowValue();
				cd.setGenre(e.getNewValue());
				int i = cd.getCdID();
				cdList.getCDs().remove(i);
				cdList.getCDs().add(i, cd);
				cdList.setInFile();
			}

		});

		TableColumn<CD, Integer> stockColumn = new TableColumn<>("Stock");
		stockColumn.setCellValueFactory(new PropertyValueFactory<CD, Integer>("stock"));
		stockColumn.setCellFactory(TextFieldTableCell.<CD, Integer>forTableColumn(new IntegerStringConverter()));
		stockColumn.setMinWidth(50);
		
		stockColumn.setOnEditCommit(new EventHandler<CellEditEvent<CD, Integer>>() {

			@Override
			public void handle(CellEditEvent<CD, Integer> e) {
				CD cd = e.getRowValue();
				cd.setStock(e.getNewValue());
				int i = cd.getCdID();
				cdList.getCDs().remove(i);
				cdList.getCDs().add(i, cd);
				cdList.setInFile();
			}

		});

		TableColumn<CD, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<CD, Double>("price"));
		priceColumn.setCellFactory(TextFieldTableCell.<CD, Double>forTableColumn(new DoubleStringConverter()));
		priceColumn.setMinWidth(100);
		
		priceColumn.setOnEditCommit(new EventHandler<CellEditEvent<CD, Double>>() {

			@Override
			public void handle(CellEditEvent<CD, Double> e) {
				CD cd = e.getRowValue();
				cd.setPrice(e.getNewValue());
				int i = cd.getCdID();
				cdList.getCDs().remove(i);
				cdList.getCDs().add(i, cd);
				cdList.setInFile();
			}

		});

		for (int i = 0; i < cdList.getCDs().size(); i++) {
			cdTable.getItems().add((CD) cdList.getCDs().get(i));
		}
		

		cdTable.getColumns().addAll(IDColumn, albumColumn, artistColumn, genreColumn, stockColumn, priceColumn);

		Button addCDButton = new Button("Add CD");
		Button deleteButton = new Button("Delete CD");

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, cdTable, addCDButton, deleteButton);
		gp.add(vbox, 0, 0);
		
		Alert noSelection = new Alert(AlertType.INFORMATION);
		noSelection.setTitle("Error");
		noSelection.setHeaderText("Please select a CD to be deleted");
		
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				int i = -1;
				i = cdTable.getSelectionModel().getSelectedIndex();
				if (i < 0) {
					noSelection.show();
				}
				if (i >= 0) {
					cdList.getCDs().remove(i);
					int size = cdList.getCDs().size();
					cdTable.getItems().remove(i);
					cdTable.getSelectionModel().clearSelection();

					for (int j = i; j < size; j++) {
						int ID = cdList.getCDs().get(j).getCdID();
						int newID = ID - 1;
						System.out.println(ID);
						cdList.getCDs().get(j).setCdID(newID);
					}
					cdList.setInFile();
				}
			}

		});

		addCDButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				Stage newStage = new Stage();
				Scene scene = new Scene(CDAdd.nextPage());
				newStage.setScene(scene);
				//((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
				newStage.show();

			}

		});
		
		return gp;
	}

}
