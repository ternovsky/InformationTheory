package ternovsky.code.prefix;

import ternovsky.Alphabet;
import ternovsky.Code;
import ternovsky.ProbableSource;
import ternovsky.Word;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 10.10.12
 * Time: 23:23
 * To change this template use File | Settings | File Templates.
 */
public abstract class PrefixCode extends Code {

    protected float averageCodeWordLength;
    protected Map<Character, List<Character>> signCodeWordMap;
    protected Map<Character, Float> signProbableMap;

    protected PrefixCode(Alphabet initialAlphabet, Alphabet finalAlphabet, ProbableSource probableSource) {
        super(initialAlphabet, finalAlphabet);

        signProbableMap = probableSource.getSignProbableMap();
        signCodeWordMap = new HashMap<Character, List<Character>>(signProbableMap.size());
        for (Character character : signProbableMap.keySet()) {
            signCodeWordMap.put(character, new LinkedList<Character>());
        }

        buildCodeTable();

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

    protected abstract void buildCodeTable();

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
}
