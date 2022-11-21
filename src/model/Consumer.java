package model;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Consumer extends User implements Playlistable, Buyable{

    //Attributes
    private HashMap<Genre,ChronologicalFormat> timePlayedByGenre;
    private HashMap<Category,ChronologicalFormat> timePlayedByCategory;
    private HashMap<Artist,Integer> listenArtist;
    private HashMap<ContentCreator,Integer> listenContentCreator;

    //Builder
    public Consumer(String nickname, String id) {
        super(nickname, id);
        timePlayedByGenre = new HashMap<>();
        timePlayedByCategory = new HashMap<>();
        listenArtist = new HashMap<>();
        listenContentCreator = new HashMap<>();
    }
    /**
     * <b>Name:updateCounter</b><br>
     * This method allows you to update the counter.
     * <b>Pre:</b>The playlist must exist previously <br>
     * <b>Post:</b>updateCounter function was operated correctly<br>
     * @param playlists PLaylist[]. Variable containing the Playlists array. playlists != null
     */
    public void updateCounter(Playlist[] playlists) {
        for (int i = 0; i < playlists.length; i++) {
            for (int j = 0; j < playlists[i].getAudioContentList().size(); j++) {
                if (playlists[i].getAudioContentList().get(j) instanceof Song) {
                    if (timePlayedByGenre.containsKey(((Song) playlists[i].getAudioContentList().get(j)).getGenre())) {
                        timePlayedByGenre.get(((Song) playlists[i].getAudioContentList().get(j)).getGenre()).addTime(playlists[i].getAudioContentList().get(j).getDuration().getHour(), playlists[i].getAudioContentList().get(j).getDuration().getMinute(), playlists[i].getAudioContentList().get(j).getDuration().getSecond());
                    } else timePlayedByGenre.put(((Song) playlists[i].getAudioContentList().get(j)).getGenre(), ((Song) playlists[i].getAudioContentList().get(j)).getDuration());
                }
                if (playlists[i].getAudioContentList().get(j) instanceof Podcast) {
                    if (timePlayedByCategory.containsKey(((Podcast) playlists[i].getAudioContentList().get(j)).getCategory())) {
                        timePlayedByCategory.get(((Podcast) playlists[i].getAudioContentList().get(j)).getCategory()).addTime(playlists[i].getAudioContentList().get(j).getDuration().getHour(), playlists[i].getAudioContentList().get(j).getDuration().getMinute(), playlists[i].getAudioContentList().get(j).getDuration().getSecond());
                    } else timePlayedByCategory.put(((Podcast) playlists[i].getAudioContentList().get(j)).getCategory(), ((Podcast) playlists[i].getAudioContentList().get(j)).getDuration());
                }
            }
        }
    }

    /**
     * <b>Name:updateCounter</b><br>
     * This method allows you to update the counter.
     * <b>Pre:</b>The playlist must exist previously <br>
     * <b>Post:</b>updateCounter function was operated correctly<br>
     * @param playlists PLaylist[]. Variable containing the Playlists array. playlists != null
     */
    public void updateCounter(ArrayList<Playlist> playlists) {
        for (int i = 0; i < playlists.size(); i++) {
            for (int j = 0; j < playlists.get(i).getAudioContentList().size(); j++) {
                if (playlists.get(i).getAudioContentList().get(j) instanceof Song) {
                    if (timePlayedByGenre.containsKey(((Song) playlists.get(i).getAudioContentList().get(j)).getGenre())) {
                        timePlayedByGenre.get(((Song) playlists.get(i).getAudioContentList().get(j)).getGenre()).addTime(playlists.get(i).getAudioContentList().get(j).getDuration().getHour(), playlists.get(i).getAudioContentList().get(j).getDuration().getMinute(), playlists.get(i).getAudioContentList().get(j).getDuration().getSecond());
                    } else timePlayedByGenre.put(((Song) playlists.get(i).getAudioContentList().get(j)).getGenre(), ((Song) playlists.get(i).getAudioContentList().get(j)).getDuration());
                }
                if (playlists.get(i).getAudioContentList().get(j) instanceof Podcast) {
                    if (timePlayedByCategory.containsKey(((Podcast) playlists.get(i).getAudioContentList().get(j)).getCategory())) {
                        timePlayedByCategory.get(((Podcast) playlists.get(i).getAudioContentList().get(j)).getCategory()).addTime(playlists.get(i).getAudioContentList().get(j).getDuration().getHour(), playlists.get(i).getAudioContentList().get(j).getDuration().getMinute(), playlists.get(i).getAudioContentList().get(j).getDuration().getSecond());
                    } else timePlayedByCategory.put(((Podcast) playlists.get(i).getAudioContentList().get(j)).getCategory(), ((Podcast) playlists.get(i).getAudioContentList().get(j)).getDuration());
                }
            }
        }
    }

    /**
     * <b>Name:updateCounter</b><br>
     * This method allows you to update the counter.
     * <b>Pre:</b>The artist must exist previously <br>
     * <b>Post:</b>updateCounter function was operated correctly<br>
     * @param artist Artist. Variable containing the Artist. artist != null
     * @param value int. Variable containing the int value. value != null
     */
    public void updateCounter(Artist artist, int value) {
        if (artist != null) {
            if (listenArtist.containsKey(artist)) {
                listenArtist.replace(artist, listenArtist.get(artist), listenArtist.get(artist)+value);
            } else listenArtist.put(artist, value);
        }
    }

    /**
     * <b>Name:updateCounter</b><br>
     * This method allows you to update the counter.
     * <b>Pre:</b>The contentCreator must exist previously <br>
     * <b>Post:</b>updateCounter function was operated correctly<br>
     * @param contentCreator ContentCreator. Variable containing the ContentCreator. contentCreator != null
     * @param value int. Variable containing the int value. value != null
     */
    public void updateCounter(ContentCreator contentCreator, int value) {
        if (contentCreator != null) {
            if (listenContentCreator.containsKey(contentCreator)) {
                listenContentCreator.replace(contentCreator, listenContentCreator.get(contentCreator), listenContentCreator.get(contentCreator)+value);
            } else listenContentCreator.put(contentCreator, value);
        }
    }

    //Getters and Setters

    public HashMap<Genre, ChronologicalFormat> getTimePlayedByGenre() {
        return timePlayedByGenre;
    }

    public void setTimePlayedByGenre(HashMap<Genre, ChronologicalFormat> timePlayedByGenre) {
        this.timePlayedByGenre = timePlayedByGenre;
    }

    public HashMap<Category, ChronologicalFormat> getTimePlayedByCategory() {
        return timePlayedByCategory;
    }

    public void setTimePlayedByCategory(HashMap<Category, ChronologicalFormat> timePlayedByCategory) {
        this.timePlayedByCategory = timePlayedByCategory;
    }

    public HashMap<Artist, Integer> getListenArtist() {
        return listenArtist;
    }

    public void setListenArtist(HashMap<Artist, Integer> listenArtist) {
        this.listenArtist = listenArtist;
    }

    public HashMap<ContentCreator, Integer> getListenContentCreator() {
        return listenContentCreator;
    }

    public void setListenContentCreator(HashMap<ContentCreator, Integer> listenContentCreator) {
        this.listenContentCreator = listenContentCreator;
    }

    @Override
    public String toString() {
        return "Consumer:\n" +
                "  Nickname: " + getNickname() + "\n" +
                "  ID: " + getId() + "\n" +
                "  Join date: " + getJoinDate().toString();
    }
}
