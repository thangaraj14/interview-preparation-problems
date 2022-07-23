package multithreading.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WebCrawler {

    Set<String> visited = ConcurrentHashMap.newKeySet();
    Set<String> result = ConcurrentHashMap.newKeySet();

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {

        visited.add(startUrl);
        result.add(startUrl);
        List<Thread> threads = new ArrayList<>();

        for (String nextUrls : htmlParser.getUrls(startUrl)) {

            if (isUnderSameHost(startUrl, nextUrls) && visited.add(nextUrls)) {
                Thread t = new Thread(() -> crawl(nextUrls, htmlParser));
                threads.add(t);
            }
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(result);
    }

    private boolean isUnderSameHost(String u1, String u2) {
        String[] arr1 = u1.split("/");
        String d1 = arr1[0] + "//" + arr1[2];
        return u2.startsWith(d1);
    }

    interface HtmlParser {
        List<String> getUrls(String url);
    }



    public List<String> crawlUsingStreams(String startUrl, HtmlParser htmlParser) {
        String hostname = getHostname(startUrl);

        // We don't want to re-crawl pages, so we're going to use a Set that can be modified concurrently
        // The following link describes how it is implemented, if you're interested
        // https://github.com/frohoff/jdk8u-jdk/blob/master/src/share/classes/java/util/concurrent/ConcurrentHashMap.java#L271
        Set<String> visited = ConcurrentHashMap.newKeySet();
        visited.add(startUrl);

        // This is similar to map-reduce, but instead of reducing to a single value we collect the values
        return crawl(startUrl, htmlParser, hostname, visited)
                .collect(Collectors.toList());
    }

    private Stream<String> crawl(String startUrl, HtmlParser htmlParser, String hostname, Set<String> visited) {
        // htmlParser#getUrls returns a List<String>
        Stream<String> stream = htmlParser.getUrls(startUrl)
                // We process each url in parallel. The number of threads (parallelism) is decided by the JDK.
                .parallelStream()
                // We filter out external urls, meaning we don't continue processing them and they're not a part of the result
                // This method (isSameHostname) is thread-safe
                .filter(url -> isSameHostname(url, hostname))
                // visited is the concurrent set. The add method returns false if the url is already in the set. In that case we ignore the url
                // A Set is normally not thread-safe but the one we're using is.
                .filter(visited::add)
                // If the url passed both filters above, we call crawl on it (recursively). A url generates a Stream<String>
                // If we were to use .map we would get something like a List<Stream<String>>
                // but we need to return a single Stream so we can chain the calls. flatMap concat the multiple streams into a single one
                .flatMap(url -> crawl(url, htmlParser, hostname, visited));

        // We want to return the original crawled url as well as the url's we found by crawling it
        // Since startUrl is not part of the stream, we need to add it
        //
        // Think about what happens to the original url in the flatMap phase: it gets replaced by the Stream<String> of the crawl method
        // To keep the startUrl we concat it to the stream.
        return Stream.concat(Stream.of(startUrl), stream);
    }

    /** Returns the url without the path. (method name wasn't the best) */
    private String getHostname(String url) {
        // Start the search after the protocol (http:// is always in the url)
        int idx = url.indexOf('/', 7);
        // Return the url without the path
        return (idx != -1) ? url.substring(0, idx) : url;
    }

    private boolean isSameHostname(String url, String hostname) {
        if (!url.startsWith(hostname)) {
            return false;
        }
        return url.length() == hostname.length() || url.charAt(hostname.length()) == '/';
    }
}
