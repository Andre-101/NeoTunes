package model;

public class Standard extends Consumer{

    //Relationship
    private Playlist[] playlists;
    private Purchase[] purchases;

    //Builder
    public Standard(String nickname, String id) {
        super(nickname, id);
        playlists = new Playlist[20];
        purchases = new Purchase[100];
    }

    /**
     * <b>Name:create</b><br>
     * This method allows you to create the playlist.
     * <b>Post:</b>The playlist was created correctly<br>
     * @param title String. Variable containing the playlist name. title != null
     * @return a boolean with the information of the playlist creation
     */
    @Override
    public boolean create(String title) {
        for (int i = 0; i < playlists.length; i++) {
            if (playlists[i] == null) {
                playlists[i] = new Playlist(title);
                return true;
            }
        }
        return false;
    }

    /**
     * <b>Name:addAudioContentToPlaylist</b><br>
     * This method allows you to add audio content to playlist.
     * <b>Pre:</b>The playlist exist<br>
     * <b>Post:</b>The audio content was added correctly<br>
     * @param indexPlaylist int. Variable containing the playlist position. indexPlaylist != null
     * @param audioContent  AudioContent. Variable containing the audio content. AudioContent != null
     * @return a boolean with the information of the song addition
     */
    @Override
    public boolean addAudioContentToPlaylist(int indexPlaylist, AudioContent audioContent) {
        return playlists[indexPlaylist].add(audioContent);
    }

    /**
     * <b>Name:removeAudioContentToPlaylist</b><br>
     * This method allows you to remove audio content to playlist.
     * <b>Pre:</b>The playlist exist<br>
     * <b>Post:</b>The audio content was removed correctly<br>
     * @param indexPlaylist     int. Variable containing the playlist position. indexPlaylist != null
     * @param indexAudioContent int. Variable containing the audio content position. indexAudioContent != null
     * @return a boolean with the information of the song deletion
     */
    @Override
    public boolean removeAudioContentToPlaylist(int indexPlaylist, int indexAudioContent) {
        return playlists[indexPlaylist].remove(indexAudioContent);
    }

    /**
     * <b>Name:showPlaylists</b><br>
     * This method allows you to show the playlists.
     * <b>Post:</b>The playlists was showed correctly<br>
     * @return a String with the information of the playlists
     */
    @Override
    public String showPlaylists() {
        String message = "";
        for (int i = 0; i < playlists.length; i++) {
            message += " (" + (i+1) + ") " + playlists[i].getTitle() + "\n";
        }
        return "The playlist list:\n" + message;
    }

    /**
     * <b>Name:showPlaylist</b><br>
     * This method allows you to show the playlist.
     * <b>Post:</b>The playlist was showed correctly<br>
     * @param index int. Variable containing the playlist position. index != null
     * @return a String with the information of the playlist
     */
    @Override
    public String showPlaylist(int index) {
        return playlists[index].toString();
    }

    /**
     * <b>Name:sharePlaylist</b><br>
     * This method allows you to share the playlist.
     * <b>Post:</b>The playlist was share correctly<br>
     * @param index int. Variable containing the playlist position. index != null
     * @return a String with the code for share the playlist
     */
    @Override
    public String sharePlaylist(int index) {
        return playlists[index].codeToSharing();
    }

    /**
     * <b>Name:playPlaylist</b><br>
     * This method allows you to play the playlist.
     * <b>Post:</b>The playlist was showed correctly<br>
     * @param indexPlaylist int. Variable containing the playlist position. indexPlaylist != null
     * @param action int. Variable containing the action for playback. action != null
     * @return a String with the information of the playlist
     */
    @Override
    public String playPlaylist(int indexPlaylist, int action) {
        String message = "";
        switch (action) {
            case 1:
                if (playlists[indexPlaylist].getLastContent().split(":")[1].equals("0")) message = playlists[indexPlaylist].playAudioContent(0);
                else if (playlists[indexPlaylist].getPlaybackStatus()) message = "Playing";
                else if (Integer.parseInt(playlists[indexPlaylist].getLastContent().split(":")[1]) > 0) {
                    if (Integer.parseInt(playlists[indexPlaylist].getLastContent().split(":")[1]) == 1) message = playlists[indexPlaylist].playAudioContent(0);
                    else message = playlists[indexPlaylist].playAudioContent(Integer.parseInt(playlists[indexPlaylist].getLastContent().split("-")[0]));
                    playlists[indexPlaylist].deleteUpdateLastContent();
                }
                break;
            case 2:
                if (playlists[indexPlaylist].getPlaybackStatus()) {
                    playlists[indexPlaylist].stop();
                    message = "Stopped";
                } else message = "It's stopped";
                break;
            case 3:
                message = playlists[indexPlaylist].playAudioContent(playlists[indexPlaylist].previous());
                break;
            case 4:
                message = playlists[indexPlaylist].playAudioContent(playlists[indexPlaylist].next());
                break;
            default:
                message = "Invalid option";
        }
        updateCounter(playlists);
        return message;
    }

    /**
     * <b>Name:buy</b><br>
     * This method allows you to buy a song.
     * <b>Post:</b>The song was bought correctly<br>
     * @param song Song. Variable containing the song. song != null
     * @return a boolean with the information of the purchase
     */
    @Override
    public boolean buy(Song song) {
        for (int i = 0; i < purchases.length; i++) {
            if (purchases[i] == null) {
                purchases[i] = new Purchase(song);
                return true;
            }
        }
        return false;
    }

    //Getters and Setters

    public Playlist[] getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlist[] playlists) {
        this.playlists = playlists;
    }

    public String getPurchase() {
        String message = "";
        for (int i = 0; i < purchases.length; i++) {
            message += "  (" + (i+1) + ") " + purchases[i].toString() + "\n";
        }
        return message;
    }

    public Purchase[] getPurchases() {
        return purchases;
    }

    public void setPurchases(Purchase[] purchases) {
        this.purchases = purchases;
    }
}
