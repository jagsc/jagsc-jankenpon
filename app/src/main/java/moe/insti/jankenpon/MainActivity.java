package moe.insti.jankenpon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textUser, textCpu, textResult;
    private ImageView resultImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Viewとレイアウトの紐付け
        textUser = findViewById(R.id.textUser);
        textCpu = findViewById(R.id.textCpu);
        textResult = findViewById(R.id.textResult);
        resultImageView = findViewById(R.id.resultImage);

        Button rock = findViewById(R.id.buttonRock);
        Button scissors = findViewById(R.id.buttonScissors);
        Button paper = findViewById(R.id.buttonPaper);

        // ボタンが押されてるかどうかを確認するリスナーの追加
        rock.setOnClickListener(this);
        scissors.setOnClickListener(this);
        paper.setOnClickListener(this);
    }

    /**
     * じゃんけんを実行し結果を表示する
     */
    public void executeJanKenPonAndShowResult(String userAction) {
        JanKenPon janKenPon = new JanKenPon();

        String result = janKenPon.doJanKenPon(userAction);
        textUser.setText("あなたは" + userAction + "を選択しました。");
        textCpu.setText("CPUが" + janKenPon.cpuAction + "を出しました。");
        textResult.setText("結果："+ result + "！");

        // 結果の画像を設定する
        switch (janKenPon.cpuAction){
            case "グー":
                resultImageView.setImageResource(R.drawable.rock);
                break;
            case "チョキ":
                resultImageView.setImageResource(R.drawable.scissors);
                break;
            case "パー":
                resultImageView.setImageResource(R.drawable.paper);
                break;
        }
    }

    //ボタンが押されると結果を表示するメソッド
    @Override
    public void onClick(View view) {
        Button button = (Button) view;

        switch (button.getId()) {
            case R.id.buttonRock:
                executeJanKenPonAndShowResult("グー");
                break;
            case R.id.buttonScissors:
                executeJanKenPonAndShowResult("チョキ");
                break;
            case R.id.buttonPaper:
                executeJanKenPonAndShowResult("パー");
                break;
        }
    }
}
