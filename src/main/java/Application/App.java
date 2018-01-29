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
import java.util.regex.*;
import org.apache.commons.validator.routines.EmailValidator;

public class App extends Application {

    private final TableView<Person> table = new TableView<>();
    private ObservableList<Person> data =
            FXCollections.observableArrayList(DbConnection.fillPerson());

    private Stage myStage;
    private Button updateBtn;
    private Button addBtn;
    private Button changeBtn;
    private Button deleteBtn;

    @Override
    public void start(Stage primaryStage) {
        myStage = new Stage();
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

        vbox.getChildren().addAll(addRefreshBtn(), addAddBtn(), addChangeBtn(), addDeleteBtn());
        return vbox;
    }

    private Button addRefreshBtn() {
        updateBtn = new Button("Refresh");
        updateBtn.setPrefSize(100, 20);
        updateBtn.setOnAction((ae) -> {
            data = FXCollections.observableArrayList(DbConnection.fillPerson());
            table.getItems().clear();
            table.setItems(data);
        });
        return updateBtn;
    }

    private Button addAddBtn() {
        addBtn = new Button("Add");
        addBtn.setPrefSize(100, 20);
        addBtn.setOnAction((ae) -> {
            addDialog();
        });
        return addBtn;
    }

    private Button addChangeBtn() {
        changeBtn = new Button("Change");
        changeBtn.setPrefSize(100, 20);
        changeBtn.setOnAction((ae) -> {
            //TODO
            changeBtn();
        });
        return changeBtn;
    }

