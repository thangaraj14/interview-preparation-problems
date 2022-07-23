

package reflections.topologicalsort.databases;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Simulates database
 */
public class Database {
    /// Game Name -> (Rating, Price)
    private Map<String, List<Float>> GAME_TO_PRICE = Map.of("Fortnite", Arrays.asList(5f, 10f),
            "Minecraft", Arrays.asList(4.3f, 100f),
            "League Of Legends", Arrays.asList(4.9f, 89f),
            "Ace Combat", Arrays.asList(4.8f, 50f),
            "StarCraft", Arrays.asList(4.7f, 66f),
            "Burnout", Arrays.asList(4.4f, 31f));


    public Set<String> readAllGames() {
        return Collections.unmodifiableSet(GAME_TO_PRICE.keySet());
    }

    public Map<String, Float> readGameToRatings(Set<String> games) {
        return GAME_TO_PRICE.entrySet()
                .stream()
                .filter(entry -> games.contains(entry.getKey()))
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, entry -> entry.getValue().get(0)));
    }

    public Map<String, Float> readGameToPrice(Set<String> games) {
        return GAME_TO_PRICE.entrySet()
                .stream()
                .filter(entry -> games.contains(entry.getKey()))
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, entry -> entry.getValue().get(1)));
    }
}
