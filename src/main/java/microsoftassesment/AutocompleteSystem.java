package microsoftassesment;

import java.util.*;
import java.util.stream.Collectors;

class AutocompleteSystem {
    private final Map<String, Integer> cache = new HashMap<>();
    private String input = "";

    public AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i++) {
            cache.put(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            Integer count = cache.getOrDefault(input, 0);
            cache.put(input, ++count);
            input = "";
            return Collections.emptyList();
        }

        input += c;
        return cache.entrySet().stream()
                .filter(e -> e.getKey().startsWith(input))
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
