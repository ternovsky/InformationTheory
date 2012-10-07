package ternovsky.codes;

import ternovsky.Alphabet;
import ternovsky.Code;
import ternovsky.Word;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 07.10.12
 * Time: 1:23
 * To change this template use File | Settings | File Templates.
 */
public class RegularCode extends Code {

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
        char[] initialChars = word.getCharacters();
        char[] finalChars = new char[initialChars.length / (int) codeWordLength];
        char[] codeWord = new char[(int) codeWordLength];
        int cwi = 0;
        int fci = 0;
        for (char c : initialChars) {
            if (cwi >= codeWordLength) {
                finalChars[fci] = wordCharacterMap.get(new Word(codeWord));
                fci++;
                cwi = 0;
            }
            codeWord[cwi] = c;
            cwi++;
        }
        finalChars[fci] = wordCharacterMap.get(new Word(codeWord));
        return new Word(finalChars);
    }
}
