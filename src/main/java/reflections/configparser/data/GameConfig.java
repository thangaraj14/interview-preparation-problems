

package reflections.configparser.data;

import java.util.Arrays;

public class GameConfig {
    private int releaseYear;
    private String gameName;
    private double price;
    private String[] characterNames;

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public String getGameName() {
        return this.gameName;
    }

    public double getPrice() {
        return this.price;
    }

    public String[] getCharacterNames() {
        return characterNames;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "releaseYear=" + releaseYear +
                ", gameName='" + gameName + '\'' +
                ", price=" + price +
                ", characterName=" + Arrays.toString(characterNames) +
                '}';
    }
}
