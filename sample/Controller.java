package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import sample.model.Album;
import sample.model.Artist;
import sample.model.Datasource;
import sample.model.Song;
import sun.awt.windows.ThemeReader;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public class Controller {
    @FXML
    private Button play;
    @FXML
    private UpdateController updateController;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TableView artistTable;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button close;
    @FXML
    public void listAlbums(){
        Task<ObservableList<Album>> task = new GetAllAlbums();
        artistTable.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }
    @FXML
    public void listSongs(){
        Task<ObservableList<Song>> task = new GetSongs();
        artistTable.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }
    @FXML
    public void handleCloseButton(){

        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
        Platform.exit();
        System.exit(0);

    }

    @FXML
    public void Delete(){
        final Object object = (Object) artistTable.getSelectionModel().getSelectedItem();
        if (object==null){
            System.out.println("Nothing selected");
        }
        if (object!=null){
            if (object.getClass().getSimpleName().equals("Album")){
                final Album album = (Album) artistTable.getSelectionModel().getSelectedItem();
                if (album==null){
                    System.out.println("No album selected");
                }
                Task<ObservableList<Boolean>> task= new Task<ObservableList<Boolean>>() {
                    @Override
                    protected ObservableList<Boolean> call() throws Exception {
                        return FXCollections.observableArrayList(Datasource.getInstance().deleteAlbum(album.getId()));
                    }
                };
                new Thread(task).start();
                artistTable.refresh();
            }
            if (object.getClass().getSimpleName().equals("Artist")){
                final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
                if (artist==null){
                    System.out.println("No artist selected");
                }

                Task<ObservableList<Boolean>> task = new Task<ObservableList<Boolean>>() {
                    @Override
                    protected ObservableList<Boolean> call() throws Exception {
                        return FXCollections.observableArrayList(Datasource.getInstance().deleteArtist(artist.getId()));
                    }
                };
                new Thread(task).start();
                artistTable.refresh();
            }
        }

    }
    @FXML
    public void listArtists() {
        Task<ObservableList<Artist>> task = new GetAllArtistsTask();
        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());
        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));

        new Thread(task).start();
    }
//    @FXML
//    public void del(){
//        final Object object = (Object) artistTable.getSelectionModel().getSelectedItem();
//        if (object==null){
//            System.out.println("Nothing selected");
//        }if (object!=null){
//            if (object.getClass().getSimpleName().equals("Album")){
//                Task<Integer> task = new Task<Integer>() {
//                    @Override
//                    protected Integer call() throws Exception {
//                        return (Datasource.getInstance().del());
//                    }
//                } ;
//                new Thread(task).start();
//            }
//            else {
//                System.out.println("Please select song from Album only");
//            }
//        }
//    }
    @FXML
    public void Play(){
        final Object object = (Object) artistTable.getSelectionModel().getSelectedItem();
        if (object==null){
            System.out.println("Nothing selected");
        }
        if (object!=null){
            if (object.getClass().getSimpleName().equals("Album")){
                final Album album = (Album) artistTable.getSelectionModel().getSelectedItem();
                if (album==null){
                    System.out.println("No album selected");
                }
                Task<ObservableList<Song>> task = new Task<ObservableList<Song>>() {
                    @Override
                    protected ObservableList<Song> call() throws Exception {
                        return FXCollections.observableArrayList(Datasource.getInstance().querySongFromAlbum(album.getName()));
                    }
                };

                artistTable.itemsProperty().bind(task.valueProperty());
                new Thread(task).start();
            }else if (object.getClass().getSimpleName().equals("Song")){
                String path = new File( "C:\\Users\\vamsi\\Music\\Music\\src\\sample\\Bommali-SenSongsMp3.Co.mp3").toURI().toString();
                MediaPlayer mediaPlayer = new MediaPlayer(new Media(path));
                mediaPlayer.play();

//                try {
//                    Media media = new Media(getClass().getResource(path).toURI().toString());
//                    MediaPlayer mediaPlayer = new MediaPlayer(media);
//                    mediaPlayer.play();
//                }catch (URISyntaxException e){
//                    e.getMessage();
//                }
            }
            else {
                System.out.println("Please select song from Album only");
            }
        }

    }
    @FXML
    public void listAlbumForArtist(){

        final Object object = (Object) artistTable.getSelectionModel().getSelectedItem();
        if (object==null){
            System.out.println("Nothing selected");
        }
        if (object!=null){
            if (object.getClass().getSimpleName().equals("Artist")){
                final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
                if (artist==null){
                    System.out.println("No Artist Selected");
                }
                Task<ObservableList<Album>> task = new Task<ObservableList<Album>>() {
                    @Override
                    protected ObservableList<Album> call() throws Exception {
                        return FXCollections.observableArrayList(Datasource.getInstance().queryAlbumForArtistId(artist.getId()));
                    }
                };
                artistTable.itemsProperty().bind(task.valueProperty());
                new Thread(task).start();
            }
            else {
                System.out.println("Select Artist ");
            }
        }

    }

    public void updateArtist(){
        Dialog<ButtonType> dialog =new Dialog<>();
//        dialog.initOwner(borderPane.getScene().getWindow());
        dialog.setTitle("Add song details");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("updateSong.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            UpdateController controller = fxmlLoader.getController();
            controller.updateSong();
            int i=0;
            int j=0;
            int k=0;
            Album album = new Album();
            Song song= new Song();
            Artist artist = new Artist();
            album.setId(i++);
            song.setAlbumId(i++);
            artist.setId(j++);
            album.setArtistId(j++);
            song.setId(k++);
            artistTable.refresh();
        }

//        final Artist artist = (Artist) artistTable.getItems().get(2);
//        Task<Boolean> task = new Task<Boolean>() {
//            @Override
//            protected Boolean call() throws Exception {
//                return Datasource.getInstance().updateArtistName(artist.getId(),"AC/DC");
//            }
//        };
//        task.setOnSucceeded(e -> {
//            if (task.valueProperty().get()){
//                artist.setName("AC/DC");
//                artistTable.refresh();
//            }
//        });
//        new Thread(task).start();
    }
}

class GetAllArtistsTask extends Task {

    @Override
    public ObservableList<Artist> call()  {
        return FXCollections.observableArrayList
                (Datasource.getInstance().queryArtists(Datasource.ORDER_BY_ASC));
    }
}
class GetAllAlbums extends Task{
    @Override
    public ObservableList<Album> call(){
        return FXCollections.observableArrayList(Datasource.getInstance().queryAlbums(Datasource.ORDER_BY_ASC));
    }
}
class GetSongs extends Task{
    @Override
    public ObservableList<Song> call(){
        return FXCollections.observableArrayList(Datasource.getInstance().querySongs(Datasource.ORDER_BY_ASC));
    }
}

