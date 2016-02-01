package kuvaldis.algorithm.careercup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*

http://careercup.com/question?id=5733696185303040

i18n (where 18 stands for the number of letters between the first i and the last n in the word “internationalization,”) Wiki it.

Generate all such possible i18n strings for any given string. for eg. "careercup"=>"c7p","ca6p","c6up","car5p","ca5up","care4p","car4up","caree3p","care3up"..till the count is 0 which means its the complete string again.

 */
public class I18N {
    public List<String> solution(final String s) {
        if (s.length() < 3) {
            return Collections.singletonList(s);
        }
        final int n = s.length() - 2;
        final List<String> result = new ArrayList<>();
        for (int i = n; i > 0; i--) {
            for (int j = 1; j + i < s.length(); j++) {
                result.add(s.substring(0, j) + i + s.substring(j + i));
            }
        }
        return result;
    }
}
