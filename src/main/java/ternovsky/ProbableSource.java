package ternovsky;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 07.10.12
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class ProbableSource {

    private Map<Character, Float> signProbableMap;

    public ProbableSource(final Map<Character, Float> signProbableMap) {
        this.signProbableMap = new TreeMap<Character, Float>(new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                if (c1.equals(c2)) {
                    return 0;
                } else {
                    return signProbableMap.get(c1) < signProbableMap.get(c2) ? 1 : -1;
                }
            }
        });
        this.signProbableMap.putAll(signProbableMap);
    }

    public Map<Character, Float> getSignProbableMap() {
        return signProbableMap;
    }
}
