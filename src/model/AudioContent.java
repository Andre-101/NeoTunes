package model;

public abstract class AudioContent {

    //Attributes
    private String title;
    private ChronologicalFormat duration;

    //Builder
    public AudioContent(String title, ChronologicalFormat duration) {
        this.title = title;
        this.duration = duration;
    }

    //Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ChronologicalFormat getDuration() {
        return duration;
    }

    public void setDuration(ChronologicalFormat duration) {
        this.duration = duration;
    }
}
