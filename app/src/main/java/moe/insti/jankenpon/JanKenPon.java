package moe.insti.jankenpon;

import java.util.Objects;
import java.util.Random;

/**
 * じゃんけんぽんを実行するクラス
 */
public class JanKenPon {

    /**
      * CPUの出した手を格納
      */
    public String cpuAction;

    /**
     * じゃんけんぽんを実行します
     * @param userAction ユーザーの選んだ手 ("グー" または "チョキ" または "パー")
     * @return 結果 ("勝ち" または "負け" または "分け")
     */
    public String doJanKenPon(String userAction) {
        // CPUの手を決定する。
        Random r = new Random();
        int randomInt = r.nextInt(3); // 0以上3未満の数値をランダムに設定する
        switch (randomInt) {
            case 0:
                cpuAction = "グー";
                break;
            case 1:
                cpuAction = "チョキ";
                break;
            case 2:
                cpuAction = "パー";
                break;
        }

        // ユーザーの手とCPUの手に応じて勝敗を判定する
        String matchResult;
        switch (userAction) {
            case "グー":
                if (Objects.equals(cpuAction, "グー")) {
                    matchResult = "分け";
                } else if (Objects.equals(cpuAction, "パー")) {
                    matchResult = "負け";
                } else if (Objects.equals(cpuAction, "チョキ")) {
                    matchResult = "勝ち";
                }
                break;
            case "パー":
                // TODO: 以下 == を equals に変更する
                if (cpuAction == "パー") {
                    matchResult = "分け";
                } else if (cpuAction == "チョキ") {
                    matchResult = "負け";
                } else if (cpuAction == "グー") {
                    matchResult = "勝ち";
                    return matchResult;
                }
                break;
            case "チョキ":
                if (cpuAction == "チョキ") {
                    matchResult = "分け";
                } else if (cpuAction == "グー") {
                    matchResult = "負け";
                } else if (cpuAction == "パー") {
                    matchResult = "勝ち";
                }
                break;
        }

        // 勝敗結果をリターンする
        return matchResult;
    }

}
