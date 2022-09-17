

package reflections.game.internal;

enum Sign {
    EMPTY(' '),
    X('X'),
    Y('Y');

    private char value;

    Sign(char value) {
        this.value = value;
    }

    public char getValue() {
        return this.value;
    }
}
