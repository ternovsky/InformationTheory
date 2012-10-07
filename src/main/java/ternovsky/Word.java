package ternovsky;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 06.10.12
 * Time: 23:44
 * To change this template use File | Settings | File Templates.
 */
public class Word {

    private List<Character> signs;

    public Word(String string) {
        this(string.toCharArray());
    }

    public Word(List<Character> signs) {
        this.signs = new ArrayList<Character>(signs);
    }

    public Word(char[] signs) {
        this.signs = new ArrayList<Character>(signs.length);
        for (char c : signs) {
            this.signs.add(c);
        }
    }

    public List<Character> getSigns() {
        return signs;
    }

    public int getLength() {
        return signs.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        if (signs != null ? !signs.equals(word.signs) : word.signs != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return signs != null ? signs.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char c : signs) {
            builder.append(c);
        }
        return builder.toString();
    }
}
