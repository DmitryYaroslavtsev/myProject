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

import java.sql.*;

public class App extends Application {

    //Variables for DB users
    private static String urlUsers = "jdbc:postgresql://localhost:5432/myProject";
    private static String login = "postgres";
    private static String pass = "postgres";
    private static String driver = "org.postgresql.Driver";

    private final TableView<Person> table = new TableView<>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
                    new Person("1", "Peter", "Belgy", "+79112345678","peter@pisem.net")
            );


    @Override
    public void start(Stage primaryStage) {
        Stage myStage = new Stage();
        myStage.setTitle("Application");

        BorderPane rootNode = new BorderPane();

        Scene myScene = new Scene(rootNode, 700, 300);

        myStage.setScene(myScene);

        rootNode.setLeft(addHBox());
        rootNode.setCenter(addTableView());
        myStage.show();
    }

    private VBox addHBox() {
        VBox vbox = new VBox(8);
        vbox.setPadding(new Insets(10));

        vbox.getChildren().addAll(addUpdateBtn(), addAddBtn(), addChangeBtn(), addDeleteBtn());
        return vbox;
    }

    private Button addUpdateBtn() {
        Button updateBtn = new Button("Update");
        updateBtn.setPrefSize(100, 20);
        updateBtn.setOnAction((ae) -> {
            //TODO
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
        //TableView table = new TableView();
        //table.setEditable(false);

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

        //
        //test();
        //
        table.setItems(data);
        table.getColumns().addAll(contactId, firstName, secondName, phone, email);
        return table;
    }

    static ResultSet request(String req) {
        ResultSet resultSet = null;
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(urlUsers, login, pass)) {
                Statement stmn = con.createStatement();
                resultSet = stmn.executeQuery(req);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
