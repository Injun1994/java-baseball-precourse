package baseball;

import java.util.List;

public class Point {
    public final static String STRIKE_STR = "스트라이크";
    public final static String BALL_STR = "볼";
    public final static String NOTHING_STR = "낫싱";
    String resultPrint = "";
    int strike = 0;
    int ball = 0;
    boolean strikeFlag = false;

    public void checkStrikeOrBall(String userPoint, String computerNumber, List<Integer> computerDigitList, int idx, int length) {
        if(strike == 3) {
            strikeFlag = true;
        }

        if(idx == 0) {
            strike = 0;
            ball = 0;
        } else if(idx >= length) {
            strike = 0;
            ball = 0;
            return;
        }

        String com = computerDigitList.get(idx).toString();
        String user = Character.toString(userPoint.charAt(idx));

        if(com.equals(user)) {
            strike++;
        }

        if(!com.equals(user) && computerNumber.contains(user)) {
            ball++;
        }

        if(strike == 0) {
            resultPrint = ball + BALL_STR;
        } else if(ball == 0) {
            resultPrint = strike + STRIKE_STR;
        } else {
            resultPrint = strike + STRIKE_STR + " " + ball + BALL_STR;
        }

        if(idx + 1 == length) {
            System.out.println(resultPrint);
        }

        if(!strikeFlag) {
            checkStrikeOrBall(userPoint, computerNumber, computerDigitList, ++idx, length);
        }
    }

    public int checkNothing(String userPointDigit, String computerNumber) {
        int answer = 0;

        if(!computerNumber.contains(userPointDigit)) {
            answer++;
        }

        return answer;
    }

    public boolean substrUserPoint(String userPoint, String computerNumber, List<Integer> computerDigitList, int length) {
        boolean strikeResult;
        int nothing = 0;
        int idx = 0;

        while(idx < length) {
            String dgt = Character.toString(userPoint.charAt(idx));
            nothing += checkNothing(dgt, computerNumber);
            idx++;
        }

        if(nothing == 3) {
            System.out.println(NOTHING_STR);
            strikeResult = false;
        } else {
            checkStrikeOrBall(userPoint, computerNumber, computerDigitList, 0, length);
            strikeResult = strikeFlag;
        }
        return strikeResult;
    }
}
