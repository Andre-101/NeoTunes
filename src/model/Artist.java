package model;

import java.util.ArrayList;

public class Artist extends Producer{

    //Relationship
    private ArrayList<Song> songs;

    //Builder
    public Artist(String nickname, String id, String name, String representativeUrl) {
        super(nickname, id, name, representativeUrl);
    }
    /**
     * <b>Name:addSong</b><br>
     * This method allows you to add a song.
     * <b>Pre:</b>The song doesn´t exist previously<br>
     * <b>Post:</b>Song method was operated correctly<br>
     * @param song Song. Variable containing the Song you want to add on the add method. song != null
     * @return a boolean if the song was added or not
     */
    public boolean addSong(Song song) {
        return songs.add(song);
    }
     /**
     * <b>Name:showSongs</b><br>
     * This method allows you to show a list of songs.
     * <b>Post:</b>showSongs method was operated correctly<br>
     * @return a String with the list of songs
     */
    public String showSongs() {
        String message = "";
        for (int i = 0; i < songs.size(); i++) {
            message += "  (" + (i+1) + ") " + songs.get(i).getTitle() + "\n";
        }
        return "The list songs:\n" + message;
    }

    public AudioContent getTheMostListenedSong() {
        AudioContent m = null;
        for (int i = 0; i < songs.size(); i++) {
            if (m == null) {
                if (getPlaybackNumberByAudioContent().containsKey(songs.get(i))) m = songs.get(i);
            } else {
                if (getPlaybackNumberByAudioContent().containsKey(songs.get(i))) {
                    if (getPlaybackNumberByAudioContent().get(m) < getPlaybackNumberByAudioContent().get(songs.get(i))) m = songs.get(i);
                }
            }
        }
        return m;
    }

    //Getters and Setters

    public Song getSong(int index) {
        return songs.get(index);
    }

    public void setSong(boolean add_remove, Song song) {
        if (add_remove) songs.add(song);
        else songs.remove(song);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

}
