package org.kodluyoruz;

import org.kodluyoruz.classes.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    static Map<String, City> mapCities;
    final static List<String> citiesCodes = new ArrayList<>();
    KeyEvent e;

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ExecutorService service = Executors.newFixedThreadPool(5);

        orderingCities();
        List<String> selectedCodes = new ArrayList<>();


        System.out.println("Please select city codes that you want to see the clock." +
                "\nAt least 3,up to 5..How many you want to select..");

        int selected;
        while (true) {
            try {
                selected = scanner.nextInt();

                while (selected < 3 || selected > 5) {
                    System.out.println("Please select 3,4 or 5");
                    selected = scanner.nextInt();
                }
                scanner.nextLine();
                System.out.println("Please enter the codes..");

                for (int i = 1; i <= selected; i++) {
                    System.out.print((i) + " = ");
                    String cityCode = scanner.nextLine().toUpperCase();

                    while (cityCode.length() != 3 || !(citiesCodes.contains(cityCode))) {
                        System.out.println("Please type the correct city code..");
                        System.out.print((i) + " = ");
                        cityCode = scanner.nextLine().toUpperCase();
                    }
                    selectedCodes.add(cityCode);

                }

                //getting cityName with the cityCode to the newCityList
                List<City> newCityList = new LinkedList<>();
                for (int i = 0; i < selectedCodes.size(); i++) {
                    City getNewCity = getValueByKey(mapCities, selectedCodes.get(i));
                    newCityList.add(getNewCity);
                }
                Collections.sort(newCityList);

                // TODO: 12/3/2020  You will add CTRL+X control to end the program

                for (City c : newCityList) {
                    Runnable timeThread = new TimeThread(c);
                    service.execute(timeThread);
                }
                service.shutdown();
            } catch (InputMismatchException e) {
                System.out.println("Please insert number format..");
                scanner.nextLine();
            }
        }
    }

    private static void citiesAddingToMap() {
        City moscow = new Moscow("MOSCOW", "MOW", GmtEnum.MOSCOW);
        City newdelhi = new Newdelhi("NEWDELHİ", "DEL", GmtEnum.NEWDELHI);
        City newyork = new Newyork("NEWYORK", "NYC", GmtEnum.NEWYORK);
        City london = new London("LONDON", "LON", GmtEnum.LONDON);
        City berlin = new Berlin("BERLIN", "BER", GmtEnum.BERLIN);


        //use this array in main method,while user adding the city codes
        citiesCodes.add(moscow.getCityCode());
        citiesCodes.add(newyork.getCityCode());
        citiesCodes.add(newdelhi.getCityCode());
        citiesCodes.add(london.getCityCode());
        citiesCodes.add(berlin.getCityCode());


        mapCities = new HashMap<>();
        mapCities.put(moscow.getCityCode(), moscow);
        mapCities.put(newyork.getCityCode(), newyork);
        mapCities.put(london.getCityCode(), london);
        mapCities.put(berlin.getCityCode(), berlin);
        mapCities.put(newdelhi.getCityCode(), newdelhi);
    }

    private static void orderingCities() {
        citiesAddingToMap();

        List<City> cityName = new ArrayList<>(mapCities.values());

        Collections.sort(cityName);
        for (City sehir : cityName) {
            System.out.println(sehir.getCityCode() + " = " + sehir.getCityName());
        }
    }


    public static City getValueByKey(Map<String, City> map, String key) {
        for (Map.Entry<String, City> entry : map.entrySet()) {
            if (Objects.equals(key, entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

}

