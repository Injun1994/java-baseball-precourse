package baseball;

import org.junit.platform.commons.util.StringUtils;

public class Application {

    protected final static int NUMBER_LENGTH = 3;
    private boolean continueFlag = true;
    private int replay = 0;
    private String computerNumber = "";


    public static void main(String[] args) {
        //숫자 야구 게임 구현
        Application application = new Application();

        while (application.continueFlag) {
            application.play();
        }
    }

    private void play() {
        System.out.print(Print.TELL_USER_INPUT_NUMBER);

        Computer computer = new Computer();
        User user = new User();

        Point point;
        int strike = 0;
        int ball = 0;
        boolean nothing;


        if (replay == 0 && StringUtils.isBlank(computerNumber)) {
            computerNumber = computer.getNumber();
        }
        if (replay == 1) {
            computerNumber = computer.getNumber();
            replay = 0;
        }

        point = new Point(computerNumber, user.getInput(NUMBER_LENGTH));

        nothing = point.checkNothing();
        if (nothing) {
            System.out.println(Print.NOTHING);
            return;
        }

        for (int i = 0; i < Application.NUMBER_LENGTH; i++) {
            strike += point.checkStrike(i);
            ball += point.checkBall(i);
        }

        if (ball == 0) {
            System.out.println(strike + Print.STRIKE);
        } else if (strike == 0) {
            System.out.println(ball + Print.BALL);
        } else {
            System.out.println(strike + Print.STRIKE + " " + ball + Print.BALL);
        }

        if (strike == 3) {
            replay = Integer.parseInt(askReplay());
        }

        if (replay == 2) {
            continueFlag = false;
        }
    }

    private String askReplay() {
        System.out.println(Print.STRIKE_AND_FINISH_GAME);
        System.out.println(Print.ASK_RESTART_OR_TERMINATE);

        User user = new User();

        String replay = user.getInput(1);

        if (!"1".equals(replay) && !"2".equals(replay)) {
            replay = askReplay();
        }

        return replay;
    }
}
