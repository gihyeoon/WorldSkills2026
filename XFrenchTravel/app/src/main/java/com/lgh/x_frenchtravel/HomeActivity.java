package com.lgh.x_frenchtravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    public static int hotelId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LinearLayout itemLayout = findViewById(R.id.homeItemListLayout);
        LayoutInflater inflater = LayoutInflater.from(this);

        List<Hotel> hotelList = DummyData.getHotels();

        for (Hotel h : hotelList) {
            View itemView = inflater.inflate(R.layout.item_hotel, itemLayout, false);

            ImageView hotelImage = itemView.findViewById(R.id.hotelImage);
            TextView hotelNameText = itemView.findViewById(R.id.hotelNameText);
            RatingBar hotelRatingBar = itemView.findViewById(R.id.ratingBar);
            TextView hotelDistanceText = itemView.findViewById(R.id.hotelDistanceText);
            Button hotelBookBtn = itemView.findViewById(R.id.hotelBookBtn);

            hotelImage.setImageResource(h.imageResId);
            hotelNameText.setText(h.name);
            hotelRatingBar.setRating(h.rating);
            hotelDistanceText.setText(h.distance);
            hotelBookBtn.setOnClickListener(e -> {
                hotelId = h.id;
                startActivity(new Intent(this.getApplicationContext(), BookingListActivity.class));
            });

            itemLayout.addView(itemView);
        }
    }
}
