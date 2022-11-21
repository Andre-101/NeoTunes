package model;

public interface Editable {
    /**
     * <b>Name:add</b><br>
     * This method allows you to operate the sine function.
     * <b>Pre:</b>that the audio doesn't exist previously<br>
     * <b>Post:</b>The audio was added correctly<br>
     * @param content AudioContent. Variable containing the audio content. content != null
     * @return a boolean if the content was added or not
     */
    public boolean add(AudioContent content);
    /**
     * <b>Name:remove</b><br>
     * This method allows you to remove the sine function.
     * <b>Pre:</b>that the audio exists previously<br>
     * <b>Post:</b>The audio was removed correctly<br>
     * @param indexAudioContent int. Variable containing the position of the audio content. indexAudioContent != null
     * @return a boolean if the content was removed or not
     */
    public boolean remove(int indexAudioContent);
}
