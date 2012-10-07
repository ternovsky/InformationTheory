package ternovsky.code;

import ternovsky.Alphabet;
import ternovsky.Code;
import ternovsky.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 07.10.12
 * Time: 1:23
 * To change this template use File | Settings | File Templates.
 */
public class RegularCode extends Code {

    private int codeWordLength;

    public RegularCode(Alphabet initialAlphabet, Alphabet finalAlphabet, Map<Character, Word> codeTable) {
        super(initialAlphabet, finalAlphabet);
        for (Character character : codeTable.keySet()) {
            Word word = codeTable.get(character);
            codeWordLength = word.getLength();
            characterWordMap.put(character, word);
            wordCharacterMap.put(word, character);
        }
    }

    @Override
    public Word decode(Word word) {
        List<Character> initialSigns = word.getSigns();
        List<Character> finalSigns = new ArrayList<Character>(initialSigns.size() / codeWordLength);
        List<Character> bufferSigns = new ArrayList<Character>(codeWordLength);
        for (Character initialSign : initialSigns) {
            bufferSigns.add(initialSign);
            if (bufferSigns.size() == codeWordLength) {
                finalSigns.add(wordCharacterMap.get(new Word(bufferSigns)));
                bufferSigns.clear();
            }
        }
        return new Word(finalSigns);
    }
}
