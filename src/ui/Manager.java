package ui;

import model.*;

import java.util.Scanner;

public class Manager {

    //Attributes
    final String errMessage1 = "option not available";
    final String errMessage2 = "only numeric values are allowed";
    
    //Relationship
    Controller cl;
    Scanner sc;

    //Builder
    public Manager() {
        cl = new Controller();
        sc = new Scanner(System.in);
    }
    
    //Executable
    public static void main(String[] args) {
        Manager m = new Manager();
        System.out.println("Hello world!");
        do {
            m.showMenu();
        } while (m.operation());
        System.out.println("Thanks for using app");
    }

    /**
     * <b>Name:operation</b><br>
     * This method allows you to operate according to the user's selection with regard to the menu the option to follow
     * <b>Pre:</b>The scanner variable is created, The menu was displayed and the error message must exist<br>
     * <b>Post:</b>The option has been successfully executed<br>
     * @return a boolean who will determine if the application closes
     */
    private boolean operation() {
        switch (validateInput(0,13)) {
            case 0: return false;
            case 1: registerUser(); break;
            case 2: registerAudioContent(); break;
            case 3: createPlaylist(); break;
            case 4: editPlaylist(); break;
            case 5: sharePlaylist(); break;
            case 6: playbackPlaylist(); break;
            case 7: buySong(); break;
            case 8: showPlays(); break;
            case 9: showMostListen(); break;
            case 10: topFive(); break;
            case 11: topTen(); break;
            case 12: showSold(); break;
            case 13: showBestSeller(); break;
        }
        return true;
    }

    private void editPlaylist() {
        System.out.println("");
    }

    private void createPlaylist() {
        System.out.println("Type nickname");
        String nickName = sc.nextLine();
        if (cl.searchUser(nickName) != null) {
            if (cl.searchUser(nickName) instanceof Consumer) {
                System.out.println("Type playlist title");
                String title = sc.nextLine();
                if (cl.createPlaylist(cl.searchUserPosition(nickName), title).equals("")) {
                    System.out.println("Create successful");
                }
                else System.out.println(cl.createPlaylist(cl.searchUserPosition(nickName), title));
            } else System.out.println("Invalid user, the user must be a consumer (Standard or Premium");
        } else System.out.println("Didn't find the user");
    }

    private void registerAudioContent() {
        String title, duration, album_description = null, pictureUrl;
        int hour, minute, second, genre_category = 0;
        double price = 0;
        System.out.println("Type nickname");
        String nickName = sc.nextLine();
        if (cl.searchUser(nickName) != null) {
            if (cl.searchUser(nickName) instanceof Producer) {
                System.out.println("Type title");
                title = sc.nextLine();
                do {
                    System.out.println("Type duration - Hours, Minutes, Seconds  (HH:MM:SS)\n  It will ask again if it does not comply with the format");
                    duration = sc.nextLine();
                } while (duration.split(":").length != 3);
                hour = Integer.parseInt(duration.split(":")[0]);
                minute = Integer.parseInt(duration.split(":")[1]);
                second = Integer.parseInt(duration.split(":")[2]);
                System.out.println("Type the representative image url");
                pictureUrl = sc.nextLine();
                if (cl.searchUser(nickName) instanceof Artist) {
                    System.out.println("Type album");
                    album_description = sc.nextLine();
                    System.out.println("Type genre\n (1)Rock\n (2)Pop\n (3)Trap\n (4)House");
                    genre_category = validateInput(1,4);
                    System.out.println("Type price");
                    price = sc.nextDouble();
                    sc.nextLine();
                }
                if (cl.searchUser(nickName) instanceof ContentCreator) {
                    System.out.println("Type description");
                    album_description = sc.nextLine();
                    System.out.println("Type category\n (1)Politics\n (2)Entertainment\n (3)Video games\n (4)Fashion");
                    genre_category = validateInput(1,4);
                }
                if (cl.registerAudioContent(cl.searchUserPosition(nickName), title, hour, minute, second, album_description, genre_category, pictureUrl, price))
                    System.out.println("Register successful");
                else System.out.println("Error register");
            } else System.out.println("Invalid user, the user must be a producer (Artist or Content Creator");
        } else System.out.println("Didn't find the user");
    }

