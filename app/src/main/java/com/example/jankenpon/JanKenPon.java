package com.example.jankenpon;

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
     * @return 結果 ("かち" または "まけ" または "あいこ")
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
        String matchResult = "";
        switch (userAction) {
            case "グー":
                if (Objects.equals(cpuAction, "グー")) {
                    matchResult = "あいこ";
                } else if (Objects.equals(cpuAction, "パー")) {
                    matchResult = "まけ";
                } else if (Objects.equals(cpuAction, "チョキ")) {
                    matchResult = "かち";
                }
                break;
            case "パー":
                if (Objects.equals(cpuAction,"パー")) {
                    matchResult = "あいこ";
                } else if (Objects.equals(cpuAction,"チョキ")) {
                    matchResult = "まけ";
                } else if (Objects.equals(cpuAction,"グー")) {
                    matchResult = "かち";
                }
                break;
            case "チョキ":
                if (Objects.equals(cpuAction,"チョキ")) {
                    matchResult = "あいこ";
                } else if (Objects.equals(cpuAction,"グー")) {
                    matchResult = "まけ";
                } else if (Objects.equals(cpuAction,"パー")) {
                    matchResult = "かち";
                }
                break;
        }

        // 勝敗結果をリターンする
        return matchResult;
    }

}
