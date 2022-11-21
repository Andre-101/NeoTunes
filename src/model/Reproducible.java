package model;

public interface Reproducible {
    /**
     * <b>Name:play</b><br>
     * This method allows you to play the audio content.
     * <b>Post:</b>play method was operated correctly<br>
     * @return a boolean if it was played or not
     */
    public boolean play();

     /**
     * <b>Name:stop</b><br>
     * This method allows you to stop the audio content.
     * <b>Post:</b>stop method was operated correctly<br>
     * @return a boolean if it was stopped or not
     */
    public boolean stop();

     /**
     * <b>Name:next</b><br>
     * This method allows you to the next audio content.
     * <b>Post:</b>next method was operated correctly<br>
     * @return an int with the next audio content position
     */
    public int next();

     /**
     * <b>Name:previous</b><br>
     * This method allows you to go to the previous audio content.
     * <b>Post:</b>previous method was operated correctly<br>
     * @return an int with the previous content position 
     */
    public int previous();
}
