package baseball;

import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static camp.nextstep.edu.missionutils.Randoms.*;

public class Application {

    public final static String STRIKE_STR = "스트라이크";
    public final static String BALL_STR = "볼";
    public final static String NOTHING_STR = "낫싱";
    public final static int STR_LENGTH = 3;
    static List<Integer> computerDigitList;
    String computerNumber = "";
    String resultPrint = "";
    String userAnswer = "";
    int restartAnswer = 0;
    boolean strikeFlag = false;
    int strike = 0;
    int ball = 0;


    public void checkStrikeOrBall(String userPoint, int idx) {
        if(strike == 3) {
            strikeFlag = true;
        }

        if(idx == 0) {
            strike = 0;
            ball = 0;
        } else if(idx >= STR_LENGTH) {
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

        if(idx + 1 == STR_LENGTH) {
            System.out.println(resultPrint);
        }

        checkStrikeOrBall(userPoint, ++idx);
    }

    public int checkNothing(String userPointDigit) {
        int answer = 0;

        if(!computerNumber.contains(userPointDigit)) {
            answer++;
        }

        return answer;
    }

    public boolean substrUserPoint(String userPoint) {
        boolean strikeResult;
        int nothing = 0;
        int idx = 0;

        while(idx < STR_LENGTH) {
            String dgt = Character.toString(userPoint.charAt(idx));
            nothing += checkNothing(dgt);
            idx++;
        }

        if(nothing == 3) {
            System.out.println(NOTHING_STR);
            strikeResult = false;
        } else {
            checkStrikeOrBall(userPoint, 0);
            strikeResult = strikeFlag;
        }
        return strikeResult;
    }

    public void restartGame() {
        try {
            int digit = Integer.parseInt(userAnswer);
            if(digit >= 1 && digit <= 2) {
                restartAnswer = digit;
            }
        } catch(IllegalArgumentException argumentException) {
            argumentException.getStackTrace();
        }

        if(restartAnswer == 1) {
            strikeFlag = false;
            computerDigitList = pickUniqueNumbersInRange(1, 9, STR_LENGTH);
            computerNumber = "";
            for(int i : computerDigitList) {
                computerNumber += i;
            }
            restartAnswer = 0;
        }
    }

    public boolean startGame() {
        boolean gameEnd = false;

        try {
            if(Integer.parseInt(userAnswer) % 1 == 0 && userAnswer.length() == STR_LENGTH) {
                gameEnd = substrUserPoint(userAnswer);
            }
        } catch(IllegalArgumentException argumentException) {
            argumentException.getStackTrace();
        }
        return gameEnd;
    }

    public boolean tellUser() {
        boolean restartFlag = true;

        if(strikeFlag) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        } else {
            System.out.print("숫자를 입력해주세요 : ");
        }

        userAnswer = readLine();

        if(strikeFlag) {
            restartGame();
        } else {
            startGame();
        }

        if(restartAnswer == 2) {
            restartFlag = false;
        } else {
            tellUser();
        }

        return restartFlag;
    }

    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현
   /*     camp.nextstep.edu.missionutils에서 제공하는 Randoms, Console API를 활용해 구현해야 한다.
        Random 값 추출은 camp.nextstep.edu.missionutils.Randoms의 pickNumberInRange()를 활용한다.
        사용자가 입력하는 값은 camp.nextstep.edu.missionutils.Console의 readLine()을 활용한다.*/

        boolean restartFlag = true;
        Application application = new Application();

        computerDigitList = pickUniqueNumbersInRange(1, 9, STR_LENGTH);
        for(int i : computerDigitList) {
            application.computerNumber += i;
        }

        while(restartFlag) {
            restartFlag = application.tellUser();
        }
    }
}
