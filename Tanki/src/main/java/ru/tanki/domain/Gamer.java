package ru.tanki.domain;

public class Gamer {

    private String nameId;

    private int time;

    public Gamer(String nameId, int time) {
        this.nameId = nameId;
        this.time = time;
    }

    public String getNameId() {
        return nameId;
    }

    public int getTime() {
        return time;
    }
}
