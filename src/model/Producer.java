package model;

import java.util.ArrayList;
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

    /**
     * <b>Name:updateCounter</b><br>
     * This method allows you to update the counter.
     * <b>Post:</b>updateCounter function was operated correctly<br>
     * @param audioContent AudioContent. Variable containing the audio content. audioContent != null
     */
    public void updateCounter(AudioContent audioContent) {
        if (playbackNumberByAudioContent.containsKey(audioContent)) {
            playbackNumberByAudioContent.replace(audioContent, playbackNumberByAudioContent.get(audioContent),playbackNumberByAudioContent.get(audioContent)+1);
            playbackTimeByAudioContent.get(audioContent).addTime(audioContent.getDuration().getHour(), audioContent.getDuration().getMinute(), audioContent.getDuration().getSecond());
        } else {
            playbackNumberByAudioContent.put(audioContent,1);
            playbackTimeByAudioContent.put(audioContent,audioContent.getDuration());
        }
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

    public int getTotalPlays() {
        int counts = 0;
        ArrayList<Integer> plays = (ArrayList<Integer>) playbackNumberByAudioContent.values();
        for (int i = 0; i < plays.size(); i++) {
            counts += plays.get(i);
        }
        return counts;
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
