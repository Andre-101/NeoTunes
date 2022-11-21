package model;

public interface Playlistable {

    /**
     * <b>Name:create</b><br>
     * This method allows you to create the playlist.
     * <b>Post:</b>The playlist was created correctly<br>
     * @param title String. Variable containing the playlist name. title != null
     * @return a boolean with the information of the playlist creation
     */
    public boolean create(String title);

    /**
     * <b>Name:addAudioContentToPlaylist</b><br>
     * This method allows you to add audio content to playlist.
     * <b>Pre:</b>The playlist exist<br>
     * <b>Post:</b>The audio content was added correctly<br>
     * @param indexPlaylist int. Variable containing the playlist position. indexPlaylist != null
     * @param audioContent AudioContent. Variable containing the audio content. AudioContent != null
     * @return a boolean with the information of the song addition
     */
    public boolean addAudioContentToPlaylist(int indexPlaylist, AudioContent audioContent);

    /**
     * <b>Name:removeAudioContentToPlaylist</b><br>
     * This method allows you to remove audio content to playlist.
     * <b>Pre:</b>The playlist exist<br>
     * <b>Post:</b>The audio content was removed correctly<br>
     * @param indexPlaylist int. Variable containing the playlist position. indexPlaylist != null
     * @param indexAudioContent int. Variable containing the audio content position. indexAudioContent != null
     * @return a boolean with the information of the song deletion
     */
    public boolean removeAudioContentToPlaylist(int indexPlaylist, int indexAudioContent);

    /**
     * <b>Name:showPlaylists</b><br>
     * This method allows you to show the playlists.
     * <b>Post:</b>The playlists was showed correctly<br>
     * @return a String with the information of the playlists
     */
    public String showPlaylists();

    /**
     * <b>Name:showPlaylist</b><br>
     * This method allows you to show the playlist.
     * <b>Post:</b>The playlist was showed correctly<br>
     * @param index int. Variable containing the playlist position. index != null
     * @return a String with the information of the playlist
     */
    public String showPlaylist(int index);

    /**
     * <b>Name:sharePlaylist</b><br>
     * This method allows you to share the playlist.
     * <b>Post:</b>The playlist was share correctly<br>
     * @param index int. Variable containing the playlist position. index != null
     * @return a String with the code for share the playlist
     */
    public String sharePlaylist(int index);

    /**
     * <b>Name:playPlaylist</b><br>
     * This method allows you to play the playlist.
     * <b>Post:</b>The playlist was showed correctly<br>
     * @param indexPlaylist int. Variable containing the playlist position. indexPlaylist != null
     * @param action int. Variable containing the action for playback. action != null
     * @return a String with the information of the playlist
     */
    public String playPlaylist(int indexPlaylist, int action);
}
