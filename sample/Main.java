package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import sample.model.Datasource;

import java.awt.event.ActionEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 800, 600,Color.web("#81c483"));
        primaryStage.setScene(scene);
        primaryStage.show();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
//        Parent root = loader.load();
//        Controller controller = loader.getController();
//        controller.listArtists();
////        controller.listAlbums();
//
//        primaryStage.setTitle("Music Database");
//        primaryStage.setScene(new Scene(root, 800, 600));
//        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        if(!Datasource.getInstance().open()) {
            System.out.println("FATAL ERROR: Couldn't connect to database");
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Datasource.getInstance().close();
    }
    public static void getStage(){

    }

    public static void main(String[] args) {
        launch(args);
    }
}
