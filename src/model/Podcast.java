package model;

public class Podcast extends AudioContent{

    //Attributes
    private String description;
    private Category category;
    private String podcastPictureURL;

    //Builder
    public Podcast(String title, ChronologicalFormat duration, String description, int category, String podcastPictureURL) {
        super(title, duration);
        this.description = description;
        this.category = Category.values()[category];
        this.podcastPictureURL = podcastPictureURL;
    }

    //Getters and Setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPodcastPictureURL() {
        return podcastPictureURL;
    }

    public void setPodcastPictureURL(String podcastPictureURL) {
        this.podcastPictureURL = podcastPictureURL;
    }

    public String getObject() {
        return getTitle() + "    Category: " + category;
    }

    @Override
    public String toString() {
        return "Song: " + getTitle() +
                "  \nDuration: " +getDuration().toString()+
                "  \nDescription: " + description +
                "  \nCategory: " + category +
                "  \nPodcast picture URL: " + podcastPictureURL;
    }
}
