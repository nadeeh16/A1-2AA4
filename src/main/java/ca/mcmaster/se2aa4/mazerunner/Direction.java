package ca.mcmaster.se2aa4.mazerunner;

enum Direction {
    R, D, L, U;

    public Direction turnLeft() {
        switch (this) {
            case R: return U;
            case D: return R;
            case L: return D;
            case U: return L;
            default: throw new IllegalStateException("Unexpected value: " + this);
        }
    }
    public Direction turnRight() {
        switch (this) {
            case R: return D;
            case D: return L;
            case L: return U;
            case U: return R;
            default: throw new IllegalStateException("Unexpected value: " + this);
        }
    }

}
