package baseball;

import java.util.List;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;

public class Computer {

    protected String getNumber() {
        List<Integer> computerDigitList = pickUniqueNumbersInRange(1, 9, Application.NUMBER_LENGTH);

        String computerNumber = "";

        for (int i : computerDigitList) {
            computerNumber += i;
        }

        return computerNumber;
    }

}
