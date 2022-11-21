package model;

public class ChronologicalFormat {
    //Attributes
    private int hour;
    private int minute;
    private int second;

    //Builder
    public ChronologicalFormat(int hour, int minute, int second) {
        this.second = second;
        this.minute = minute;
        this.hour = hour;
        verifyFormat();
    }

    /**
     * <b>Name:addTime</b><br>
     * This method allows you to operate the time function.
     * <b>Pre:</b>The numbers must be previously validated<br>
     * <b>Post:</b>Time function was operated correctly<br>
     * @param hour   int. Variable containing the hour. hour != null
     * @param minute int. Variable that contains the minute. minute != null
     * @param minute int. Variable that contains the second. second != null
     */
    public void addTime(int hour, int minute, int second) {
        this.second += second;
        this.minute += minute;
        this.hour += hour;
        verifyFormat();
    }

    /**
     * <b>Name:verifyFormat</b><br>
     * This method allows you to verify the format.
     * <b>Pre:</b>The numbers must be previously validated as Integer<br>
     * <b>Post:</b>Verify format function was operated correctly<br>
     */
    public void verifyFormat() {
        if (second > 59) {
            minute += second / 60;
            second -= ((second / 60) * 60);
        }
        if (minute > 59) {
            hour += minute / 60;
            minute -= ((minute / 60) * 60);
        }
    }

    public int toSeconds() {
        return hour * 3600 + minute * 60 + second;
    }

    //Getters and setters

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }


    @Override
    public String toString() {
        return (hour < 10? "0"+hour:hour) + ":" + (minute < 10? "0"+minute:minute) + ":" + (second < 10? "0"+second:second);
    }
}
