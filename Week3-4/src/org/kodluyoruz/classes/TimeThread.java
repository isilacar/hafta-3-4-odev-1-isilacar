package org.kodluyoruz.classes;

public class TimeThread implements Runnable {
    private City city;


    public TimeThread(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public void run() {

        city.showTime();


    }
}
