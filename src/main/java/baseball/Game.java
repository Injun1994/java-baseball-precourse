package baseball;

import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Game {

    List<Integer> computerDigitList;
    String computerNumber = "";


    public int restartGame(String userAnswer) {
        int restartAnswer = 0;

        try {
            int digit = Integer.parseInt(userAnswer);
            if(digit >= 1 && digit <= 2) {
                restartAnswer = digit;
            }
        } catch(IllegalArgumentException argumentException) {
            argumentException.getStackTrace();
        }
        return restartAnswer;
    }

    public boolean startGame(String userAnswer, String comNumber, List<Integer> comDigitList, int length) {
        boolean gameEnd = false;
        Point point = new Point();

        if(StringUtils.isBlank(computerNumber) && computerDigitList == null) {
            computerNumber = comNumber;
            computerDigitList = new ArrayList<>(comDigitList);
        }

        try {
            if(Integer.parseInt(userAnswer) % 1 == 0 && userAnswer.length() == length) {
                gameEnd = point.substrUserPoint(userAnswer, computerNumber, computerDigitList, length);
            }
        } catch(IllegalArgumentException argumentException) {
            argumentException.getStackTrace();
        }
        return gameEnd;
    }
}
