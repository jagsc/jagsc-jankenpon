package moe.insti.jankenpon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private JanKenPon JanKenPon;
    private TextView textUser, textCpu, textResult;

    //ボタンが押されてるかどうかを確認するリスナーの追加
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rock = findViewById(R.id.buttonRock);
        Button scissors = findViewById(R.id.buttonScissors);
        Button paper = findViewById(R.id.buttonPaper);

        rock.setOnClickListener(this);
        scissors.setOnClickListener(this);
        paper.setOnClickListener(this);
    }

    //結果を表示するメソッド
    public void showResult(String userAction) {
        String result;
        JanKenPon = new JanKenPon();

        textUser = findViewById(R.id.textUser);
        textCpu = findViewById(R.id.textCpu);
        textResult = findViewById(R.id.textResult);

        result = JanKenPon.JanKenPon(userAction);
        textUser.setText("あなたは" + userAction + "を選択しました。");
        textCpu.setText("CPUが" + JanKenPon.randomAction + "を出しました。");
        textResult.setText("結果："+ result + "！");
    }

    //ボタンが押されると結果を表示するメソッド
    @Override
    public void onClick(View view) {
        Button button = (Button) view;

        switch (button.getId()) {
            case R.id.buttonRock:
                showResult("グー");
                break;
            case R.id.buttonScissors:
                showResult("チョキ");
                break;
            case R.id.buttonPaper:
                showResult("パー");
                break;
        }
    }
}
