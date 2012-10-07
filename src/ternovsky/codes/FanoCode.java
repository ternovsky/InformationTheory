package ternovsky.codes;

import ternovsky.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 07.10.12
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public class FanoCode extends Code {

    private List<Pair<Character, StringBuilder>> characterStringBuilderPairs;

    public FanoCode(Alphabet initialAlphabet, Alphabet finalAlphabet, ProbableSource probableSource) {
        super(initialAlphabet, finalAlphabet);

        List<Pair<Character, Float>> pairs = probableSource.getSortedCharacterProbablePairs();
        characterStringBuilderPairs = new ArrayList<Pair<Character, StringBuilder>>(pairs.size());
        for (Pair<Character, Float> pair : pairs) {
            characterStringBuilderPairs.add(
                    new Pair<Character, StringBuilder>(pair.getFirst(), new StringBuilder())
            );
        }
        int middleIndex = findMiddleIndex(pairs);
        buildCodeTable('1', pairs, 0, middleIndex);
        buildCodeTable('0', pairs, middleIndex, pairs.size());

        for (Pair<Character, StringBuilder> pair : characterStringBuilderPairs) {
            Word word = new Word(pair.getSecond().toString());
            codeWordLength = word.getLength();
            characterWordMap.put(pair.getFirst(), word);
            wordCharacterMap.put(word, pair.getFirst());
        }
    }

    @Override
    public Word decode(Word word) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void buildCodeTable(Character character, List<Pair<Character, Float>> pairs, int startIndex, int finishIndex) {
        for (int i = startIndex; i < finishIndex; i++) {
            characterStringBuilderPairs.get(i).getSecond().append(character);
        }
        if (finishIndex - startIndex <= 1) {
            return;
        }
        int middleIndex = startIndex + findMiddleIndex(pairs.subList(startIndex, finishIndex - 1));
        buildCodeTable('1', pairs, startIndex, middleIndex);
        buildCodeTable('0', pairs, middleIndex, finishIndex);
    }

    private int findMiddleIndex(List<Pair<Character, Float>> pairs) {
        float leftSum = 0;
        float rightSum = 0;
        float difference;
        float minDifference = Float.POSITIVE_INFINITY;
        int middleIndex = 0;
        while (true) {
            for (int j = 0; j < pairs.size(); j++) {
                if (j <= middleIndex) {
                    leftSum += pairs.get(j).getSecond();
                } else {
                    rightSum += pairs.get(j).getSecond();
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
