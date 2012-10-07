package ternovsky;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 06.10.12
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */
public class Alphabet {

    private String name;
    private Set<Character> signs;

    public Alphabet(String name, String string) {
        this.name = name;
        signs = new HashSet<Character>();
        for (String s : string.split(",")) {
            for (char c : s.toCharArray()) {
                signs.add(c);
            }
        }
    }

    public boolean containsSymbol(Character sign) {
        return signs.contains(sign);
    }

    public int size() {
        return signs.size();
    }

    @Override
    public int hashCode() {
        return signs.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Alphabet ");
        builder.append(name);
        builder.append(" = {");
        for (Character sign : signs) {
            builder.append(sign);
            builder.append(" ,");
        }
        builder.append("};\n");
        return builder.toString();
    }
}
