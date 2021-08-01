package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.model.Datasource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;


public class loginController {

    @FXML
    private Button SignInButton;
    @FXML
    private TextField emailIdField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button closeButton;
    @FXML
    public Stage closeLogin(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        return stage;
    }
    @FXML
    public void setSignInButton() throws SQLException {
        Dialog<ButtonType> dialog =new javafx.scene.control.Dialog<>();
//        dialog.initOwner(borderPane.getScene().getWindow());
        dialog.setTitle("Create New Account");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("SignIn.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("The data was not updated");
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            SignInController signIn = fxmlLoader.getController();
            signIn.CreateNewUser();
        }

    }
    @FXML
    public void login() throws SQLException{
        if (Datasource.getInstance().getCheckUser(emailIdField.getText())!=null){
            String pass = Datasource.getInstance().CheckPass(emailIdField.getText());
            if (passwordField.getText().equals(pass)){
                Stage primaryStage= new Stage();
                System.out.println("Success");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
                try{
                    Parent root = loader.load();
                    Controller controller = loader.getController();
                    controller.listArtists();
                    primaryStage.setTitle("Music Database");
                    primaryStage.setScene(new Scene(root, 800, 600));
                    primaryStage.show();
                }catch (IOException e){
                    e.printStackTrace();
                    return;
                }
            }else {
                System.out.println("Password was incorrect");
            }
        }else {
            System.out.println("This email or password was wrong");
        }
    }
}
