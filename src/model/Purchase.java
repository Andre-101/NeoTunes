package model;

import java.util.Date;

public class Purchase{

    //Attributes
    private Song song;
    private Date purchaseDate;

    //Builder
    public Purchase(Song song) {
        this.song = song;
        this.purchaseDate = new Date();
    }

    @Override
    public String toString() {
        return "Purchase:\n" +
                " Song: " + song.getTitle() + "\n" +
                " Price: $" + song.getPrice() + "\n" +
                " purchaseDate" + purchaseDate.toString();
    }
}
