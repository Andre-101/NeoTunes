package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class Controller implements Ads{

    //Relationship
    private ArrayList<User> users;
    private Random rd;

    //Builder
    public Controller() {
        users = new ArrayList<>();
        rd = new Random();
    }

    /**
     * <b>Name:registerUser</b><br>
     * This method allows you to register a user.
     * <b>Pre:</b>The user doesn't exist previously<br>
     * <b>Post:</b>register method was operated correctly<br>
     * @param nickname String. Variable containing the String you want to register. nickname != null
     * @param id String. Variable that contains the id . id != null
     * @param name String. Variable that contains the name . name != null
     * @param representativeUrl String. Variable that contains the representativeUrl . representativeUrl != null
     * @param userType int. Variable that contains the userType . userType != null
     * @return a boolean if the user was registered or not
     */
    public boolean registerUser(String nickname, String id, String name, String representativeUrl, int userType) {
        switch (userType) {
            case 1:
                return users.add(new Standard(nickname, id));
            case 2:
                return users.add(new Premium(nickname, id));
            case 3:
                return users.add(new Artist(nickname, id, name, representativeUrl));
            case 4:
                return users.add(new ContentCreator(nickname, id, name, representativeUrl));
        }
        return false;
    }

    /**
     * <b>Name:registerAudioContent</b><br>
     * This method allows you to register a user.
     * <b>Pre:</b>The AudioContent doesn't exist previously<br>
     * <b>Post:</b>register method was operated correctly<br>
     * @param user int. Variable containing the int you want to register. user != null
     * @param title String. Variable that contains the title . title != null
     * @param hour int. Variable that contains the hour . hour != null
     * @param minute int. Variable that contains the minute . minute != null
     * @param second int. Variable that contains the second . second != null
     * @param album_description String. Variable that contains the album_description . album_description != null
     * @param genre_category int. Variable that contains the genre_category . genre_category != null
     * @param pictureUrl String. Variable that contains the pictureUrl . pictureUrl != null
     * @param price double. Variable that contains the price . price != null
     * @return a boolean if the audio content was registered or not
     */
    public boolean registerAudioContent(int user, String title, int hour, int minute, int second, String album_description, int genre_category, String pictureUrl, double price) {
        if (users.get(user) instanceof Artist)
            return ((Artist) users.get(user)).addSong(new Song(title, new ChronologicalFormat(hour, minute, second), album_description, genre_category, pictureUrl, price));
        if (users.get(user) instanceof ContentCreator)
            return ((ContentCreator) users.get(user)).addPodcast(new Podcast(title, new ChronologicalFormat(hour, minute, second), album_description, genre_category, pictureUrl));
        return false;
    }

    /**
     * <b>Name:searchAudioContent</b><br>
     * This method allows you to searchAudioContent.
     * <b>Pre:</b>The audio content should previously exist<br>
     * <b>Post:</b>searchAudioContent method was operated correctly<br>
     * @param title String. Variable containing the String you want to find. title != null
     * @return an AudioContent with the audio content found
     */
    public AudioContent searchAudioContent(String title) {
        AudioContent audioContent = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Artist) {
                for (int j = 0; j < ((Artist) users.get(i)).getSongs().size(); j++) {
                    if (((Artist) users.get(i)).getSongs().get(j).getTitle().equalsIgnoreCase(title)) {
                        audioContent = ((Artist) users.get(i)).getSongs().get(j);
                    }
                }
            }
            if (users.get(i) instanceof ContentCreator) {
                for (int j = 0; j < ((ContentCreator) users.get(i)).getPodcasts().size(); j++) {
                    if (((ContentCreator) users.get(i)).getPodcasts().get(j).getTitle().equalsIgnoreCase(title)) {
                        audioContent = ((ContentCreator) users.get(i)).getPodcasts().get(j);
                    }
                }
            }
        }
        return audioContent;
    }

    /**
     * <b>Name:createPlaylist</b><br>
     * This method allows you to createPlaylist.
     * <b>Pre:</b>The playlist should not previously exist<br>
     * <b>Post:</b>createPlaylist method was operated correctly<br>
     * @param user int. Variable containing the integer you want to create. user != null
     * @param playlistTitle String. Variable containing the String you want to create. playlistTitle != null
     * @return a String with the playlist created.
     */
    public String createPlaylist(int user, String playlistTitle) {
        if (users.get(user) instanceof Consumer) {
            if (((Consumer) users.get(user)).create(playlistTitle)) return "Successful";
            else return "You're not premium, you can't create more playlist";
        }
        return "Invalid user";
    }

     /**
     * <b>Name:showPlaylist</b><br>
     * This method allows you to show a playlist.
     * <b>Pre:</b>The playlist should previously exist<br>
     * <b>Post:</b>showPlaylist method was operated correctly<br>
     * @param user int. Variable containing the integer to locate the user.  user != null
     * @return a String with the shown playlist.
     */
    public String showPlaylist(int user) {
        if (users.get(user) instanceof Consumer) return ((Consumer) users.get(user)).showPlaylists();
        return "Invalid user";
    }

    /**
     * <b>Name:showPlaylist</b><br>
     * This method allows you to show a playlist.
     * <b>Pre:</b>The playlist should previously exist<br>
     * <b>Post:</b>showPlaylist method was operated correctly<br>
     * @param user int. Variable containing the integer to locate the user.  user != null
     * @param playlist int. Variable containing the integer to locate the playlist.  playlist != null
     * @return a String with the shown playlist.
     */
    public String showPlaylist(int user, int playlist) {
        if (users.get(user) instanceof Consumer) return ((Consumer) users.get(user)).showPlaylist(playlist);
        return "Invalid user";
    }

     /**
     * <b>Name:editPlaylist</b><br>
     * This method allows you to edit a playlist.
     * <b>Pre:</b>The playlist should previously exist<br>
     * <b>Post:</b>editPlaylist method was operated correctly<br>
     * @param user int. Variable containing the integer to locate the user.  user != null
     * @param playlist int. Variable containing the integer to locate the playlist.  playlist != null
     * @param title String. Variable containing the String to locate the title.  title != null
     * @return a String with the edited playlist.
     */
    public String editPlaylist(int user, int playlist, String title) {
        if (users.get(user) instanceof Consumer) {
            if (searchAudioContent(title) != null) {
                if (((Consumer) users.get(user)).addAudioContentToPlaylist(playlist, searchAudioContent(title)))
                    return "Successful";
                else return "Error in adding";
            }
            return "Didn't find the song title";
        }
        return "Invalid user";
    }

    /**
     * <b>Name:editPlaylist</b><br>
     * This method allows you to edit a playlist.
     * <b>Pre:</b>The playlist should previously exist<br>
     * <b>Post:</b>editPlaylist method was operated correctly<br>
     * @param user int. Variable containing the integer to locate the user.  user != null
     * @param playlist int. Variable containing the integer to locate the playlist.  playlist != null
     * @param audioContent int. Variable containing the Integer to locate the audioContent.  audioContent != null
     * @return a String with the edited playlist.
     */
    public String editPlaylist(int user, int playlist, int audioContent) {
        if (users.get(user) instanceof Consumer) {
            if (((Consumer) users.get(user)).removeAudioContentToPlaylist(playlist, audioContent)) return "Successful";
            else return "Error in the deletion";
        }
        return "Invalid user";
    }

    /**
     * <b>Name:sharePlaylist</b><br>
     * This method allows you to share a playlist.
     * <b>Pre:</b>The playlist should previously exist<br>
     * <b>Post:</b>sharePlaylist method was operated correctly<br>
     * @param user int. Variable containing the integer to locate the user.  user != null
     * @param playlist int. Variable containing the integer to locate the playlist.  playlist != null
     * @return a String with the shared playlist.
     */
    public String sharePlaylist(int user, int playlist) {
        if (users.get(user) instanceof Consumer) return ((Consumer) users.get(user)).sharePlaylist(playlist);
        return "Invalid user";
    }

    /**
     * <b>Name:playbackPlaylist</b><br>
     * This method allows you to playback a playlist.
     * <b>Pre:</b>The playlist should previously exist<br>
     * <b>Post:</b>playbackPlaylist method was operated correctly<br>
     * @param user int. Variable containing the Integer to locate the user.  user != null
     * @param playlist int. Variable containing the Integer to locate the playlist.  playlist != null
     * @param action int. Variable containing the Integer to execute the action.  action != null
     * @return a String with the shared playlist.
     */
    public String playbackPlaylist(int user, int playlist, int action) {
        if (users.get(user) instanceof Consumer) {
            if (action == 3 || action == 4) updateCounterListenProducer(user, playlist, action);
            if (users.get(user) instanceof Standard) {
                if (Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split(":")[1]) % 3 == 0) return showAds();
            }
            return ((Consumer) users.get(user)).playPlaylist(playlist, action);
        }
        return "Invalid user";
    }

    /**
     * <b>Name:showAds</b><br>
     * This method allows you to show ads.
     * <b>Post:</b>showAds method was operated correctly<br>
     * @return a String with the shown ads.
     */
    private String showAds() {
        switch (rd.nextInt(3)) {
            case 0: return ad1;
            case 1: return ad2;
            case 2: return ad3;
        }
        return "";
    }

    /**
     * <b>Name:updateCounterListenProducer</b><br>
     * This method allows you to update counter for times you listen to Producer.
     * <b>Pre:</b>The playlist should previously exist<br>
     * <b>Post:</b>updateCounterListenProducer method was operated correctly<br>
     * @param user int. Variable containing the Integer to locate the user.  user != null
     * @param playlist int. Variable containing the Integer to locate the playlist.  playlist != null
     * @param action int. Variable containing the Integer to execute the action.  action != null
     */
    private void updateCounterListenProducer(int user, int playlist, int action) {
        if (users.get(user) instanceof Standard) {
            if (Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split(":")[1]) > 1) {
                if (action == 3) {
                    if (Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) > 0) {
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1) instanceof Song) {
                            Objects.requireNonNull(searchArtist(((Song) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1)))).updateCounter(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1));
                            ((Standard) users.get(user)).updateCounter(searchArtist(((Song) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1))), 1);
                        }
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1) instanceof Podcast) {
                            Objects.requireNonNull(searchContentCreator(((Podcast) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1)))).updateCounter(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1));
                            ((Standard) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1))), 1);
                        }
                    } else {
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().size() - 1) instanceof Song) {
                            Objects.requireNonNull(searchArtist(((Song) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().size() - 1)))).updateCounter(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().size() - 1));
                            ((Standard) users.get(user)).updateCounter(searchArtist(((Song) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().size() - 1))), 1);
                        }
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().size() - 1) instanceof Podcast) {
                            Objects.requireNonNull(searchContentCreator(((Podcast) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().size() - 1)))).updateCounter(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().size() - 1));
                            ((Standard) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().size() - 1))), 1);
                        }
                    }
                }
                if (action == 4) {
                    if (Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) < ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().size() - 1) {
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) + 1) instanceof Song) {
                            Objects.requireNonNull(searchArtist(((Song) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) + 1)))).updateCounter(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) + 1));
                            ((Standard) users.get(user)).updateCounter(searchArtist(((Song) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1))), 1);
                        }
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) + 1) instanceof Podcast) {
                            Objects.requireNonNull(searchContentCreator(((Podcast) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) + 1)))).updateCounter(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) + 1));
                            ((Standard) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1))), 1);
                        }
                    } else {
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(0) instanceof Song) {
                            Objects.requireNonNull(searchArtist(((Song) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(0)))).updateCounter(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(0));
                            ((Standard) users.get(user)).updateCounter(searchArtist(((Song) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(0))), 1);
                        }
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(0) instanceof Podcast) {
                            Objects.requireNonNull(searchContentCreator(((Podcast) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(0)))).updateCounter(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(0));
                            ((Standard) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(0))), 1);
                        }
                    }
                }
            }
        }
        if (users.get(user) instanceof Premium) {
            if (Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split(":")[1]) > 1) {
                if (action == 3) {
                    if (Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) > 0) {
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1) instanceof Song) {
                            Objects.requireNonNull(searchArtist(((Song) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1)))).updateCounter(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1));
                            ((Premium) users.get(user)).updateCounter(searchArtist(((Song) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1))), 1);
                        }
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1) instanceof Podcast) {
                            Objects.requireNonNull(searchContentCreator(((Podcast) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1)))).updateCounter(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1));
                            ((Premium) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1))), 1);
                        }
                    } else {
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().size() - 1) instanceof Song) {
                            Objects.requireNonNull(searchArtist(((Song) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().size() - 1)))).updateCounter(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().size() - 1));
                            ((Premium) users.get(user)).updateCounter(searchArtist(((Song) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().size() - 1))), 1);
                        }
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().size() - 1) instanceof Podcast) {
                            Objects.requireNonNull(searchContentCreator(((Podcast) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().size() - 1)))).updateCounter(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().size() - 1));
                            ((Premium) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().size() - 1))), 1);
                        }
                    }
                }
                if (action == 4) {
                    if (Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) < ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().size() - 1) {
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) + 1) instanceof Song) {
                            Objects.requireNonNull(searchArtist(((Song) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) + 1)))).updateCounter(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) + 1));
                            ((Premium) users.get(user)).updateCounter(searchArtist(((Song) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1))), 1);
                        }
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) + 1) instanceof Podcast) {
                            Objects.requireNonNull(searchContentCreator(((Podcast) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) + 1)))).updateCounter(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) + 1));
                            ((Premium) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1))), 1);
                        }
                    } else {
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(0) instanceof Song) {
                            Objects.requireNonNull(searchArtist(((Song) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(0)))).updateCounter(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(0));
                            ((Premium) users.get(user)).updateCounter(searchArtist(((Song) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(0))), 1);
                        }
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(0) instanceof Podcast) {
                            Objects.requireNonNull(searchContentCreator(((Podcast) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(0)))).updateCounter(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(0));
                            ((Premium) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(0))), 1);
                        }
                    }
                }
            }
        }
    }

    /**
     * <b>Name:searchContentCreator</b><br>
     * This method allows you to search for a content creator.
     * <b>Pre:</b>The content creator should previously exist<br>
     * <b>Post:</b>searchContentCreator method was operated correctly<br>
     * @param podcast Podcast. Variable containing the Podcast to locate the content creator.  podcast != null
     * @return a ContentCreator in a position.
     */
    private ContentCreator searchContentCreator(Podcast podcast) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof ContentCreator) {
                for (int j = 0; j < ((ContentCreator) users.get(i)).getPodcasts().size(); j++) {
                    if (((ContentCreator) users.get(i)).getPodcasts().get(j).equals(podcast))
                        return ((ContentCreator) users.get(i));
                }
            }
        }
        return null;
    }

    /**
     * <b>Name:searchArtist</b><br>
     * This method allows you to search for an artist creator.
     * <b>Pre:</b>The artist should previously exist<br>
     * <b>Post:</b>searchArtist method was operated correctly<br>
     * @param song Song. Variable containing the Podcast to locate the content creator.  podcast != null
     * @return an Artist in a position.
     */
    private Artist searchArtist(Song song) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Artist) {
                for (int j = 0; j < ((Artist) users.get(i)).getSongs().size(); j++) {
                    if (((Artist) users.get(i)).getSongs().get(j).equals(song)) return ((Artist) users.get(i));
                }
            }
        }
        return null;
    }

     /**
     * <b>Name:buySong</b><br>
     * This method allows you to buy a song.
     * <b>Pre:</b>The song should previously exist<br>
     * <b>Post:</b>buySong method was operated correctly<br>
     * @param user int. Variable containing the Integer to locate the user.  user != null
     * @param title String. Variable containing the song's title.  title != null
     * @return a String with the purchase message.
     */
    public String buySong(int user, String title) {
        if (users.get(user) instanceof Consumer) {
            if (searchAudioContent(title) != null) {
                if (searchAudioContent(title) instanceof Song) {
                    switch (rd.nextInt(5)) {
                        case 0:
                            return "The banking system shut down";
                        case 1:
                            return "Card declined, insufficient funds";
                        case 2:
                            return "Card rejected, bank blocks access";
                        case 3:
                            return "System error, payment cannot be made";
                        case 4:
                            if (((Consumer) users.get(user)).buy(((Song) searchAudioContent(title))))
                                return "Successful purchase";
                            else
                                return "Purchase was successful but you cannot buy more songs, the purchase money will be returned to you.";
                    }
                } else return "Error, the title isn't a song";
            } else return "Don't found the song title";
        }
        return "Invalid user";
    }

     /**
     * <b>Name:totalSongPlays</b><br>
     * This method allows you to display totalSongPlays.
     * <b>Pre:</b>The song should previously exist<br>
     * <b>Post:</b>totalSongPlays method was operated correctly<br>
     * @return a String with the total played songs
     */
    public String totalSongPlays() {
        int counter = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Standard) {
                for (int j = 0; j < ((Standard) users.get(i)).getPlaylists().length; j++) {
                    if (((Standard) users.get(i)).getPlaylists()[j].sharingType() == 1)
                        counter += Integer.parseInt(((Standard) users.get(i)).getPlaylists()[j].getLastContent().split(":")[1]);
                    if (((Standard) users.get(i)).getPlaylists()[j].sharingType() == 3) {
                        for (int k = 0; k < ((Standard) users.get(i)).getPlaylists()[j].getAudioContentList().size(); k++) {
                            if (((Standard) users.get(i)).getPlaylists()[j].getAudioContentList().get(k) instanceof Song)
                                counter++;
                        }
                    }
                }
            }
            if (users.get(i) instanceof Premium) {
                for (int j = 0; j < ((Premium) users.get(i)).getPlaylists().size(); j++) {
                    if (((Premium) users.get(i)).getPlaylists().get(j).sharingType() == 1)
                        counter += Integer.parseInt(((Premium) users.get(i)).getPlaylists().get(j).getLastContent().split(":")[1]);
                    if (((Premium) users.get(i)).getPlaylists().get(j).sharingType() == 3) {
                        for (int k = 0; k < ((Premium) users.get(i)).getPlaylists().get(j).getAudioContentList().size(); k++) {
                            if (((Premium) users.get(i)).getPlaylists().get(j).getAudioContentList().get(k) instanceof Song)
                                counter++;
                        }
                    }
                }
            }
        }
        return "The total song plays: " + counter;
    }

    /**
     * <b>Name:totalPodcastPlay</b><br>
     * This method allows you to display totalPodcastPlay.
     * <b>Pre:</b>The podcast should previously exist<br>
     * <b>Post:</b>totalPodcastPlay method was operated correctly<br>
     * @return a String with the total played podcasts.
     */
    public String totalPodcastPlay() {
        int counter = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Standard) {
                for (int j = 0; j < ((Standard) users.get(i)).getPlaylists().length; j++) {
                    if (((Standard) users.get(i)).getPlaylists()[j].sharingType() == 2)
                        counter += Integer.parseInt(((Standard) users.get(i)).getPlaylists()[j].getLastContent().split(":")[1]);
                    if (((Standard) users.get(i)).getPlaylists()[j].sharingType() == 3) {
                        for (int k = 0; k < ((Standard) users.get(i)).getPlaylists()[j].getAudioContentList().size(); k++) {
                            if (((Standard) users.get(i)).getPlaylists()[j].getAudioContentList().get(k) instanceof Podcast)
                                counter++;
                        }
                    }
                }
            }
            if (users.get(i) instanceof Premium) {
                for (int j = 0; j < ((Premium) users.get(i)).getPlaylists().size(); j++) {
                    if (((Premium) users.get(i)).getPlaylists().get(j).sharingType() == 2)
                        counter += Integer.parseInt(((Premium) users.get(i)).getPlaylists().get(j).getLastContent().split(":")[1]);
                    if (((Premium) users.get(i)).getPlaylists().get(j).sharingType() == 3) {
                        for (int k = 0; k < ((Premium) users.get(i)).getPlaylists().get(j).getAudioContentList().size(); k++) {
                            if (((Premium) users.get(i)).getPlaylists().get(j).getAudioContentList().get(k) instanceof Podcast)
                                counter++;
                        }
                    }
                }
            }
        }
        return "The total podcast plays: " + counter;
    }

    /**
     * <b>Name:theMostListenGenre</b><br>
     * This method allows you to display theMostListenGenre.
     * <b>Pre:</b>The genres should previously exist<br>
     * <b>Post:</b>theMostListenGenre method was operated correctly<br>
     * @return a String with theMostListenGenre.
     */
    public String theMostListenGenre(int indexUser) {
        Genre max = Genre.values()[0];
        if (users.get(indexUser) instanceof Consumer) {
            for (int i = 1; i < Genre.values().length; i++) {
                if (((Consumer) users.get(indexUser)).getTimePlayedByGenre().get(max).toSeconds() < ((Consumer) users.get(indexUser)).getTimePlayedByGenre().get(Genre.values()[i]).toSeconds())
                    max = Genre.values()[i];
            }
            return max.name();
        }
        return "Invalid user";
    }

    /**
     * <b>Name:theMostListenGenre</b><br>
     * This method allows you to display theMostListenGenre.
     * <b>Pre:</b>The genres should previously exist<br>
     * <b>Post:</b>theMostListenGenre method was operated correctly<br>
     * @return a String with theMostListenGenre.
     */
    public String theMostListenGenre() {
        HashMap<Genre,ChronologicalFormat> genres = new HashMap<>();
        Genre max = Genre.values()[0];
        for (int i = 0; i < Genre.values().length; i++) {
            genres.put(Genre.values()[i],new ChronologicalFormat(0,0,0));
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Consumer) {
                for (int j = 0; j < Genre.values().length; j++) {
                    if (((Consumer) users.get(i)).getTimePlayedByGenre().containsKey(Genre.values()[j])) {
                        ChronologicalFormat temp = ((Consumer) users.get(i)).getTimePlayedByGenre().get(Genre.values()[j]);
                        genres.get(Genre.values()[j]).addTime(temp.getHour(), temp.getMinute(), temp.getSecond());
                    }
                }
            }
        }
        for (int i = 1; i < Genre.values().length; i++) {
            if (genres.get(max).toSeconds() < genres.get(Genre.values()[i]).toSeconds())
                max = Genre.values()[i];
        }

        return max.name();
    }

    /**
     * <b>Name:theMostListenCategory</b><br>
     * This method allows you to display theMostListenCategory.
     * <b>Pre:</b>The category should previously exist<br>
     * <b>Post:</b>theMostListenCategory method was operated correctly<br>
     * @param indexUser int. Variable containing the Integer to locate the user.   indexUser != null
     * @return a String with theMostListenCategory.
     */
    public String theMostListenCategory(int indexUser) {
        Category max = Category.values()[0];
        if (users.get(indexUser) instanceof Consumer) {
            for (int i = 1; i < Category.values().length; i++) {
                if (((Consumer) users.get(indexUser)).getTimePlayedByCategory().get(max).toSeconds() < ((Consumer) users.get(indexUser)).getTimePlayedByCategory().get(Category.values()[i]).toSeconds())
                    max = Category.values()[i];
            }
            return max.name();
        }
        return "Invalid user";
    }

    /**
     * <b>Name:theMostListenCategory</b><br>
     * This method allows you to display theMostListenCategory.
     * <b>Pre:</b>The category should previously exist<br>
     * <b>Post:</b>theMostListenCategory method was operated correctly<br>
     * @return a String with theMostListenCategory.
     */
    public String theMostListenCategory() {
        HashMap<Category,ChronologicalFormat> categories = new HashMap<>();
        Category max = Category.values()[0];
        for (int i = 0; i < Category.values().length; i++) {
            categories.put(Category.values()[i],new ChronologicalFormat(0,0,0));
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Consumer) {
                for (int j = 0; j < Category.values().length; j++) {
                    if (((Consumer) users.get(i)).getTimePlayedByCategory().containsKey(Category.values()[j])) {
                        ChronologicalFormat temp = ((Consumer) users.get(i)).getTimePlayedByCategory().get(Category.values()[j]);
                        categories.get(Category.values()[j]).addTime(temp.getHour(), temp.getMinute(), temp.getSecond());
                    }
                }
            }
        }
        for (int i = 1; i < Category.values().length; i++) {
            if (categories.get(max).toSeconds() < categories.get(Category.values()[i]).toSeconds())
                max = Category.values()[i];
        }
        return max.name();
    }

    public String topFiveArtist() {
        ArrayList<Producer> artists = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Artist) artists.add(((Artist) users.get(i)));
        }
        ArrayList<Producer> top = sortUsers(artists);
        return showTopProducers(top);
    }

    public String topFiveContentCreators() {
        ArrayList<Producer> contentCreators = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof ContentCreator) contentCreators.add(((ContentCreator) users.get(i)));
        }
        ArrayList<Producer> top = sortUsers(contentCreators);
        return showTopProducers(top);
    }

    private String showTopProducers(ArrayList<Producer> top) {
        String message = "";
        for (int i = 0; i < top.size() && i < 5; i++) {
            message += "  (" + (i+1) + ")  " + top.get(i).getNickname() + " (" + top.get(i).getName() + ")   " + top.get(i).getTotalPlays() + " total plays\n";
        }
        return message;
    }

    private ArrayList<Producer> sortUsers(ArrayList<Producer> producers) {
        Producer max;
        for (int i = 0; i < producers.size()-1; i++) {
            for (int j = i; j < producers.size()-1; j++) {
                if (producers.get(i).getTotalPlays() < producers.get(j+1).getTotalPlays()) {
                    max = producers.get(j+1);
                    producers.set(j+1, producers.get(i));
                    producers.set(i,max);
                }
            }
        }
        return producers;
    }

    public String topTenSongs() {
        ArrayList<AudioContent> songs = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Artist) {
                songs.add(((Artist) users.get(i)).getTheMostListenedSong());
            }
        }
        ArrayList<AudioContent> top = sortAudioContent(songs);
        return showTopAudioContent(top);
    }

    public String topTenPodcast() {
        ArrayList<AudioContent> podcasts = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof ContentCreator) {
                podcasts.add(((ContentCreator) users.get(i)).getTheMostListenedPodcast());
            }
        }
        ArrayList<AudioContent> top = sortAudioContent(podcasts);
        return showTopAudioContent(top);
    }

    private String showTopAudioContent(ArrayList<AudioContent> top) {
        String message = "";
        for (int i = 0; i < top.size() && i < 10; i++) {
            message += "  (" + (i+1) + ")  ";
            if (top.get(i) instanceof Song) {
                message += ((Song) top.get(i)).getObject() + "    plays: " + Objects.requireNonNull(searchArtist(((Song) top.get(i)))).getPlaybackNumberByAudioContent().get(top.get(i)) + "\n";
            }
            if (top.get(i) instanceof Podcast) {
                message += ((Podcast) top.get(i)).getObject() + "    plays: " + Objects.requireNonNull(searchContentCreator(((Podcast) top.get(i)))).getPlaybackNumberByAudioContent().get(top.get(i)) + "\n";
            }
        }
        return message;
    }

    private ArrayList<AudioContent> sortAudioContent(ArrayList<AudioContent> songs) {
        AudioContent max;
        for (int i = 0; i < songs.size()-1; i++) {
            for (int j = i; j < songs.size()-1; j++) {
                if (Objects.requireNonNull(searchArtist((Song) songs.get(i))).getPlaybackNumberByAudioContent().get(songs.get(i)) < Objects.requireNonNull(Objects.requireNonNull(searchArtist((Song) songs.get(j + 1))).getPlaybackNumberByAudioContent().get(songs.get(j+1)))) {
                    max = songs.get(j+1);
                    songs.set(j+1, songs.get(i));
                    songs.set(i,max);
                }
            }
        }
        return songs;
    }
}
