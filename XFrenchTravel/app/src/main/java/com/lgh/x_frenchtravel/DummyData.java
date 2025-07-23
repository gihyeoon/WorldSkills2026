package com.lgh.x_frenchtravel;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    public static List<Hotel> getHotels() {
        List<Hotel> list = new ArrayList<>();

        list.add(new Hotel(1000, "First Hotel", 4, "0.75 km from Apls' ski lift", R.drawable._000));
        list.add(new Hotel(1001, "Second Hotel", 3, "0.75 km from Apls' ski lift", R.drawable._001));
        list.add(new Hotel(1002, "Third Hotel", 1, "0.75 km from Apls' ski lift", R.drawable._002));
        list.add(new Hotel(1003, "Fourth Hotel", 5, "0.75 km from Apls' ski lift", R.drawable._003));
        list.add(new Hotel(1004, "Fifth Hotel", 2, "0.75 km from Apls' ski lift", R.drawable._004));
        list.add(new Hotel(1005, "Sixth Hotel", 4, "0.75 km from Apls' ski lift", R.drawable._005));
        list.add(new Hotel(1006, "Seven Hotel", 4, "0.75 km from Apls' ski lift", R.drawable._006));
        list.add(new Hotel(1007, "eight Hotel", 4, "0.75 km from Apls' ski lift", R.drawable._007));
        list.add(new Hotel(1008, "nine Hotel", 4, "0.75 km from Apls' ski lift", R.drawable._008));
        list.add(new Hotel(1009, "ten Hotel", 4, "0.75 km from Apls' ski lift", R.drawable._009));

        return list;
    }

}
