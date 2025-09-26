public class Calculator {
    public enum SIGN {
        PLUS, MINUS, MULTIPLY, DIVIDE
    }

    private Double calculate(SIGN sign, Double arg1, Double arg2) {
        switch (sign) {
            case DIVIDE:
                if (arg2 == 0) return 0d;
                return arg1 / arg2;
            default:
                break;
        }
        return 0d;
    }
}