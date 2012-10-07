package ternovsky;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 07.10.12
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class ProbableSource {

    private List<Pair<Character, Float>> characterProbablePairs;

    public ProbableSource(Collection<Pair<Character, Float>> characterProbablePairs) {
        this.characterProbablePairs = new ArrayList<Pair<Character, Float>>(characterProbablePairs);
        Collections.sort(this.characterProbablePairs, new Comparator<Pair<Character, Float>>() {
            @Override
            public int compare(Pair<Character, Float> p1, Pair<Character, Float> p2) {
                if (p1.equals(p2)) {
                    return 0;
                } else {
                    return p1.getSecond() < p2.getSecond() ? 1 : -1;
                }
            }
        });
    }

    public List<Pair<Character, Float>> getSortedCharacterProbablePairs() {
        return characterProbablePairs;
    }
}
