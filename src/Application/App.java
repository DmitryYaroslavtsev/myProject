package Application;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class App extends Application {

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

    private TableView addTableView() {
        TableView table = new TableView();
        table.setEditable(false);

        TableColumn contactId = new TableColumn("Contact_ID");
        contactId.setMinWidth(100);

        TableColumn firstName = new TableColumn("First Name");
        firstName.setMinWidth(100);

        TableColumn secondName = new TableColumn("Second Name");
        secondName.setMinWidth(100);

        TableColumn phone = new TableColumn("Phone Number");
        phone.setMinWidth(100);

        TableColumn email = new TableColumn("Email");
        email.setMinWidth(180);


        table.getColumns().addAll(contactId, firstName, secondName, phone, email);
        return table;
    }
}
