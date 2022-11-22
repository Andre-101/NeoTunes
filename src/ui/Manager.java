package ui;

import model.*;

import java.util.Scanner;

public class Manager {
    
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

    private boolean operation() {
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
                        "(13) Show the best selling sold"
        );
    }
/*
    public void play() {
        boolean stop = false;
        do {
            System.out.println(pl.getLastContent());
            System.out.println("1. play  2. pause  3. previous  4. next  0. exit");
            switch (sc.nextInt()) {
                case 0: stop = true; break;
                case 1:
                    if (pl.getLastContent().split(":")[1].equals("0")) System.out.println(pl.playAudioContent(0));
                    else if (pl.getPlaybackStatus()) System.out.println("Playing");
                    else if (Integer.parseInt(pl.getLastContent().split(":")[1]) > 0) {
                        if (Integer.parseInt(pl.getLastContent().split(":")[1]) == 1) System.out.println(pl.playAudioContent(0));
                        else System.out.println(pl.playAudioContent(Integer.parseInt(pl.getLastContent().split("-")[0])));
                        pl.deleteUpdateLastContent();
                    }
                    break;
                case 2:
                    if (pl.getPlaybackStatus()) {
                        pl.stop();
                        System.out.println("Stopped");
                    } else System.out.println("It's stopped");
                    break;
                case 3:
                    System.out.println(pl.playAudioContent(pl.previous()));
                    break;
                case 4:
                    System.out.println(pl.playAudioContent(pl.next()));
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (!stop);
    }
    
 */
}