package sample;

import javafx.concurrent.Task;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import sample.model.Album;
import sample.model.Artist;
import sample.model.Datasource;
import sample.model.Song;

import java.security.SecureRandom;


public class UpdateController {
    @FXML
    private TextField Album;
    @FXML
    private TextField Artist;
    @FXML
    private TextField Title;
    @FXML
    private TextField TrackNumber;
    public void updateSong(){
        sample.model.Album album = new Album();
        Artist artist = new Artist();
        Song song = new Song();

        String AlbumName = Album.getText().trim();
        album.setName(AlbumName);
        String ArtistName = Artist.getText().trim();
        artist.setName(ArtistName);
        String title = Title.getText().trim();
        song.setName(title);
        String track = TrackNumber.getText().trim();
        int num = Integer.parseInt(track);
        song.setTrack(num);
        Datasource.getInstance().insertSong(title,ArtistName,AlbumName,num);

    }

}
