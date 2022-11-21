package model;

import java.util.ArrayList;
import java.util.HashMap;
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

    public boolean registerAudioContent(int user, String title, int hour, int minute, int second, String album_description, int genre_category, String pictureUrl, double price) {
        if (users.get(user) instanceof Artist)
            return ((Artist) users.get(user)).addSong(new Song(title, new ChronologicalFormat(hour, minute, second), album_description, genre_category, pictureUrl, price));
        if (users.get(user) instanceof ContentCreator)
            return ((ContentCreator) users.get(user)).addPodcast(new Podcast(title, new ChronologicalFormat(hour, minute, second), album_description, genre_category, pictureUrl));
        return false;
    }

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

    public String createPlaylist(int user, String playlistTitle) {
        if (users.get(user) instanceof Consumer) {
            if (((Consumer) users.get(user)).create(playlistTitle)) return "Successful";
            else return "You're not premium, you can't create more playlist";
        }
        return "Invalid user";
    }

    public String showPlaylist(int user) {
        if (users.get(user) instanceof Consumer) return ((Consumer) users.get(user)).showPlaylists();
        return "Invalid user";
    }

    public String showPlaylist(int user, int playlist) {
        if (users.get(user) instanceof Consumer) return ((Consumer) users.get(user)).showPlaylist(playlist);
        return "Invalid user";
    }

    public String editPlaylist(int user, int playlist, String title) {
        if (users.get(user) instanceof Consumer) {
            if (searchAudioContent(title) != null) {
                if (((Consumer) users.get(user)).addAudioContentToPlaylist(playlist, searchAudioContent(title)))
                    return "Successful";
                else return "Error in adding";
            }
            return "Don't found the song title";
        }
        return "Invalid user";
    }

    public String editPlaylist(int user, int playlist, int audioContent) {
        if (users.get(user) instanceof Consumer) {
            if (((Consumer) users.get(user)).removeAudioContentToPlaylist(playlist, audioContent)) return "Successful";
            else return "Error in the deletion";
        }
        return "Invalid user";
    }

    public String sharePlaylist(int user, int playlist) {
        if (users.get(user) instanceof Consumer) return ((Consumer) users.get(user)).sharePlaylist(playlist);
        return "Invalid user";
    }

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

    private String showAds() {
        switch (rd.nextInt(3)) {
            case 0: return ad1;
            case 1: return ad2;
            case 2: return ad3;
        }
        return "";
    }

    private void updateCounterListenProducer(int user, int playlist, int action) {
        if (users.get(user) instanceof Standard) {
            if (Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split(":")[1]) > 1) {
                if (action == 3) {
                    if (Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) > 0) {
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1) instanceof Song)
                            ((Standard) users.get(user)).updateCounter(searchArtist(((Song) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1))), 1);
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1) instanceof Podcast)
                            ((Standard) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1))), 1);
                    } else {
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().size() - 1) instanceof Song)
                            ((Standard) users.get(user)).updateCounter(searchArtist(((Song) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().size() - 1))), 1);
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().size() - 1) instanceof Podcast)
                            ((Standard) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().size() - 1))), 1);
                    }
                }
                if (action == 4) {
                    if (Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) < ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().size() - 1) {
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) + 1) instanceof Song)
                            ((Standard) users.get(user)).updateCounter(searchArtist(((Song) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1))), 1);
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) + 1) instanceof Podcast)
                            ((Standard) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(Integer.parseInt(((Standard) users.get(user)).getPlaylists()[playlist].getLastContent().split("-")[0]) - 1))), 1);
                    } else {
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(0) instanceof Song)
                            ((Standard) users.get(user)).updateCounter(searchArtist(((Song) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(0))), 1);
                        if (((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(0) instanceof Podcast)
                            ((Standard) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Standard) users.get(user)).getPlaylists()[playlist].getAudioContentList().get(0))), 1);
                    }
                }
            }
        }
        if (users.get(user) instanceof Premium) {
            if (Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split(":")[1]) > 1) {
                if (action == 3) {
                    if (Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) > 0) {
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1) instanceof Song)
                            ((Premium) users.get(user)).updateCounter(searchArtist(((Song) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1))), 1);
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1) instanceof Podcast)
                            ((Premium) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1))), 1);
                    } else {
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().size() - 1) instanceof Song)
                            ((Premium) users.get(user)).updateCounter(searchArtist(((Song) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().size() - 1))), 1);
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().size() - 1) instanceof Podcast)
                            ((Premium) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().size() - 1))), 1);
                    }
                }
                if (action == 4) {
                    if (Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) < ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().size() - 1) {
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) + 1) instanceof Song)
                            ((Premium) users.get(user)).updateCounter(searchArtist(((Song) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1))), 1);
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) + 1) instanceof Podcast)
                            ((Premium) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(Integer.parseInt(((Premium) users.get(user)).getPlaylists().get(playlist).getLastContent().split("-")[0]) - 1))), 1);
                    } else {
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(0) instanceof Song)
                            ((Premium) users.get(user)).updateCounter(searchArtist(((Song) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(0))), 1);
                        if (((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(0) instanceof Podcast)
                            ((Premium) users.get(user)).updateCounter(searchContentCreator(((Podcast) ((Premium) users.get(user)).getPlaylists().get(playlist).getAudioContentList().get(0))), 1);
                    }
                }
            }
        }
    }

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
}
