package com.lgh.x_frenchtravel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BookingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        LinearLayout bookingListLayout = findViewById(R.id.bookingListLayout);
        LayoutInflater inflater = LayoutInflater.from(this);

        List<Book> bookList = new ArrayList<>();

        bookList.add(new Book("이기현", "Hotel1", "Tue, Sep 10,2024 to Sun, Sep 15,2024", "2 Adults, 0 Children, 1 Room", "For sightseeing   Pay with cash", "299"));
        bookList.add(new Book("이기현2", "Hotel2", "Tue, Sep 10,2024 to Sun, Sep 15,2024", "2 Adults, 1 Children, 1 Room", "For sightseeing   Pay with cash", "299"));
        bookList.add(new Book("이기현3", "Hotel3", "Tue, Sep 10,2024 to Sun, Sep 15,2024", "2 Adults, 0 Children, 1 Room", "For sightseeing   Pay with cash", "299"));
        bookList.add(new Book("이기현4", "Hotel4", "Tue, Sep 10,2024 to Sun, Sep 15,2024", "2 Adults, 3 Children, 1 Room", "For sightseeing   Pay with cash", "299"));
        bookList.add(new Book("이기현5", "Hotel5", "Tue, Sep 10,2024 to Sun, Sep 15,2024", "2 Adults, 2 Children, 1 Room", "For sightseeing   Pay with cash", "299"));
        bookList.add(new Book("이기현6", "Hotel6", "Tue, Sep 10,2024 to Sun, Sep 15,2024", "2 Adults, 1 Children, 1 Room", "For sightseeing   Pay with cash", "299"));
        bookList.add(new Book("이기현7", "Hotel7", "Tue, Sep 10,2024 to Sun, Sep 15,2024", "2 Adults, 6 Children, 1 Room", "For sightseeing   Pay with cash", "299"));

        int idx = 1;
        for(Book b : bookList) {
            View view = inflater.inflate(R.layout.item_booking_list, bookingListLayout, false);

            TextView sequenceText = view.findViewById(R.id.sequenceText);
            TextView usernameText = view.findViewById(R.id.usernameText);
            TextView hotelNameText = view.findViewById(R.id.bookedHotelNameText);
            TextView fullDateText = view.findViewById(R.id.bookedFullDateText);
            TextView peoplesText = view.findViewById(R.id.bookedPeopleText);
            TextView anotherInfosText = view.findViewById(R.id.bookedAnotherInfosText);
            TextView priceText = view.findViewById(R.id.bookedPriceText);

            sequenceText.setText(idx + "");
            usernameText.setText(b.username);
            hotelNameText.setText(b.hotelName);
            fullDateText.setText(b.fullDate);
            peoplesText.setText(b.peoples);
            anotherInfosText.setText(b.anotherInfos);
            priceText.setText(b.price);

            bookingListLayout.addView(view);

            idx++;
        }
    }
}

