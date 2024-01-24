package baseball;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class User {

    protected String getInput(int length) {
        String user = readLine();
        boolean validated;

        if (length == Application.NUMBER_LENGTH) {
            validated = validateLine(user);
        } else {
            validated = validateDigit(user);
        }

        if (!validated && length == Application.NUMBER_LENGTH) {
            System.out.print(Print.TELL_USER_INPUT_NUMBER);
            user = getInput(length);
        }

        return user;
    }

    private boolean validateLine(String line) {
        boolean validated = false;

        try {
            if (line.length() == Application.NUMBER_LENGTH && Integer.parseInt(line) % 1 == 0) {
                validated = true;
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.getStackTrace();
        }
        return validated;
    }

    private boolean validateDigit(String digit) {
        boolean validated = false;

        try {
            if (Integer.parseInt(digit) == 1 || Integer.parseInt(digit) == 2) {
                validated = true;
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.getStackTrace();
        }
        return validated;
    }
}
