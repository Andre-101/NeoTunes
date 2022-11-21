package model;

import java.util.HashMap;

public abstract class Producer extends User{

    //Attributes
    private HashMap<AudioContent,Integer> playbackNumberByAudioContent;
    private HashMap<AudioContent,ChronologicalFormat> playbackTimeByAudioContent;
    private String name;
    private String representativeUrl;

    //Builder
    public Producer(String nickname, String id, String name, String representativeUrl) {
        super(nickname, id);
        this.name = name;
        this.representativeUrl = representativeUrl;
        playbackNumberByAudioContent = new HashMap<>();
        playbackTimeByAudioContent = new HashMap<>();
    }

    //Getters and Setters

    public HashMap<AudioContent, Integer> getPlaybackNumberByAudioContent() {
        return playbackNumberByAudioContent;
    }

    public void setPlaybackNumberByAudioContent(HashMap<AudioContent, Integer> playbackNumberByAudioContent) {
        this.playbackNumberByAudioContent = playbackNumberByAudioContent;
    }

    public HashMap<AudioContent, ChronologicalFormat> getPlaybackTimeByAudioContent() {
        return playbackTimeByAudioContent;
    }

    public void setPlaybackTimeByAudioContent(HashMap<AudioContent, ChronologicalFormat> playbackTimeByAudioContent) {
        this.playbackTimeByAudioContent = playbackTimeByAudioContent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepresentativeUrl() {
        return representativeUrl;
    }

    public void setRepresentativeUrl(String representativeUrl) {
        this.representativeUrl = representativeUrl;
    }

    @Override
    public String toString() {
        return " Producer:\n" +
                "  Nickname: " + getNickname() + "\n" +
                "  ID: " + getId() + "\n" +
                "  Join date: " + getJoinDate().toString() + "\n" +
                "  Name: " + name + "\n" +
                "  Representative Url" + representativeUrl;
    }
}
