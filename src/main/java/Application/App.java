package Application;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.*;

public class App extends Application {

    private final TableView<Person> table = new TableView<>();
    private ObservableList<Person> data =
            FXCollections.observableArrayList(DbConnection.fillPerson());

    BorderPane rootNode;

    @Override
    public void start(Stage primaryStage) {
        Stage myStage = new Stage();
        myStage.setTitle("Application");

        rootNode = new BorderPane();

        Scene myScene = new Scene(rootNode, 700, 300);

        myStage.setScene(myScene);

        rootNode.setLeft(addHBox());
        rootNode.setCenter(addTableView());
        myStage.show();
    }

    private VBox addHBox() {
        VBox vbox = new VBox(8);
        vbox.setPadding(new Insets(10));

        vbox.getChildren().addAll(addRefreshBtn(), addAddBtn(), addChangeBtn(), addDeleteBtn());
        return vbox;
    }

    private Button addRefreshBtn() {
        Button updateBtn = new Button("Refresh");
        updateBtn.setPrefSize(100, 20);
        updateBtn.setOnAction((ae) -> {
            data = FXCollections.observableArrayList(DbConnection.fillPerson());
            table.getItems().clear();
            table.setItems(data);
        });
        return updateBtn;
    }

    private Button addAddBtn() {
        Button addBtn = new Button("Add");
        addBtn.setPrefSize(100, 20);
        addBtn.setOnAction((ae) -> {
            //TODO
        });
        return addBtn;
    }

    private Button addChangeBtn() {
        Button changeBtn = new Button("Change");
        changeBtn.setPrefSize(100, 20);
        changeBtn.setOnAction((ae) -> {
            //TODO
        });
        return changeBtn;
    }

    private Button addDeleteBtn() {
        Button deleteBtn = new Button("Delete");
        deleteBtn.setPrefSize(100, 20);
        deleteBtn.setOnAction((ae) -> {
            //TODO
        });
        return deleteBtn;
    }

    private TableView<Person> addTableView() {

        TableColumn contactId = new TableColumn("Contact_ID");
        contactId.setMinWidth(100);
        contactId.setCellValueFactory(new PropertyValueFactory<>("contactId"));

        TableColumn firstName = new TableColumn("First Name");
        firstName.setMinWidth(100);
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn secondName = new TableColumn("Second Name");
        secondName.setMinWidth(100);
        secondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        TableColumn phone = new TableColumn("Phone Number");
        phone.setMinWidth(100);
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn email = new TableColumn("Email");
        email.setMinWidth(180);
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(data);
        table.getColumns().addAll(contactId, firstName, secondName, phone, email);
        return table;
    }
}
