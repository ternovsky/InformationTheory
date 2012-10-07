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

    private Map<Character, Float> characterProbableMap;

    public ProbableSource(final Map<Character, Float> characterProbableMap) {
        this.characterProbableMap = new TreeMap<Character, Float>(new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                if (c1.equals(c2)) {
                    return 0;
                } else {
                    return characterProbableMap.get(c1) < characterProbableMap.get(c2) ? 1 : -1;
                }
            }
        });
        this.characterProbableMap.putAll(characterProbableMap);
    }

    public Map<Character, Float> getCharacterProbableMap() {
        return characterProbableMap;
    }
}
