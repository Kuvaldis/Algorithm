package kuvaldis.algorithm.strings;

import static java.util.stream.IntStream.range;

public class SearchString {

    public int search(final char[] string, final char[] substring) {
        return range(0, string.length - substring.length + 1).filter(i ->
                range(0, substring.length).allMatch(j ->
                        substring[j] == string[i + j])).findFirst().orElse(-1);
    }
}
