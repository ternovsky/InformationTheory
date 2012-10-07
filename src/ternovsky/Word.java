package ternovsky;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 06.10.12
 * Time: 23:44
 * To change this template use File | Settings | File Templates.
 */
public class Word {
    private char[] word;

    public Word(String string) {
        this.word = string.toCharArray();
    }

    public Word(char[] word) {
        this.word = word;
    }

    public char[] getCharacters() {
        return word;
    }

    public int getLength() {
        return word.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        if (!Arrays.equals(word, word1.word)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return word != null ? Arrays.hashCode(word) : 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char c : word) {
            builder.append(c);
        }
        return builder.toString();
    }
}