    private Button addDeleteBtn() {
        deleteBtn = new Button("Delete");
        deleteBtn.setPrefSize(100, 20);
        deleteBtn.setOnAction((ae) -> {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(myStage);
            alert.setTitle("Warning");

            int index = table.getSelectionModel().getSelectedIndex();

            if (index == -1) {
                alert.setHeaderText("The record isn't selected");
                alert.setContentText("Please choose the record");
                alert.show();
            }
            else {
                int res = DbConnection.delete((table.getItems().get(index).getContactId()));
                if (res == 1) {
                    alert.setHeaderText("The record was deleted");
                    alert.show();
                }
                else {
                    alert.setHeaderText("ERROR");
                    alert.show();
                }
                updateBtn.fire();
            }
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

    private void changeBtn() {
        paintWin();

    }

    private void paintWin() {
        Stage addDialog = new Stage();
        addDialog.setTitle("Add new contact");
        addDialog.initModality(Modality.WINDOW_MODAL);
        addDialog.initOwner(myStage);
        BorderPane pane = new BorderPane();
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(pane, 300,200);

        //Set buttons
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);
        Button okBtn = new Button("OK");
        Button cancelBtn = new Button("Cancel");
        okBtn.setPrefWidth(100);
        cancelBtn.setPrefWidth(100);
        hBox.getChildren().addAll(okBtn, cancelBtn);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        //Create labels
        Label firstName = new Label("First Name");
        Label lastName = new Label("Last Name");
        Label phone = new Label("Phone");
        Label email = new Label("Email");

        //Add labels to pane
        gridPane.add(firstName, 0,0);
        gridPane.add(lastName, 0,1);
        gridPane.add(phone, 0,2);
        gridPane.add(email, 0,3);

        //Create text fields
        TextField tfFirstName = new TextField();
        tfFirstName.setPromptText("First Name");
        TextField tfLastName = new TextField();
        tfLastName.setPromptText("Last Name");
        TextField tfPhone = new TextField();
        tfPhone.setPromptText("+12345678910");
        TextField tfEmail = new TextField();
        tfEmail.setPromptText("test@test.com");

        //Add text fields
        gridPane.add(tfFirstName, 1,0);
        gridPane.add(tfLastName, 1,1);
        gridPane.add(tfPhone, 1,2);
        gridPane.add(tfEmail, 1,3);
        //cancelBtn.setOnAction((ae) -> addDialog.close());

        //Set action for okBtn
        okBtn.setOnAction((ae) -> {});
        pane.setBottom(hBox);
        pane.setCenter(gridPane);

        addDialog.setScene(scene);
        addDialog.showAndWait();

    }

    private void addDialog() {
        Stage addDialog = new Stage();
        addDialog.setTitle("Add new contact");
        addDialog.initModality(Modality.WINDOW_MODAL);
        addDialog.initOwner(myStage);
        BorderPane pane = new BorderPane();
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(pane, 300,200);

        //Set buttons
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);
        Button okBtn = new Button("OK");
        Button cancelBtn = new Button("Cancel");
        okBtn.setPrefWidth(100);
        cancelBtn.setPrefWidth(100);
        hBox.getChildren().addAll(okBtn, cancelBtn);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        //Create labels
        Label firstName = new Label("First Name");
        Label lastName = new Label("Last Name");
        Label phone = new Label("Phone");
        Label email = new Label("Email");

        //Add labels to pane
        gridPane.add(firstName, 0,0);
        gridPane.add(lastName, 0,1);
        gridPane.add(phone, 0,2);
        gridPane.add(email, 0,3);

        //Create text fields
        TextField tfFirstName = new TextField();
        tfFirstName.setPromptText("First Name");
        TextField tfLastName = new TextField();
        tfLastName.setPromptText("Last Name");
        TextField tfPhone = new TextField();
        tfPhone.setPromptText("+12345678910");
        TextField tfEmail = new TextField();
        tfEmail.setPromptText("test@test.com");

        //Add text fields
        gridPane.add(tfFirstName, 1,0);
        gridPane.add(tfLastName, 1,1);
        gridPane.add(tfPhone, 1,2);
        gridPane.add(tfEmail, 1,3);

        //Set action for cancelBtn
        cancelBtn.setOnAction((ae) -> addDialog.close());

        //Set action for okBtn
        okBtn.setOnAction((ae) -> {

            int isEmpty = 0;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(addDialog);
            alert.setTitle("Warning");

            if (tfFirstName.getText().isEmpty() | !isNameValid(tfFirstName.getText())) {
                isEmpty = 1;
            }
            else if (tfLastName.getText().isEmpty() | !isNameValid(tfLastName.getText())) {
                isEmpty = 2;
            }
            else if (tfPhone.getText().isEmpty() | !isPhoneValid(tfPhone.getText())) {
                isEmpty = 3;
            }
            else if (tfEmail.getText().isEmpty() |
                    !EmailValidator.getInstance().isValid(tfEmail.getText())) {
                isEmpty = 4;
            }

            switch (isEmpty) {
                case 1: {
                    alert.setHeaderText("First Name is invalid!");
                    alert.setContentText("Please recheck First Name");
                    alert.show();
                    break;
                }
                case 2: {
                    alert.setHeaderText("Last Name is invalid!");
                    alert.setContentText("Please recheck Last Name");
                    alert.show();
                    break;
                }
                case 3: {
                    alert.setHeaderText("Phone is invalid!");
                    alert.setContentText("Please recheck Phone");
                    alert.show();
                    break;
                }
                case 4: {
                    alert.setHeaderText("Email is invalid!");
                    alert.setContentText("Please recheck Email");
                    alert.show();
                    break;
                }
                default:
                    DbConnection.insertRecord(
                            tfFirstName.getText(),
                            tfLastName.getText(),
                            tfPhone.getText(),
                            tfEmail.getText()
                    );
                    addDialog.close();
                    updateBtn.fire();
                    break;
            }
        });

        pane.setBottom(hBox);
        pane.setCenter(gridPane);

        addDialog.setScene(scene);
        addDialog.showAndWait();
    }

    static boolean isPhoneValid(String phone) {
        Pattern pattern = Pattern.compile("[+]\\d{11}");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    static boolean isNameValid(String name) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    static boolean nonSqlInjection(String string) {
        boolean result = false;
        if (!string.contains(";") & (!string.contains("'"))) result = true;
        return result;
    }
}
