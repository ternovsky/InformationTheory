package ternovsky;

import ternovsky.code.RegularCode;
import ternovsky.code.prefix.FanoCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 06.10.12
 * Time: 23:45
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        Alphabet latinAlphabet = new Alphabet("Text", "a,b,c,d,e,f");
        Alphabet binaryAlphabet = new Alphabet("Binary", "0,1");

        Map<Character, Word> codeTable = new HashMap<Character, Word>();
        codeTable.put('a', new Word("00"));
        codeTable.put('b', new Word("01"));
        codeTable.put('c', new Word("10"));

        Word latinAlphabetWord = new Word("abcbaaca");

        Code regularCode = new RegularCode(latinAlphabet, binaryAlphabet, codeTable);
        Word binaryAlphabetWord = regularCode.encode(latinAlphabetWord);
        System.out.println(latinAlphabetWord + " -> " + binaryAlphabetWord);
        System.out.println(binaryAlphabetWord + " -> " + regularCode.decode(binaryAlphabetWord));

        Map<Character, Float> pairs = new HashMap<Character, Float>();
        pairs.put('f', 1f);
        pairs.put('e', 2f);
        pairs.put('d', 3f);
        pairs.put('c', 5f);
        pairs.put('b', 8f);
        pairs.put('a', 10f);

        ProbableSource probableSource = new ProbableSource(pairs);

        Code fanoCode = new FanoCode(latinAlphabet, binaryAlphabet, probableSource);
        binaryAlphabetWord = fanoCode.encode(new Word("abbcdefe"));
        System.out.println(new Word("abbcdefe") + " -> " + binaryAlphabetWord);
        System.out.println(binaryAlphabetWord + " -> " + fanoCode.decode(binaryAlphabetWord));
    }
}
