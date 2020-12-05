package org.kodluyoruz.classes;

public enum GmtEnum {
    MOSCOW(3),
    NEWYORK(-5),
    LONDON(0),
    BERLIN(1),
    NEWDELHI(5);

    GmtEnum(int gmt) {

        this.gmt = gmt;
    }
    private int gmt;

    public int getGmt() {
        return gmt;
    }



}
