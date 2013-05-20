package main;

public enum Value {

    X, O, _;

    public static Value parseValue(String str) {
        switch (str) {
            case "X": {
                return Value.X;
            }
            case "O": {
                return Value.O;
            }
            default: {
                return Value._;
            }
        }

    }

    public int toInteger() {
        if (this.equals(Value.O)) {
            return 1;
        }
        if (this.equals(Value.X)) {
            return -1;
        }
        return 0;
    }
}
