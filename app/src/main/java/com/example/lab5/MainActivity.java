package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ListView mainLV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Courier courier = new Courier("Khochenkov Alexey", "Driver");
        Order[] orders = {
                new Order(new Company("Mail", "Moscow"),
                        new SmallPackage(false, ""), "Moscow",
                        "Vladimir", 2400),
                new Order(new Company("Stolovoya", "Moscow"),
                        new BigPackage(true, 30), "Moscow",
                        "Piter", 900),
                new Order(new Company("Chinese Embassy",
                        "Beijing"), new Document(false,
                        "No water",
                        "Chinese Embassy - Kremlin"),
                        "Beijing",
                        "Moscow", 7000)
        };

        setContentView(R.layout.activity_main);

        TextView mainTV = (TextView) findViewById(R.id.mainTV);
        mainTV.setText(courier.getFullName());


        mainLV = (ListView) findViewById(R.id.mainLV);
        OrderAdapter orderAdapter = new OrderAdapter(this, orders);

        mainLV.setAdapter(orderAdapter);
        mainLV.setOnItemClickListener((parent, view, position, id) -> {

            TypedValue typedValue = new TypedValue();
            getTheme().resolveAttribute(android.R.attr.colorPressedHighlight, typedValue, true);
            int colorOnClicked = typedValue.data;

            TypedValue typedValue1 = new TypedValue();
            getTheme().resolveAttribute(android.R.attr.background, typedValue1, true);
            int colorInitial = typedValue1.data;


            Drawable background = view.getBackground();

            if (background instanceof ColorDrawable) {
                int backgroundColor = ((ColorDrawable) background).getColor();
                if (backgroundColor == colorInitial) {
                    courier.getOrders().add((Order) parent.getAdapter().getItem(position));
                    view.setBackgroundColor(colorOnClicked);
                } else {
                    courier.getOrders().remove((Order) parent.getAdapter().getItem(position));
                    view.setBackgroundColor(colorInitial);
                }
            } else {
                int backgroundColor = view.getDrawingCacheBackgroundColor();
                if (backgroundColor == colorInitial) {
                    courier.getOrders().add((Order) parent.getAdapter().getItem(position));
                    view.setBackgroundColor(colorOnClicked);
                } else {
                    courier.getOrders().remove((Order) parent.getAdapter().getItem(position));
                    view.setBackgroundColor(colorInitial);
                }
            }
        });
        Button clean_button = findViewById(R.id.clean_button);
        clean_button.setOnClickListener(v -> {
            TypedValue typedValue1 = new TypedValue();
            getTheme().resolveAttribute(android.R.attr.background, typedValue1, true);
            int colorInitial = typedValue1.data;
            for (int i = 0; i < mainLV.getChildCount(); ++i) {
                View listItem = mainLV.getChildAt(i);

                listItem.setBackgroundColor(colorInitial);
            }
            courier.getOrders().clear();
        });

        Button ok_button = findViewById(R.id.ok_button);
        ok_button.setOnClickListener(v -> {
            int sum = 0;
            for (Order order : courier.getOrders()) {
                sum += order.getPrice();
            }
            if (sum > 0) {
                Toast.makeText(MainActivity.this, String.valueOf(sum), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Please, choose orders", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
