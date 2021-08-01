package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Song {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty albumId;
    private SimpleIntegerProperty track;
    public Song(){
        this.id = new SimpleIntegerProperty();
        this.name=new SimpleStringProperty();
        this.albumId = new SimpleIntegerProperty();
        this.track = new SimpleIntegerProperty();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAlbumId() {
        return albumId.get();
    }

    public SimpleIntegerProperty albumIdProperty() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId.set(albumId);
    }

    public int getTrack() {
        return track.get();
    }

    public SimpleIntegerProperty trackProperty() {
        return track;
    }

    public void setTrack(int track) {
        this.track.set(track);
    }
}
