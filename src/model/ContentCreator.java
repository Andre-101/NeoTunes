package model;

import java.util.ArrayList;

public class ContentCreator extends Producer{

    //Relationship
    private ArrayList<Podcast> podcasts;

    //Builder
    public ContentCreator(String nickname, String id, String name, String representativeUrl) {
        super(nickname, id, name, representativeUrl);
    }
     /**
     * <b>Name:addPodcast</b><br>
     * This method allows you to add a podcast.
     * <b>Pre:</b>The podcast doesn´t exist previously<br>
     * <b>Post:</b>addPodcast method was operated correctly<br>
     * @param podcast Podcast. Variable containing the podcast that you want to add on the add method. podcast != null
     * @return a boolean if the podcast was added or not
     */
    public boolean addPodcast(Podcast podcast) {
        return podcasts.add(podcast);
    }
     /**
     * <b>Name:showPodcast</b><br>
     * This method allows you to show a podcast.
     * <b>Post:</b>showPodcast method was operated correctly<br>
     * @return a String if the podcast was shown or not
     */
    public String showPodcast() {
        String message = "";
        for (int i = 0; i < podcasts.size(); i++) {
            message += "  (" + (i+1) + ") " + podcasts.get(i).getTitle() + "\n";
        }
        return "The list podcast:\n" + message;
    }

    //Setters and Getters

    public Podcast getPodcast(int index) {
        return podcasts.get(index);
    }

    public void setPodcast(boolean add_remove, Podcast podcast) {
        if (add_remove) podcasts.add(podcast);
        else podcasts.remove(podcast);
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }
}
