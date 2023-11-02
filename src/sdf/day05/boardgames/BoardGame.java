package sdf.day05.boardgames;

public class BoardGame {

    private String name;
    private String year;
    private int minPlayTime;
    private int maxPlayTime;

    public BoardGame(String name, String year, int minPlayTime, int maxPlayTime) {
        this.name = name;
        this.year = year;
        this.minPlayTime = minPlayTime;
        this.maxPlayTime = maxPlayTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getMinPlayTime() {
        return minPlayTime;
    }

    public void setMinPlayTime(int minPlayTime) {
        this.minPlayTime = minPlayTime;
    }

    public int getMaxPlayTime() {
        return maxPlayTime;
    }

    public void setMaxPlayTime(int maxPlayTime) {
        this.maxPlayTime = maxPlayTime;
    }

    

    
    
}
