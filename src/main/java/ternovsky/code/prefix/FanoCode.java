package ternovsky.code.prefix;

import ternovsky.Alphabet;
import ternovsky.ProbableSource;
import ternovsky.Word;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 07.10.12
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public class FanoCode extends PrefixCode {

    public FanoCode(Alphabet initialAlphabet, Alphabet finalAlphabet, ProbableSource probableSource) {
        super(initialAlphabet, finalAlphabet, probableSource);
    }

    @Override
    protected void buildCodeTable() {
        List<Character> signs = new ArrayList<Character>(signProbableMap.keySet());
        int middleIndex = findMiddleIndex(signs);
        addCharToCodeWords('1', signs, 0, middleIndex);
        addCharToCodeWords('0', signs, middleIndex, signs.size());
    }

    private void addCharToCodeWords(Character character, List<Character> signs, int startIndex, int finishIndex) {
        for (int i = startIndex; i < finishIndex; i++) {
            signCodeWordMap.get(signs.get(i)).add(character);
        }
        if (finishIndex - startIndex <= 1) {
            return;
        }
        int middleIndex = startIndex + findMiddleIndex(signs.subList(startIndex, finishIndex - 1));
        addCharToCodeWords('1', signs, startIndex, middleIndex);
        addCharToCodeWords('0', signs, middleIndex, finishIndex);
    }

    private int findMiddleIndex(List<Character> signs) {
        float minDifference = Float.POSITIVE_INFINITY;
        int middleIndex = 0;
        while (true) {
            float leftSum = 0;
            float rightSum = 0;
            float difference;
            for (int j = 0; j < signs.size(); j++) {
                float signProbable = signProbableMap.get(signs.get(j));
                if (j <= middleIndex) {
                    leftSum += signProbable;
                } else {
                    rightSum += signProbable;
                }
            }
            difference = Math.abs(leftSum - rightSum);
            if (difference < minDifference) {
                minDifference = difference;
                middleIndex++;
            } else {
                break;
            }
        }
        return middleIndex;
    }
}
