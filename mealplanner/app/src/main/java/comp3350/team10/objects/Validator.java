package comp3350.team10.objects;

import java.util.ArrayList;

public class Validator {

    public static void validArrayListNoNull(ArrayList<?> inputArrayList, String message) {
        if (inputArrayList == null || inputArrayList.contains(null))
        {
            throw new IllegalArgumentException(message +
                    " input list cannot be null cannot have null elements");
        }
    }

    public static void validArrayListAtLeastOne(ArrayList<?> inputArrayList, String message) {
        if (inputArrayList == null || inputArrayList.size() <= Constant.ENTRY_MIN_VALUE
                || inputArrayList.size() > Constant.ENTRY_MAX_VALUE)
        {
            throw new IllegalArgumentException(message +
                    " input list cannot be null and requires length between " + Constant.ENTRY_MIN_VALUE + "and" + Constant.ENTRY_MAX_VALUE);
        }
    }

    public static void validStringInputatLeastOne(String inputString, String message) {
        if (inputString == null
                || inputString.length() <= Constant.ENTRY_MIN_VALUE
                 || inputString.length() > Constant.ENTRY_MAX_VALUE)
        {
            throw new IllegalArgumentException(message + "received " + inputString +
                    " input cannot be null and requires string length between " + Constant.ENTRY_MIN_VALUE + "and" + Constant.ENTRY_MAX_VALUE);
        }
    }

    public static void validStringInputatLeastZero(String inputString, String message) {
        if (inputString == null
                || inputString.length() < Constant.ENTRY_MIN_VALUE
                || inputString.length() > Constant.ENTRY_MAX_VALUE)
        {
            throw new IllegalArgumentException(message + "received " + inputString +
                    " input cannot be null and requires string length between " + Constant.ENTRY_MIN_VALUE + "and" + Constant.ENTRY_MAX_VALUE);
        }
    }

    public static void atLeastZero(int inputNumber, String message) {
        if (inputNumber < Constant.ENTRY_MIN_VALUE
                || inputNumber > Constant.ENTRY_MAX_VALUE)
        {
            throw new IllegalArgumentException(message + "received " + inputNumber +
                    "input requires value between " + Constant.ENTRY_MIN_VALUE + "and" + Constant.ENTRY_MAX_VALUE);
        }
    }

    public static void atLeastOne(int inputNumber, String message) {
        if (inputNumber <= Constant.ENTRY_MIN_VALUE
                || inputNumber > Constant.ENTRY_MAX_VALUE)
        {
            throw new IllegalArgumentException(message + "received " + inputNumber +
                    "input requires value between " + Constant.ENTRY_MIN_VALUE + "and" + Constant.ENTRY_MAX_VALUE);
        }
    }

    public static void atLeastZero(double inputNumber, String message) {
        if (inputNumber < Constant.ENTRY_MIN_VALUE
                || inputNumber > Constant.ENTRY_MAX_VALUE)
        {
            throw new IllegalArgumentException(message + "received " + inputNumber +
                    "input requires value between " + Constant.ENTRY_MIN_VALUE + "and" + Constant.ENTRY_MAX_VALUE);
        }
    }

    public static void atLeastOne(double inputNumber, String message) {
        if (inputNumber <= Constant.ENTRY_MIN_VALUE
                || inputNumber > Constant.ENTRY_MAX_VALUE)
        {
            throw new IllegalArgumentException(message + "received " + inputNumber +
                    "input requires value between " + Constant.ENTRY_MIN_VALUE + "and" + Constant.ENTRY_MAX_VALUE);
        }
    }
}
