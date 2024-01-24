package baseball;

public class Point {

    protected String computerNumber;
    protected String userNumber;


    public Point(String computerNumber, String userNumber) {
        this.computerNumber = computerNumber;
        this.userNumber = userNumber;
    }


    protected int checkStrike(int idx) {
        int strike = 0;

        if(computerNumber.charAt(idx) == userNumber.charAt(idx)) {
            strike++;
        }

        return strike;
    }

    protected int checkBall(int idx) {
        int ball = 0;

        if(computerNumber.charAt(idx) != userNumber.charAt(idx) && computerNumber.contains(Character.toString(userNumber.charAt(idx)))) {
            ball++;
        }

        return ball;
    }

    protected boolean checkNothing() {
        boolean nothing = false;
        int idx = 0;

        while(idx < Application.NUMBER_LENGTH && !computerNumber.contains(Character.toString(userNumber.charAt(idx)))) {
            idx++;
        }

        if(idx == Application.NUMBER_LENGTH) {
            nothing = true;
        }

        return nothing;
    }
}
