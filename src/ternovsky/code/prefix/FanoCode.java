package ternovsky.code.prefix;

import ternovsky.Alphabet;
import ternovsky.Code;
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
public class FanoCode extends Code {

    private float averageCodeWordLength;
    private Map<Character, List<Character>> signCodeWordMap;
    private Map<Character, Float> signProbableMap;

    public FanoCode(Alphabet initialAlphabet, Alphabet finalAlphabet, ProbableSource probableSource) {
        super(initialAlphabet, finalAlphabet);

        signProbableMap = probableSource.getCharacterProbableMap();
        signCodeWordMap = new HashMap<Character, List<Character>>(signProbableMap.size());
        for (Character character : signProbableMap.keySet()) {
            signCodeWordMap.put(character, new LinkedList<Character>());
        }

        List<Character> signs = new ArrayList<Character>(signProbableMap.keySet());
        int middleIndex = findMiddleIndex(signs);
        buildCodeTable('1', signs, 0, middleIndex);
        buildCodeTable('0', signs, middleIndex, signs.size());

        int probableSum = 0;
        for (Character sign : signCodeWordMap.keySet()) {
            Word word = new Word(signCodeWordMap.get(sign));
            probableSum += signProbableMap.get(sign);
            averageCodeWordLength += word.getLength() * signProbableMap.get(sign);
            characterWordMap.put(sign, word);
            wordCharacterMap.put(word, sign);
        }
        averageCodeWordLength = averageCodeWordLength / signCodeWordMap.size() / probableSum;
    }

    @Override
    public Word decode(Word word) {
        List<Character> initialSigns = word.getSigns();
        List<Character> finalSigns = new ArrayList<Character>();
        List<Character> bufferSigns = new ArrayList<Character>();
        for (Character initialSign : initialSigns) {
            bufferSigns.add(initialSign);
            Character foundCharacter = wordCharacterMap.get(new Word(bufferSigns));
            if (foundCharacter != null) {
                finalSigns.add(foundCharacter);
                bufferSigns.clear();
            }

        }
        return new Word(finalSigns);
    }

    private void buildCodeTable(Character character, List<Character> signs, int startIndex, int finishIndex) {
        for (int i = startIndex; i < finishIndex; i++) {
            signCodeWordMap.get(signs.get(i)).add(character);
        }
        if (finishIndex - startIndex <= 1) {
            return;
        }
        int middleIndex = startIndex + findMiddleIndex(signs.subList(startIndex, finishIndex - 1));
        buildCodeTable('1', signs, startIndex, middleIndex);
        buildCodeTable('0', signs, middleIndex, finishIndex);
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
