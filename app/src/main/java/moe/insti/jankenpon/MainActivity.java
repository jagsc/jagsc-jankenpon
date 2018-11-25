package moe.insti.jankenpon;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textUser, textCpu, textResult;
    private Bitmap rockBitmap, scissorsBitmap, paperBitmap;
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

        // 画像をassetsから読み込み
        AssetManager assets = getResources().getAssets();

        try {
            InputStream inputStream = assets.open("rock.png");
            rockBitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            inputStream = assets.open("scissors.png");
            scissorsBitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            inputStream = assets.open("paper.png");
            paperBitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                resultImageView.setImageBitmap(rockBitmap);
                break;
            case "チョキ":
                resultImageView.setImageBitmap(scissorsBitmap);
                break;
            case "パー":
                resultImageView.setImageBitmap(paperBitmap);
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
