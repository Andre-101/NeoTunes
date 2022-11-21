package model;

public interface Buyable {

    /**
     * <b>Name:buy</b><br>
     * This method allows you to buy a song.
     * <b>Post:</b>The song was bought correctly<br>
     * @param song Song. Variable containing the song. song != null
     * @return a boolean with the information of the purchase
     */
    public boolean buy(Song song);
}
