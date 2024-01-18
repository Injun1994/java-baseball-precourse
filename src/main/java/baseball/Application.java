package baseball;

import org.junit.platform.commons.util.StringUtils;

import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static camp.nextstep.edu.missionutils.Randoms.*;

public class Application {

    static List<Integer> computer;
    static String cStr = "";
    static int num = 0;
    static String result = "";
    static boolean strikeFlag = false;
    static int strike = 0;
    static int ball = 0;
    final static String STRIKE_STR = "스트라이크";
    final static String BALL_STR = "볼";
    final static String NOTHING_STR = "낫싱";
    final static int STR_LENGTH = 3;


    static void checkStrikeOrBall(String userPoint, int idx) {
        if(strike == 3) {
            strikeFlag = true;
        }

        if(idx == 0) {
            strike = 0;
            ball = 0;
        } else if(idx >= STR_LENGTH) {
            return;
        }

        String com = computer.get(idx).toString();
        String user = Character.toString(userPoint.charAt(idx));

        if(com.equals(user)) {
            strike++;
        }

        if(!com.equals(user) && cStr.contains(user)) {
            ball++;
        }

        if(strike == 0) {
            result = ball + BALL_STR;
        } else if(ball == 0) {
            result = strike + STRIKE_STR;
        } else {
            result = strike + STRIKE_STR + " " + ball + BALL_STR;
        }

        if(idx + 1 == STR_LENGTH) {
            System.out.println(result);
        }

        checkStrikeOrBall(userPoint, ++idx);
    }

    static int checkNothing(String digitStr) {
        int answer = 0;

        if(!cStr.contains(digitStr)) {
            answer++;
        }

        return answer;
    }

    static boolean substrUserPoint(String userPoint) {
        boolean flag;
        int answer = 0;
        int idx = 0;

        while(idx < STR_LENGTH) {
            String dgt = Character.toString(userPoint.charAt(idx));
            answer += checkNothing(dgt);
            idx++;
        }

        if(answer == 3) {
            System.out.println(NOTHING_STR);
            flag = false;
        } else {
            checkStrikeOrBall(userPoint, 0);
            flag = strikeFlag;
        }
        return flag;
    }

    static boolean startGame(String line) {
        boolean flag;
        if(!StringUtils.isBlank(line) && line.length() == STR_LENGTH) {
            flag = substrUserPoint(line);
        } else {
            flag = false;
        }

        return flag;
    }

    static void showResult(String line) {
        if(startGame(line)) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String numStr = readLine();
            num = Integer.parseInt(numStr);
        }
    }

    static boolean tellUser() {
        boolean flag = true;
        String line = "";

        if(num == 1) {
            computer = pickUniqueNumbersInRange(1,9, STR_LENGTH);
            cStr = "";
            strikeFlag = false;
            for(int i : computer) {
                cStr += i;
            }
            num = 0;
        } else if(num == 2) {
            flag = false;
        }

        if(num != 2) {
            System.out.print("숫자를 입력해주세요 : ");
            line = readLine();
            showResult(line);
        }

        return flag;
    }

    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현
   /*     camp.nextstep.edu.missionutils에서 제공하는 Randoms, Console API를 활용해 구현해야 한다.
        Random 값 추출은 camp.nextstep.edu.missionutils.Randoms의 pickNumberInRange()를 활용한다.
        사용자가 입력하는 값은 camp.nextstep.edu.missionutils.Console의 readLine()을 활용한다.*/

        boolean flag = true;

        computer = pickUniqueNumbersInRange(1,9, STR_LENGTH);
        for(int i : computer) {
            cStr += i;
        }

        while(flag) {
            flag = tellUser();
        }

    }
}
