package model;

public class Song extends AudioContent{

    //Attributes
    private String album;
    private Genre genre;
    private String albumPictureURL;
    private double price;

    //Builder
    public Song(String title, ChronologicalFormat duration, String album, int genre, String albumPictureURL, double price) {
        super(title, duration);
        this.album = album;
        this.genre = Genre.values()[genre];
        this.albumPictureURL = albumPictureURL;
        this.price = price;
    }

    //Getters and Setters

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getAlbumPictureURL() {
        return albumPictureURL;
    }

    public void setAlbumPictureURL(String albumPictureURL) {
        this.albumPictureURL = albumPictureURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getObject() {
        return getTitle() + "    Genre: " + genre;
    }

    @Override
    public String toString() {
        return "Song: " + getTitle() +
                "  \nDuration: " +getDuration().toString()+
                "  \nAlbum: " + album +
                "  \nGenre: " + genre +
                "  \nAlbum picture URL: " + albumPictureURL +
                "  \nprice: $" + price;
    }
}
