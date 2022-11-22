package model;

import java.util.ArrayList;

public class Playlist implements Editable, Reproducible, Shareable{

    //Attributes
    private String title;
    private boolean playbackStatus;
    private String lastContent;

    //Relationship
    private ArrayList<AudioContent> audioContentList;
    private Encoder en;

    //Builder
    public Playlist(String title) {
        this.title = title;
        playbackStatus = false;
        lastContent = " :0";
        audioContentList = new ArrayList<>();
        en = new Encoder();
    }

     /**
     * <b>Name:playAudioContent</b><br>
     * This method allows you to play the audio content.
     * <b>Pre:</b>that the position of the audio exists<br>
     * <b>Post:</b>The audio was played correctly<br>
     * @param indexAudioContent int. Variable containing the audio content position. indexAudioContent != null
     * @return a String with the reproduction interface of the audio content
     */
    public String playAudioContent(int indexAudioContent) {
        play();
        int cant = Integer.parseInt(lastContent.split(":")[1])+1;
        if (cant == 1) lastContent = indexAudioContent + ":" + cant;
        else lastContent = indexAudioContent + "-" + lastContent.split(":")[0] + ":" + cant;
        return "  Title: " + audioContentList.get(indexAudioContent).getTitle() +"\n"+
                "  Duration:  " + audioContentList.get(indexAudioContent).getDuration().toString();
    }

    /**
     * <b>Name:showAudioContent</b><br>
     * This method allows you to play the audio content.
     * <b>Pre:</b>that the position of the audio exists<br>
     * <b>Post:</b>The audio was played correctly<br>
     * @param indexAudioContent int. Variable containing the audio content position. indexAudioContent != null
     * @return a String with the information of the audio content
     */
    public String showAudioContent(int indexAudioContent) {
        return audioContentList.get(indexAudioContent).toString();
    }

    /**
     * <b>Name:deleteUpdateLastContent</b><br>
     * This method allows you to delete the updated last content.
     * <b>Pre:</b>the last content must exist exists<br>
     * <b>Post:</b>Las content was deleted correctly<br>
     */
    public void deleteUpdateLastContent() {
        if (!lastContent.split(":")[1].equals("0") || !lastContent.split(":")[1].equals("1")) {
            lastContent = lastContent.split(":")[0].substring(lastContent.split("-")[0].length()+1) + ":" + (Integer.parseInt(lastContent.split(":")[1])-1);
        }
    }

    /**
     * <b>Name:add</b><br>
     * This method allows you to operate the sine function.
     * <b>Pre:</b>that the audio doesn't exist previously<br>
     * <b>Post:</b>The audio was added correctly<br>
     * @param content AudioContent. Variable containing the audio content. content != null
     * @return a boolean if the content was added or not
     */
    @Override
    public boolean add(AudioContent content) {
        return audioContentList.add(content);
    }

    /**
     * <b>Name:remove</b><br>
     * This method allows you to remove the sine function.
     * <b>Pre:</b>that the audio exists previously<br>
     * <b>Post:</b>The audio was removed correctly<br>
     * @param indexAudioContent int. Variable containing the position of the audio content. indexAudioContent != null
     * @return a boolean if the content was removed or not
     */
    @Override
    public boolean remove(int indexAudioContent) {
        int size = audioContentList.size();
        audioContentList.remove(indexAudioContent);
        return size - audioContentList.size() == 1;
    }

    /**
     * <b>Name:play</b><br>
     * This method allows you to play the audio content.
     * <b>Post:</b>play method was operated correctly<br>
     * @return a boolean if it was played or not
     */
    @Override
    public boolean play() {
        playbackStatus = true;
        return true;
    }

    /**
     * <b>Name:stop</b><br>
     * This method allows you to stop the audio content.
     * <b>Post:</b>stop method was operated correctly<br>
     * @return a boolean if it was stopped or not
     */
    @Override
    public boolean stop() {
        playbackStatus = false;
        return false;
    }

    /**
     * <b>Name:next</b><br>
     * This method allows you to the next audio content.
     * <b>Post:</b>next method was operated correctly<br>
     * @return a boolean if it went to the next audio content or not
     */
    @Override
    public int next() {
        if (lastContent.split(":")[1].equals("1")) return Integer.parseInt(lastContent.split(":")[0])+1;
        if (lastContent.split("-")[0].equals(""+(audioContentList.size()-1)) || lastContent.split(":")[1].equals("0")) return 0;
        return Integer.parseInt(lastContent.split("-")[0])+1;
    }

    /**
     * <b>Name:previous</b><br>
     * This method allows you to go to the previous audio content.
     * <b>Post:</b>previous method was operated correctly<br>
     * @return a boolean if it went to the previous content or not
     */
    @Override
    public int previous() {
        if (lastContent.split(":")[1].equals("0")) return 0;
        if (lastContent.split("-")[0].equals("0") || lastContent.split(":")[1].equals("1")) return audioContentList.size() - 1;
        return Integer.parseInt(lastContent.split("-")[0]) - 1;
    }

    /**
     * <b>Name:codeToSharing</b><br>
     * This method allows you to share a code.
     * <b>Post:</b> codeToSharing was operated correctly<br>
     * @return a String with the message with the code
     */
    @Override
    public String codeToSharing() {
        switch (sharingType()) {
            case 0: return "Empty playlist";
            case 1: return en.codeN();
            case 2: return en.codeT();
            case 3: return en.codeModifiedChessBoard();
        }
        return "0";
    }

    /**
     * <b>Name:sharingType</b><br>
     * This method allows you to knows that code type generate.
     * <b>Post:</b> Code type was operated correctly<br>
     * @return a int with the information about code type
     */
    @Override
    public int sharingType() {
        int cantSong = 0;
        int cantPodcast = 0;
        for (int i = 0; i < audioContentList.size(); i++) {
            if (audioContentList.get(i) instanceof Song) cantSong++;
            if (audioContentList.get(i) instanceof Podcast) cantPodcast++;
        }
        if (cantPodcast == 0) return 1;
        if (cantSong == 0) return 2;
        if (cantSong > 0 && cantPodcast > 0) return 3;
        return 0;
    }

    //Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getPlaybackStatus() {
        return playbackStatus;
    }

    public void setPlaybackStatus(boolean playbackStatus) {
        this.playbackStatus = playbackStatus;
    }

    public String getLastContent() {
        return lastContent;
    }

    public void setLastContent(String lastContent) {
        this.lastContent = lastContent;
    }

    public ArrayList<AudioContent> getAudioContentList() {
        return audioContentList;
    }

    public void setAudioContentList(ArrayList<AudioContent> audioContentList) {
        this.audioContentList = audioContentList;
    }

    @Override
    public String toString() {
        String message = "";
        for (int i = 0; i < audioContentList.size(); i++) {
            message += "  (" + (i+1) + ") " + audioContentList.get(i).getTitle() + " --" + (audioContentList.get(i) instanceof Song? "Song":"Podcast") + "--\n";
        }
        return "Playlist: " + title + "\n" + message;
    }
}
