package moe.insti.jankenpon;

import java.util.Random;

public class JanKenPon {

    public String randomAction;

    public JanKenPon() {

    }

    public String JanKenPon(String userAction) {
        String matchResult = null;

        //1と3の間にランダムで数字を選択し、その結果によってString型でCPU側のプレイを定義します
        //これは独立したメソッドにした方がいいんですが無駄に複雑になります
        Random r = new Random();
        int Low = 1;
        int High = 4;
        int Result = r.nextInt(High-Low) + Low;

        if (Result == 1) {
            randomAction = "グー";
        } else if (Result == 2) {
            randomAction = "パー";
        } else if (Result == 3) {
            randomAction = "チョキ";
        }

        //どのボタンが押されてるかによってCPUのプレイと比べて試合の結果を決めます
        switch (userAction) {
            case "グー":
                if (randomAction == "グー") {
                    matchResult = "分け";
                } else if (randomAction == "パー") {
                    matchResult = "負け";
                } else if (randomAction == "チョキ") {
                    matchResult = "勝ち";
                }
                break;
            case "パー":
                if (randomAction == "パー") {
                    matchResult = "分け";
                } else if (randomAction == "チョキ") {
                    matchResult = "負け";
                } else if (randomAction == "グー") {
                    matchResult = "勝ち";
                    return matchResult;
                }
                break;
            case "チョキ":
                if (randomAction == "チョキ") {
                    matchResult = "分け";
                } else if (randomAction == "グー") {
                    matchResult = "負け";
                } else if (randomAction == "パー") {
                    matchResult = "勝ち";
                }
                break;
        }

        return matchResult; //その結果をリターンします
    }

}
