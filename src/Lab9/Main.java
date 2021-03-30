package Lab9;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        TableView<Country> tableView = new TableView<>();
        ObservableList<Country> data =
                FXCollections.observableArrayList(
                        new Country("USA", "Washington DC", 280, true),
                        new Country("Canada","Ottawa", 32, true),
                        new Country("United Kingdom", "London", 60, true),
                        new Country("Germany","Berlin", 83, true),
                        new Country("France","Paris", 60, true));

        tableView.setItems(data);      //Adds the data to the table

        //Creating columns on the Table object.
        TableColumn countryColumn = new TableColumn("Country");
        countryColumn.setMinWidth(100);
        countryColumn.setCellValueFactory(
                new PropertyValueFactory<Country, String>("country"));

        TableColumn capitalColumn = new TableColumn("Capital");
        capitalColumn.setMinWidth(100);
        capitalColumn.setCellValueFactory(
                new PropertyValueFactory<Country, String>("capital"));

        TableColumn populationColumn = new TableColumn("Population (million)");
        populationColumn.setMinWidth(100);
        populationColumn.setCellValueFactory(
                new PropertyValueFactory<Country, String>("population"));

        TableColumn democraticColumn = new TableColumn("Democratic?");
        democraticColumn.setMinWidth(100);
        democraticColumn.setCellValueFactory(
                new PropertyValueFactory<Country, String>("democratic"));

        tableView.getColumns().addAll(countryColumn, capitalColumn, populationColumn, democraticColumn);

        //Creating a bar to allow manual additions to the table
        FlowPane flowPane = new FlowPane(3,3);
        TextField tfCountry = new TextField();   //TextField to get new country value
        TextField tfCapital = new TextField();   //new Capital
        TextField tfPopulation = new TextField();  //New population value
        CheckBox chkDemocratic = new CheckBox("Is Democratic?");  //New democratic value
        Button btAddRow = new Button("Add New Row");  //Button to add the new data
        Button btDelete = new Button("Delete");  //Button to delete data
        tfCountry.setPrefColumnCount(5);
        tfCapital.setPrefColumnCount(5);
        tfPopulation.setPrefColumnCount(5);

        flowPane.getChildren().addAll(new Label("Country: "), tfCountry, new Label("Capital: "), tfCapital,
                new Label("Population: "), tfPopulation, chkDemocratic, btAddRow, btDelete);

        btAddRow.setOnAction(actionEvent -> {
            data.add(new Country(tfCountry.getText(), tfCapital.getText(),
                    Double.parseDouble(tfPopulation.getText()), chkDemocratic.isSelected()));
            tfCountry.clear();
            tfCapital.clear();
            tfPopulation.clear();
        });

        btDelete.setOnAction(actionEvent -> {
            data.removeAll(tableView.getSelectionModel().getSelectedItem());
        });

        BorderPane borderPane = new BorderPane();  //create a pane in the scene
        borderPane.setCenter(tableView);  //add table to center of the pane
        borderPane.setBottom(flowPane);  //add flowPane to bottom of the pane

        Scene scene = new Scene(borderPane, 500,300);
        primaryStage.setTitle("Exercise 31.22");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static class Country {
        private final SimpleStringProperty country;
        private final SimpleStringProperty capital;
        private final SimpleDoubleProperty population;
        private final SimpleBooleanProperty democratic;

        private Country(String country, String capital, double population, boolean democratic){
            this.country = new SimpleStringProperty(country);
            this.capital = new SimpleStringProperty(capital);
            this.population = new SimpleDoubleProperty(population);
            this.democratic = new SimpleBooleanProperty(democratic);
        }

        public String getCountry() {
            return country.get();
        }
        public void setCountry(String country){
            this.country.set(country);
        }

        public String getCapital() {
            return capital.get();
        }

        public void setCapital(String capital){
            this.capital.set(capital);
        }

        public double getPopulation() {
            return population.get();
        }

        public void setPopulation(double population){
            this.population.set(population);
        }

        public boolean isDemocratic() {
            return democratic.get();
        }

        public void setDemocratic(boolean democratic){
            this.democratic.set(democratic);
        }
    }
}
