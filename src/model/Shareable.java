package model;

public interface Shareable {

     /**
     * <b>Name:codeToSharing</b><br>
     * This method allows you to share a code.
     * <b>Post:</b> codeToSharing was operated correctly<br>
     * @return a String with the message with the code
     */
    public String codeToSharing();

    /**
     * <b>Name:sharingType</b><br>
     * This method allows you to knows that code type generate.
     * <b>Post:</b> Code type was operated correctly<br>
     * @return a int with the information about code type
     */
    public int sharingType();
}
