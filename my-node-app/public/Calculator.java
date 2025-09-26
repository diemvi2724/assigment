public class Calculator {
    public enum SIGN {
        PLUS, MINUS, MULTIPLY, DIVIDE
    }

    private Double calculate(SIGN sign, Double arg1, Double arg2) {
        switch (sign) {
            case MULTIPLY:
                return arg1 * arg2;
            default:
                break;
        }
        return 0d;
    }
}