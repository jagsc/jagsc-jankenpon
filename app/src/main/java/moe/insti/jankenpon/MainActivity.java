package moe.insti.jankenpon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private JanKenPon JanKenPon;
    private TextView textUser, textCpu, textResult;
    private Bitmap rockBitmap, scissorsBitmap, paperBitmap;
    private ImageView resultImageView;
    private SharedPreferences data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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
        rockBitmap = getBitmap(assets,"rock.png");
        scissorsBitmap = getBitmap(assets,"scissors.png");
        paperBitmap = getBitmap(assets,"paper.png");

        data = getSharedPreferences("result", Context.MODE_PRIVATE);
    }

    //結果を表示するメソッド
    public void showResult(String userAction) {
        String result;
        JanKenPon = new JanKenPon();

        result = JanKenPon.JanKenPon(userAction);
        textUser.setText("あなたは" + userAction + "を選択しました。");
        textCpu.setText("CPUが" + JanKenPon.randomAction + "を出しました。");
        textResult.setText("結果："+ result + "！");

        switch (result){
            case "勝ち":
                updateResultData("win");
                break;
            case "負け":
                updateResultData("lose");
                break;
            case "分け":
                updateResultData("draw");
                break;
        }

        // 結果の画像を設定する
        switch (JanKenPon.randomAction){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_result:
                showDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("対戦結果")
                .setMessage(getString(R.string.result_text,
                        data.getInt("total",0),
                        data.getInt("win",0),
                        data.getInt("lose",0),
                        data.getInt("draw",0)
                        ));

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        builder.setNeutralButton(R.string.reset, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = data.edit();
                editor.putInt("total",0);
                editor.putInt("win",0);
                editor.putInt("lose",0);
                editor.putInt("draw",0);
                editor.apply();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public Bitmap getBitmap(AssetManager assets,String filename){
        try (InputStream inputStream = assets.open(filename)){
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateResultData(String param){
        SharedPreferences.Editor editor = data.edit();
        editor.putInt("total",data.getInt("total",0) + 1);
        editor.putInt(param,data.getInt(param,0) + 1);
        editor.apply();
    }
}
