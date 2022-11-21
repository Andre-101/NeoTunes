package ui;

import model.*;

import java.util.Scanner;

public class Manager {
    Playlist pl;
    Purchase pc;
    User user;
    ChronologicalFormat cf;
    Scanner sc;

    public Manager() {
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Manager m = new Manager();
        System.out.println("Hello world!");
        m.cf = new ChronologicalFormat(3,20,20);
        System.out.println(m.cf.toSeconds());
    }

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
}