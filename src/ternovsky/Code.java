package ternovsky;

import java.util.HashMap;
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
    protected float codeWordLength;

    protected Code(Alphabet initialAlphabet, Alphabet finalAlphabet) {
        this.initialAlphabet = initialAlphabet;
        this.finalAlphabet = finalAlphabet;
        characterWordMap = new HashMap<Character, Word>();
        wordCharacterMap = new HashMap<Word, Character>();
    }

    public Word encode(Word word) {
        char[] chars = word.getCharacters();
        StringBuilder builder = new StringBuilder();
        for (char c : chars) {
            builder.append(characterWordMap.get(c));
        }
        return new Word(builder.toString());
    }

    public abstract Word decode(Word word);
}
