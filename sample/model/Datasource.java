package sample.model;

import java.lang.ref.PhantomReference;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Datasource {

    public static final String DB_NAME = "music.db";

    
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\vamsi\\Music\\NewDataBase\\" + DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_PASSWORD = "password";


    public static final String QUERY_ALBUMS_BY_ARTIST_START =
            "SELECT " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME + " FROM " + TABLE_ALBUMS +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
                    " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
                    " WHERE " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " = \"";

    public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
            " ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    public static final String QUERY_ARTIST_FOR_SONG_START =
            "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
                    TABLE_SONGS + "." + COLUMN_SONG_TRACK + " FROM " + TABLE_SONGS +
                    " INNER JOIN " + TABLE_ALBUMS + " ON " +
                    TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
                    " WHERE " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " = \"";

    public static final String QUERY_ARTIST_FOR_SONG_SORT =
            " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";

    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE VIEW IF NOT EXISTS " +
            TABLE_ARTIST_SONG_VIEW + " AS SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " AS " + COLUMN_SONG_ALBUM + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK + ", " + TABLE_SONGS + "." + COLUMN_SONG_TITLE +
            " FROM " + TABLE_SONGS +
            " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS +
            "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
            " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
            " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
            " ORDER BY " +
            TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK;
    public static final String NEW_TABLE = "User";
    public static final String ADD_NEW_USER = "INSERT INTO "+NEW_TABLE+" ("+ COLUMN_USER_NAME+","+COLUMN_USER_PASSWORD+") VALUES(?,?) ";


    //SELECT songs.title FROM songs INNER JOIN albums ON songs.album=albums._id WHERE albums.name="ALVP"
    public static final String QUERY_SONG_BY_ALBUM = "SELECT "+TABLE_SONGS+"."+COLUMN_SONG_TITLE+" FROM " + TABLE_SONGS +
            " INNER JOIN "+TABLE_ALBUMS+" ON "+TABLE_SONGS+"."+COLUMN_SONG_ALBUM+" = "+TABLE_ALBUMS+"."+COLUMN_ALBUM_ID+
            " WHERE " + TABLE_ALBUMS+ "."+COLUMN_ALBUM_NAME+ " = ? ";

    public static final String QUERY_VIEW_SONG_INFO = "SELECT " + COLUMN_ARTIST_NAME + ", " +
            COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = \"";

    public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT " + COLUMN_ARTIST_NAME + ", " +
            COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = ?";


    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS +
            '(' + COLUMN_ARTIST_NAME + ") VALUES(?)";
    public static final String INSERT_ALBUMS = "INSERT INTO " + TABLE_ALBUMS +
            '(' + COLUMN_ALBUM_NAME + ", " + COLUMN_ALBUM_ARTIST + ") VALUES(?, ?)";

    public static final String INSERT_SONGS = "INSERT INTO " + TABLE_SONGS +
            '(' + COLUMN_SONG_TRACK + ", " + COLUMN_SONG_TITLE + ", " + COLUMN_SONG_ALBUM +
            ") VALUES(?, ?, ?)";

    public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID + " FROM " +
            TABLE_ARTISTS + " WHERE " + COLUMN_ARTIST_NAME + " = ?";

    public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " FROM " +
            TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + " = ?";

    public static final String CHECK_USER = "SELECT * FROM " +NEW_TABLE+ " WHERE "+COLUMN_USER_NAME+" =? ";
    public static final String CHECK_PASSWORD = "SELECT "+ "password" +" FROM " +NEW_TABLE+ " WHERE "+COLUMN_USER_NAME+" =? ";

    public static final String QUERY_ALBUMS_BY_ARTIST_ID = "SELECT * FROM " + TABLE_ALBUMS +
            " WHERE " + COLUMN_ALBUM_ARTIST + " = ? ORDER BY " + COLUMN_ALBUM_NAME + " COLLATE NOCASE";

    public static final String UPDATE_ARTIST_NAME = "UPDATE "+TABLE_ARTISTS+" SET "+COLUMN_ARTIST_NAME+"= ? WHERE "+
            COLUMN_ARTIST_ID+"= ?";
    public static final String DELETE_ARTIST_NAME = "DELETE FROM "+TABLE_ARTISTS+" WHERE "+COLUMN_ARTIST_ID+"= ?";
    public static final String DELETE_ALBUM_NAME = "DELETE FROM "+TABLE_ALBUMS+" WHERE "+COLUMN_ALBUM_ID+"= ?";



    private Connection conn;
    private PreparedStatement newUser;
    private PreparedStatement querySongInfoView;
    private PreparedStatement updateArtistName;
    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;
    private PreparedStatement deleteArtist;
    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;
    private PreparedStatement queryAlbumsByArtistId;
    private PreparedStatement deleteAlbum;
    private PreparedStatement querySongfromAlbum;
    private PreparedStatement checkName;
    private PreparedStatement checkPassword;

    private static Datasource instance = new Datasource();

    private Datasource() {

    }

    public static Datasource getInstance() {
        return instance;
    }

    public boolean open() {
        try {

            conn = DriverManager.getConnection(CONNECTION_STRING);
            checkName = conn.prepareStatement(CHECK_USER);
            newUser = conn.prepareStatement(ADD_NEW_USER);
            querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS, Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(INSERT_SONGS);
            queryArtist = conn.prepareStatement(QUERY_ARTIST);
            queryAlbum = conn.prepareStatement(QUERY_ALBUM);
            queryAlbumsByArtistId = conn.prepareStatement(QUERY_ALBUMS_BY_ARTIST_ID);
            updateArtistName=conn.prepareStatement(UPDATE_ARTIST_NAME);
            deleteArtist = conn.prepareStatement(DELETE_ARTIST_NAME);
            deleteAlbum=conn.prepareStatement(DELETE_ALBUM_NAME);
            querySongfromAlbum=conn.prepareStatement(QUERY_SONG_BY_ALBUM);
            checkPassword = conn.prepareStatement(CHECK_PASSWORD);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }


    public void close() {
        try {
            if (checkPassword!=null){
                checkPassword.close();
            }
            if (checkName!=null){
                checkName.close();
            }
            if (querySongfromAlbum!=null){
                querySongfromAlbum.close();
            }
            if (newUser!=null){
                newUser.close();
            }
            if (deleteAlbum!=null){
                deleteAlbum.close();
            }
            if (deleteArtist!=null){
                deleteArtist.close();
            }
            if(querySongInfoView != null) {
                querySongInfoView.close();
            }

            if(insertIntoArtists != null) {
                insertIntoArtists.close();
            }
            if (updateArtistName!=null){updateArtistName.close();}
            if(insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }

            if(insertIntoSongs !=  null) {
                insertIntoSongs.close();
            }

            if(queryArtist != null) {
                queryArtist.close();
            }

            if(queryAlbum != null) {
                queryAlbum.close();
            }

            if(queryAlbumsByArtistId != null) {
                queryAlbumsByArtistId.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }
    public String CheckPass(String name ) throws SQLException{
        checkPassword.setString(1,name);
        ResultSet resultSet = checkPassword.executeQuery();
        if (resultSet.next()){
            return resultSet.getString(1);
        }else {
            return null;
        }

    }
    public List<Album> queryAlbumForArtistId(int id){
        try {
            queryAlbumsByArtistId.setInt(1,id);
            ResultSet resultSet = queryAlbumsByArtistId.executeQuery();
            List<Album> albums = new ArrayList<>();
            while (resultSet.next()){
                Album album = new Album();
                album.setId(resultSet.getInt(1));
                album.setName(resultSet.getString(2));
                album.setArtistId(id);
                albums.add(album);
            }
            return albums;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public List<User> getCheckUser(String name) throws SQLException{
        checkName.setString(1,name);
        ResultSet resultSet = checkName.executeQuery();
        List<User> users = new ArrayList<>();
        if (resultSet.next()){
            User user = new User();
            user.setName(resultSet.getString(1));
            user.setPass(resultSet.getString(2));
            users.add(user);
            return users;
        }else {return null;}

    }

    public void addUser(String name, String password) throws SQLException{
        if (getCheckUser(name)!=null){
            System.out.println("Not updated");
        }else {
            try {
                conn.setAutoCommit(false);
                newUser.setString(1,name);
                newUser.setString(2,password);
                int affectedRows= newUser.executeUpdate();
                if(affectedRows == 1) {
                    conn.commit();
                } else {
                    throw new SQLException("The song insert failed");
                }
            }catch(Exception e) {
                System.out.println("Insert User exception: " + e.getMessage());
                try {
                    System.out.println("Performing rollback");
                    conn.rollback();
                } catch(SQLException e2) {
                    System.out.println("Oh boy! Things are really bad! " + e2.getMessage());
                }
            } finally {
                try {
                    System.out.println("Resetting default commit behavior");
                    conn.setAutoCommit(true);
                } catch(SQLException e) {
                    System.out.println("Couldn't reset auto-commit! " + e.getMessage());
                }

            }

        }
    }
//    public int del(){
//        //"DELETE FROM "+TABLE_ARTISTS+" WHERE "+COLUMN_ARTIST_ID+"= ?";
//        for (int i=1648;i<5363;i++){
//            StringBuilder stringBuilder = new StringBuilder("DELETE FROM ");
//            stringBuilder.append(TABLE_SONGS);
//            stringBuilder.append(" WHERE ");
//            stringBuilder.append(COLUMN_SONG_ID);
//            stringBuilder.append("=");
//            stringBuilder.append(i);
//            try (Statement statement = conn.createStatement()){
//                statement.execute(stringBuilder.toString());
//            }catch (SQLException e){}
//        }
//        System.out.println("Completed");
//        return 1;
//
//    }
    public List<Artist> queryArtists(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Artist> artists = new ArrayList<>();
            while (results.next()) {
//                try {
//                    Thread.sleep(20);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
                Artist artist = new Artist();
                artist.setId(results.getInt(INDEX_ARTIST_ID));
                artist.setName(results.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }

            return artists;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

//    public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {
//
//        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
//        sb.append(artistName);
//        sb.append("\"");
//
//        if (sortOrder != ORDER_BY_NONE) {
//            sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
//            if (sortOrder == ORDER_BY_DESC) {
//                sb.append("DESC");
//            } else {
//                sb.append("ASC");
//            }
//        }
//        try (Statement statement = conn.createStatement();
//             ResultSet results = statement.executeQuery(sb.toString())) {
//
//            List<String> albums = new ArrayList<>();
//            while (results.next()) {
//                albums.add(results.getString(1));
//            }
//
//            return albums;
//
//        } catch (SQLException e) {
//            System.out.println("Query failed: " + e.getMessage());
//            return null;
//        }
//    }
//    public void querySongsMetadata() {
//        String sql = "SELECT * FROM " + TABLE_SONGS;
//
//        try (Statement statement = conn.createStatement();
//             ResultSet results = statement.executeQuery(sql)) {
//
//            ResultSetMetaData meta = results.getMetaData();
//            int numColumns = meta.getColumnCount();
//            for (int i = 1; i <= numColumns; i++) {
//                System.out.format("Column %d in the songs table is names %s\n",
//                        i, meta.getColumnName(i));
//            }
//        } catch (SQLException e) {
//            System.out.println("Query failed: " + e.getMessage());
//        }
//    }

    public List<Album> queryAlbums(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ALBUMS);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ALBUM_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Album> albums = new ArrayList<>();
            while (results.next()) {
//                try {
//                    Thread.sleep(20);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
                Album album = new Album();
                album.setArtistId(results.getInt(INDEX_ALBUM_ARTIST));
                album.setName(results.getString(INDEX_ALBUM_NAME));
                album.setId(results.getInt(INDEX_ALBUM_ID));
                albums.add(album);
//                Artist artist = new Artist();
//                artist.setId(results.getInt(INDEX_ARTIST_ID));
//                artist.setName(results.getString(INDEX_ARTIST_NAME));
//                artists.add(artist);
            }

            return albums;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
    public List<Song> querySongs(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_SONGS);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_SONG_TITLE);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Song> albums = new ArrayList<>();
            while (results.next()) {
//                try {
//                    Thread.sleep(20);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
                Song song = new Song();
                song.setId(results.getInt(1));
                song.setTrack(results.getInt(2));
                song.setName(results.getString(3));
                song.setAlbumId(results.getInt(4));
                albums.add(song);
//                Artist artist = new Artist();
//                artist.setId(results.getInt(INDEX_ARTIST_ID));
//                artist.setName(results.getString(INDEX_ARTIST_NAME));
//                artists.add(artist);
            }

            return albums;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

//    public int getCount(String table) {
//        String sql = "SELECT COUNT(*) AS count FROM " + table;
//        try (Statement statement = conn.createStatement();
//             ResultSet results = statement.executeQuery(sql)) {
//
//            int count = results.getInt("count");
//
//            System.out.format("Count = %d\n", count);
//            return count;
//        } catch (SQLException e) {
//            System.out.println("Query failed: " + e.getMessage());
//            return -1;
//        }
//    }

//    public boolean createViewForSongArtists() {
//
//        try (Statement statement = conn.createStatement()) {
//
//            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
//            return true;
//
//        } catch (SQLException e) {
//            System.out.println("Create View failed: " + e.getMessage());
//            return false;
//        }
//    }
    public List<Song> querySongFromAlbum(String name){
        try {
            querySongfromAlbum.setString(1,name);
            ResultSet resultSet = querySongfromAlbum.executeQuery();
            List<Song> songs = new ArrayList<>();
            if (resultSet.next()){
                Song song = new Song();
                song.setName(resultSet.getString(1));
                songs.add(song);
            }
            return songs;
        }catch (SQLException e){e.printStackTrace(); return null;}
    }
    public boolean deleteArtist(int id){
       try {
           deleteArtist.setInt(1,id);
           deleteArtist.execute();
           return true;

       }catch (SQLException e){
           e.printStackTrace();
           return false;
       }
    }
    public boolean deleteAlbum(int id){
        try {
            deleteAlbum.setInt(1,id);
            deleteAlbum.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    private int insertArtist(String name) throws SQLException {

        queryArtist.setString(1, name);
        ResultSet results = queryArtist.executeQuery();
        if(results.next()) {
            return results.getInt(1);
        } else {
            // Insert the artist
            insertIntoArtists.setString(1, name);
            int affectedRows = insertIntoArtists.executeUpdate();

            if(affectedRows != 1) {
                throw new SQLException("Couldn't insert artist!");
            }

            ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
            if(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for artist");
            }
        }
    }

    private int insertAlbum(String name, int artistId) throws SQLException {

        queryAlbum.setString(1, name);
        ResultSet results = queryAlbum.executeQuery();
        if(results.next()) {
            return results.getInt(1);
        } else {
            // Insert the album
            insertIntoAlbums.setString(1, name);
            insertIntoAlbums.setInt(2, artistId);
            int affectedRows = insertIntoAlbums.executeUpdate();

            if(affectedRows != 1) {
                throw new SQLException("Couldn't insert album!");
            }

            ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
            if(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for album");
            }
        }
    }

    public void insertSong(String title, String artist, String album, int track) {

        try {
            conn.setAutoCommit(false);

            int artistId = insertArtist(artist);
            int albumId = insertAlbum(album, artistId);
            insertIntoSongs.setInt(1, track);
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumId);
            int affectedRows = insertIntoSongs.executeUpdate();
            if(affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("The song insert failed");
            }

        } catch(Exception e) {
            System.out.println("Insert song exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch(SQLException e2) {
                System.out.println("Oh boy! Things are really bad! " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior");
                conn.setAutoCommit(true);
            } catch(SQLException e) {
                System.out.println("Couldn't reset auto-commit! " + e.getMessage());
            }

        }
    }
    public boolean updateArtistName(int Id,String name){
        try {
            updateArtistName.setString(1,name);
            updateArtistName.setInt(2,Id);
            int affectedId = updateArtistName.executeUpdate();
            return affectedId==1;
        }catch (SQLException e){e.printStackTrace(); return false;}
    }
}















