

package reflections.game.internal;

class Cell {
    private Sign sign;

    public Cell() {
        sign = Sign.EMPTY;
    }

    public boolean isEmpty() {
        return sign == Sign.EMPTY;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }
}
