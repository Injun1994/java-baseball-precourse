package baseball;

import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;

public class Application {
    public final static int STR_LENGTH = 3;
    static List<Integer> computerDigitList;
    static String computerNumber = "";
    String userAnswer = "";
    boolean strikeFlag = false;
    boolean restartFlag = false;
    int restartAnswer = 0;


    public void tellUser() {
        Game game = new Game();

        if(strikeFlag) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        } else {
            System.out.print("숫자를 입력해주세요 : ");
        }

        userAnswer = readLine();

        if(strikeFlag) {
            restartAnswer = game.restartGame(userAnswer);
            strikeFlag = false;
        } else {
            strikeFlag = game.startGame(userAnswer, computerNumber, computerDigitList, STR_LENGTH);
        }

        if(restartAnswer == 1) {
            computerDigitList = pickUniqueNumbersInRange(1, 9, STR_LENGTH);
            computerNumber = "";
            for(int i : computerDigitList) {
                computerNumber += i;
            }
            restartAnswer = 0;
        }
        if(restartAnswer == 2) {
            restartFlag = false;
        } else {
            tellUser();
        }
    }

    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현
   /*     camp.nextstep.edu.missionutils에서 제공하는 Randoms, Console API를 활용해 구현해야 한다.
        Random 값 추출은 camp.nextstep.edu.missionutils.Randoms의 pickNumberInRange()를 활용한다.
        사용자가 입력하는 값은 camp.nextstep.edu.missionutils.Console의 readLine()을 활용한다.*/

        Application application = new Application();
        application.restartFlag = true;

        computerDigitList = pickUniqueNumbersInRange(1, 9, STR_LENGTH);
        for(int i : computerDigitList) {
            computerNumber += i;
        }

        while(application.restartFlag) {
            application.tellUser();
        }
    }
}
