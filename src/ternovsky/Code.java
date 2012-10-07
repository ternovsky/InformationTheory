package ternovsky;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 06.10.12
 * Time: 23:38
 * To change this template use File | Settings | File Templates.
 */
public abstract class Code {

    protected Alphabet initialAlphabet;
    protected Alphabet finalAlphabet;
    protected Map<Character, Word> characterWordMap;
    protected Map<Word, Character> wordCharacterMap;

    protected Code(Alphabet initialAlphabet, Alphabet finalAlphabet) {
        this.initialAlphabet = initialAlphabet;
        this.finalAlphabet = finalAlphabet;
        characterWordMap = new HashMap<Character, Word>();
        wordCharacterMap = new HashMap<Word, Character>();
    }

    public Word encode(Word word) {
        List<Character> initialSigns = word.getSigns();
        List<Character> finalSigns = new ArrayList<Character>();
        for (Character initialSign : initialSigns) {
            finalSigns.addAll(characterWordMap.get(initialSign).getSigns());
        }
        return new Word(finalSigns);
    }

    public abstract Word decode(Word word);
}
