package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.AccessCD;
import model.CD;
import model.User;

public class CreateBill {

	public static GridPane nextPage() {
		AccessCD cdList = new AccessCD();

		GridPane gp = new GridPane();
		gp.setAlignment(Pos.TOP_LEFT);
		gp.setPrefSize(800, 500);
		gp.setPadding(new Insets(50, 50, 50, 50));
		gp.setVgap(10);
		gp.setHgap(10);

		final Label label = new Label("CD List");
		label.setFont(new Font("Arial", 20));

		TableView<CD> cdTable = new TableView<CD>();
		cdTable.setPlaceholder(new Label("No CDs stored"));

		TableColumn<CD, String> albumColumn = new TableColumn<>("Album");
		albumColumn.setCellValueFactory(new PropertyValueFactory<CD, String>("album"));
		albumColumn.setMinWidth(150);

		TableColumn<CD, String> artistColumn = new TableColumn<>("Artist");
		artistColumn.setCellValueFactory(new PropertyValueFactory<CD, String>("artist"));
		artistColumn.setMinWidth(150);

		TableColumn<CD, Integer> stockColumn = new TableColumn<>("Stock");
		stockColumn.setCellValueFactory(new PropertyValueFactory<CD, Integer>("stock"));
		stockColumn.setMinWidth(50);

		TableColumn<CD, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<CD, Double>("price"));
		priceColumn.setMinWidth(100);

		for (int i = 0; i < cdList.getCDs().size(); i++) {
			cdTable.getItems().add((CD) cdList.getCDs().get(i));
		}

		cdTable.getColumns().addAll(albumColumn, artistColumn, stockColumn, priceColumn);

		TableView<CD> billTable = new TableView<CD>();
//		billTable.setPlaceholder(new Label("No CDs stored"));

		TableColumn<CD, String> albumBillColumn = new TableColumn<>("Album");
		albumBillColumn.setCellValueFactory(new PropertyValueFactory<CD, String>("album"));
		albumBillColumn.setMinWidth(150);

		TableColumn<CD, String> artistBillColumn = new TableColumn<>("Artist");
		artistBillColumn.setCellValueFactory(new PropertyValueFactory<CD, String>("artist"));
		artistBillColumn.setMinWidth(150);

		TableColumn<CD, Double> priceBillColumn = new TableColumn<>("Price");
		priceBillColumn.setCellValueFactory(new PropertyValueFactory<CD, Double>("price"));
		priceBillColumn.setMinWidth(100);

		billTable.getColumns().addAll(albumBillColumn, artistBillColumn, priceBillColumn);

		Alert noAddSelection = new Alert(AlertType.INFORMATION);
		noAddSelection.setTitle("Error");
		noAddSelection.setHeaderText("Please select a CD to be added to the bill");

		Alert noRemoveSelection = new Alert(AlertType.INFORMATION);
		noRemoveSelection.setTitle("Error");
		noRemoveSelection.setHeaderText("Please select a CD to be removed from the bill");

		Alert noStock = new Alert(AlertType.INFORMATION);
		noStock.setTitle("Error");
		noStock.setHeaderText("No item in stock left, please notify a menager/admin!");

		Alert lowStock = new Alert(AlertType.INFORMATION);
		lowStock.setTitle("Error");
		lowStock.setHeaderText("No item in stock left, please notify a menager/admin!");

		Alert noSelection = new Alert(AlertType.INFORMATION);
		noSelection.setTitle("Error");
		noSelection.setHeaderText("No item in stock left, please notify a menager/admin!");

		Button addCDButton = new Button("Add CD");

		addCDButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				int i = -1;
				i = cdTable.getSelectionModel().getSelectedIndex();
//				System.out.println(i);
				CD cd = cdTable.getSelectionModel().getSelectedItem();
//				System.out.println(cd.toString());
				int stock = cd.getStock();
//				System.out.println(stock);

				if (i < 0) {
					noSelection.show();
				} else if (stock <= 5 && stock >= 1) {
					lowStock.show();
				} else if (stock == 0) {
					noStock.show();
				}
				if (i < 0) {
					noAddSelection.show();
				}
				if (i >= 0 && stock >= 1) {
					billTable.getItems().addAll(cd);
					stock -= 1;
					cdList.getCDs().get(i).setStock(stock);
//					System.out.println(cdList.getCDs().get(i).toString());
					cdList.setInFile();
					cdTable.getSelectionModel().clearSelection();
				}

			}

		});

		Button removeCDButton = new Button("Remove CD");

		removeCDButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				int i = -1;
				i = billTable.getSelectionModel().getSelectedIndex();
//				System.out.println(i);
				CD cd = billTable.getSelectionModel().getSelectedItem();
//				System.out.println(cd.toString());
				int stock = cd.getStock();
				if (i < 0) {
					noRemoveSelection.show();
				}
				if (i >= 0) {
					billTable.getItems().remove(i);
					stock += 1;
					cdList.getCDs().get(i).setStock(stock);
//					System.out.println(cdList.getCDs().get(i).toString());
					cdList.setInFile();
					billTable.getSelectionModel().clearSelection();
				}
			}

		});

		Button calculateButton = new Button("Calculate total");
		TextField totPriceField = new TextField();
		totPriceField.setEditable(false);

		calculateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				int size = billTable.getItems().size();
//				System.out.println(size);
				double totPrice = 0;
				for (int i = 0; i < size; i++) {
					totPrice += billTable.getItems().get(i).getPrice();
				}
				String stringTotPrice = String.valueOf(totPrice);
				totPriceField.setText(stringTotPrice);

			}

		});

		Button printButton = new Button("Print Bill");

		printButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				int size = billTable.getItems().size();
//				System.out.println(size);
				double totPrice = 0;
				for (int i = 0; i < size; i++) {
					totPrice += billTable.getItems().get(i).getPrice();
				}
				String stringTotPrice = String.valueOf(totPrice);

				long millis = System.currentTimeMillis();
				Date date = new Date(millis);

				File file = new File("C:\\Users\\User\\Desktop\\School\\Programs\\Java Stuff\\Bill.txt");
				try {
					FileWriter fw = new FileWriter(file);
					PrintWriter pw = new PrintWriter(fw);

					pw.println("--- Purchase Bill ----");
					pw.println();
					pw.println();
					pw.print("Items Bought");
					for (int i = 0; i < size; i++) {
						pw.println();
						pw.print("Item " + (i + 1) + "- ");
						pw.print("Album: " + billTable.getItems().get(i).getAlbum() + "  ");
						pw.print("Artist: " + billTable.getItems().get(i).getArtist() + "  ");
						pw.print("Price: " + billTable.getItems().get(i).getPrice() + "$");
					}

					pw.println();
					pw.println();
					pw.println("Total Items Bought: " + size);
					pw.println("Total Price: " + stringTotPrice + "$");
					pw.println();
					pw.println("--- Date Purchased: " + date + " ---");

					pw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				totPriceField.clear();

				billTable.getItems().clear();

			}

		});

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, cdTable, billTable, addCDButton, removeCDButton);
		gp.add(vbox, 0, 0);
		gp.add(calculateButton, 1, 0);
		gp.add(totPriceField, 1, 1);
		gp.add(printButton, 1, 2);

		return gp;
	}

}
