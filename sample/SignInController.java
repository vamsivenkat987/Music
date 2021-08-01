package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.model.Datasource;

import javax.naming.Name;
import java.sql.SQLException;

public class SignInController {
    @FXML
    private TextField name;
    @FXML
    private TextField NewPassword;
    @FXML
    private TextField ConfirmPassword;
    @FXML
    public void CreateNewUser() throws SQLException {
        String Name = name.getText().trim();
        String Pass = NewPassword.getText().trim();
        String NewPass = ConfirmPassword.getText().trim();
        if (Pass.equals(NewPass)) {
            Datasource.getInstance().addUser(Name, Pass);
        }else {
            System.out.println("Please type two passwords same");
        }
    }
}
