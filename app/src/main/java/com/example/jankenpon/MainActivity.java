package com.example.jankenpon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Viewとレイアウトの紐付け
        textUser = findViewById(R.id.textUser);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonRock:
                textUser.setText("あなたはグーを選択しました");
                break;
            case R.id.buttonScissors:
                textUser.setText("あなたはチョキを選択しました");
                break;
            case R.id.buttonPaper:
                textUser.setText("あなたはパーを選択しました");
                break;
        }
    }
}
