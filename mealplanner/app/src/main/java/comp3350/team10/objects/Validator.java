package comp3350.team10.objects;

public class Validator {

    public static boolean validStringInput(String inputString) {
        return (inputString != null && inputString.length() > Constant.ENTRY_MIN_VALUE && inputString.length() <= Constant.ENTRY_MAX_VALUE);
    }

    public static boolean atLeastZero(int inputNumber) {
        return (inputNumber >= Constant.ENTRY_MIN_VALUE && inputNumber <= Constant.ENTRY_MAX_VALUE);
    }

    public static boolean atLeastOne(int inputNumber) {
        return (inputNumber > Constant.ENTRY_MIN_VALUE && inputNumber <= Constant.ENTRY_MAX_VALUE);
    }

    public static boolean atLeastZero(double inputNumber) {
        return (inputNumber >= Constant.ENTRY_MIN_VALUE && inputNumber <= Constant.ENTRY_MAX_VALUE);
    }

    public static boolean atLeastOne(double inputNumber) {
        return (inputNumber > Constant.ENTRY_MIN_VALUE && inputNumber <= Constant.ENTRY_MAX_VALUE);
    }
}
