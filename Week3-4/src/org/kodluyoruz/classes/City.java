package org.kodluyoruz.classes;

import org.kodluyoruz.interfaces.Clock;

import java.sql.Time;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class City implements Clock, Comparable<City> {

    private String cityName;
    private String cityCode;

    private GmtEnum gmtEnum;

    public City(String cityName, String cityCode, GmtEnum gmtEnum) {
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.gmtEnum = gmtEnum;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public GmtEnum getGmtEnum() {
        return gmtEnum;
    }

    public void setGmtEnum(GmtEnum gmtEnum) {
        this.gmtEnum = gmtEnum;
    }

    @Override
    public int compareTo(City c) {

        char thisCityName = this.getCityName().charAt(0);
        char cCityName = c.getCityName().charAt(0);

        if (thisCityName > cCityName) {
            return 1;
        } else if (thisCityName < cCityName) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public void showTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        while (true) {
            LocalTime time = LocalTime.now().minusHours(3); //to set GMT = 0
            time = time.plusHours(this.getGmtEnum().getGmt()); //get gmt difference and add it to default time as a hour

            System.out.print(this.getCityName() + " = " + time.format(formatter) + "\r");
            try {
                TimeUnit.NANOSECONDS.sleep(1_000_000_000 - time.getNano());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}




