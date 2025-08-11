package com.lgh.solofit_frontend.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lgh.solofit_frontend.R;
import com.lgh.solofit_frontend.dto.Market;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LinearLayout marketListLayout = findViewById(R.id.llMarketList);
        LayoutInflater inflater = LayoutInflater.from(this);

        List<Market> marketList = new ArrayList<>();

        marketList.add(new Market("댄싱컵 수영현대점", 5, R.drawable.coffee_icon));
        marketList.add(new Market("컴포즈커피 센텀비스타점", 0, R.drawable.coffee_icon));
        marketList.add(new Market("텐퍼센트커피 수영현대점", 5, R.drawable.coffee_icon));
        marketList.add(new Market("카페051 민락현대점", 11, R.drawable.coffee_icon));

        for (Market m : marketList) {
            View view = inflater.inflate(R.layout.item_main_market_list, marketListLayout, false);

            ImageView marketImg = view.findViewById(R.id.ivMarketImg);
            TextView marketName = view.findViewById(R.id.tvMarketName);
            TextView marketTime = view.findViewById(R.id.tvGetTime);

            marketImg.setImageResource(m.imageResId);
            marketName.setText(m.marketName);
            marketTime.setText(m.time == 0 ? "바로 수령 가능" : m.time + "분 후 수령");

            marketListLayout.addView(view);
        }
    }
}
