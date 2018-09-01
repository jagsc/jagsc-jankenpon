package moe.insti.jankenpon;

import java.util.Random;

public class JanKenPon {

    public int drawValue, lossValue, winValue;

    public String randomAction;

    public JanKenPon() {

    }

    public String JanKenPon(String userAction) {
        String matchResult = null;

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

        switch (userAction) {
            case "グー":
                if (randomAction == "グー") {
                    matchResult = "分け";
                    drawValue += 1;
                } else if (randomAction == "パー") {
                    matchResult = "負け";
                    lossValue += 1;
                } else if (randomAction == "チョキ") {
                    matchResult = "勝ち";
                    winValue += 1;
                }
                break;
            case "パー":
                if (randomAction == "パー") {
                    matchResult = "分け";
                    drawValue += 1;
                } else if (randomAction == "チョキ") {
                    matchResult = "負け";
                    lossValue += 1;
                } else if (randomAction == "グー") {
                    matchResult = "勝ち";
                    winValue += 1;
                    return matchResult;
                }
                break;
            case "チョキ":
                if (randomAction == "チョキ") {
                    matchResult = "分け";
                    drawValue += 1;
                } else if (randomAction == "グー") {
                    matchResult = "負け";
                    lossValue += 1;
                } else if (randomAction == "パー") {
                    matchResult = "勝ち";
                    winValue += 1;
                }
                break;
        }

        return matchResult;
    }

}