    private void registerUser() {
        int typeUser;
        String message;
        String name = "";
        String representativeUrl = "";
        System.out.println("Type nickname");
        String nickname = sc.nextLine();
        System.out.println("Type id");
        String id = sc.nextLine();
        System.out.println(
                "What kind of user would you like to be?\n"+
                        "(1) Standard         --You can create playlist, add and buy songs but with restrictions--\n"+
                        "(2) Premium          --You can create playlists, add and buy unlimited songs, you can also play your playlist without ads--\n"+
                        "(3) Artist           --You can only add songs, but you can be famous and appear in our rankings--\n"+
                        "(4) Content Creator  --You can only add a podcast, but you can talk about what you are most\n"+
                        "                       passionate about and if you are very interesting you can appear in our ranking--"
        );
        typeUser = validateInput(1,4);
        switch (typeUser) {
            case 2:
                boolean loop = true;
                do {
                    System.out.println(
                            "Being a premium member costs $2.99\n"+
                                    "(1) Continue with Standard account\n"+
                                    "(2) Buy premium account access"
                    );
                    typeUser = validateInput(1, 2);
                    if (typeUser == 2) {
                        message = cl.buySimulate();
                        if (message.equals("")) {
                            loop = false;
                            message = "Successful purchase";
                        }
                        System.out.println(message);
                    } else loop = false;
                } while (loop);
                break;
            case 3:
            case 4:
                System.out.println("Type name");
                name = sc.nextLine();
                System.out.println("Type representative image url");
                representativeUrl = sc.nextLine();
        }
        cl.registerUser(nickname,id,name,representativeUrl,typeUser);
    }

    /**
     * <b>Name:showMenu</b><br>
     * This method allows you to show the menu.
     * <b>Post:</b>the menu was operated correctly<br>
     */
    private void showMenu() {
        System.out.println(
                "Menu:\n"+
                        "(1)  Register user\n"+
                        "(2)  Register song o podcast\n"+
                        "(3)  Create playlist\n"+
                        "(4)  Edit playlist\n"+
                        "(5)  Share playlist\n"+
                        "(6)  Playback playlist\n"+
                        "(7)  Buy song\n"+
                        "(8)  Show total plays\n"+
                        "(9)  Show genre or category most listen\n"+
                        "(10) Top 5 artist or content creator\n"+
                        "(11) Top 10 songs or podcast\n"+
                        "(12) Show song sold by genre\n"+
                        "(13) Show the best selling sold\n"+
                        "(0)  Exit"
        );
    }

    /**
     * <b>Name:validateInput</b><br>
     * This method allows to validate the user inputs in numbers
     * <b>Pre:</b>The scanner variable is created, define the number that will be requested and the error message must exist<br>
     * <b>Post:</b>The requested number has been validated as numerical data<br>
     * @param min Integer. Variable containing the minimum range in which the integer to be validated can take value. min != null
     * @param max Integer. Variable containing the maximum range in which the integer to be validated can take value. max != null
     * @return an integer that has been validated
     */
    public int validateInput(int min, int max){
        boolean loop = false;
        int value = min -1;
        while (!loop){
            String aux = sc.nextLine();
            if (!aux.matches("[0-9]+")) System.err.println(errMessage2);
            else{
                value = Integer.parseInt(aux);
                loop = inRange(min,max,value);
                if (!loop) System.err.println(errMessage1);
            }
        }
        System.out.println();
        return value;
    }

    /**
     * <b>Name:inRange</b><br>
     * This method checks whether an integer is in range from two integers (min and max).
     * <b>Pre:</b>The numbers must be previously validated<br>
     * <b>Post:</b>Checking was operated correctly<br>
     * @param min Integer. Variable containing the minimum range in which the integer to be validated can take value. min != null
     * @param max Integer. Variable containing the maximum range in which the integer to be validated can take value. max != null
     * @param n int. Variable containing the integer you want to check if it is in range. n != null
     * @return a boolean indicating whether it meets the condition
     */
    public boolean inRange(int min, int max, int n){
        return n >= min && n <= max;
    }
}