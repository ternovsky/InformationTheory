package ternovsky.code.prefix;

import ternovsky.Alphabet;
import ternovsky.ProbableSource;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 10.10.12
 * Time: 23:37
 * To change this template use File | Settings | File Templates.
 */
public class HuffmanCode extends PrefixCode {

    public HuffmanCode(Alphabet initialAlphabet, Alphabet finalAlphabet, ProbableSource probableSource) {
        super(initialAlphabet, finalAlphabet, probableSource);
    }

    @Override
    protected void buildCodeTable() {
        List<Node> nodes = new ArrayList<Node>(signProbableMap.size());
        for (Character sign : signProbableMap.keySet()) {
            nodes.add(new Node(sign, signProbableMap.get(sign)));
        }
        Collections.reverse(nodes);
        while (nodes.iterator().hasNext()) {
            ListIterator<Node> iterator = nodes.listIterator();
            Node firstNode = iterator.next();
            iterator.remove();
            if (iterator.hasNext()) {
                Node secondNode = iterator.next();
                secondNode.getSigns().addAll(firstNode.getSigns());
                secondNode.setProbable(secondNode.getProbable() + firstNode.getProbable());
                addSignsToSignCodeWordMap(firstNode.getSigns(), '0');
                addSignsToSignCodeWordMap(secondNode.getSigns(), '1');
            }
            Collections.sort(nodes);
        }
        for (List<Character> signs : signCodeWordMap.values()) {
            Collections.sort(signs);
        }
    }

    private void addSignsToSignCodeWordMap(Collection<Character> signs, Character addedSign) {
        for (Character sign : signs) {
            signCodeWordMap.get(sign).add(addedSign);
        }
    }

    private class Node implements Comparable<Node> {
        private List<Character> signs;
        private Float probable;

        private Node(Character signs, Float probable) {
            this.signs = new ArrayList<Character>();
            this.signs.add(signs);
            this.probable = probable;
        }

        public List<Character> getSigns() {
            return signs;
        }

        public Float getProbable() {
            return probable;
        }

        public void setSigns(List<Character> signs) {
            this.signs = signs;
        }

        public void setProbable(Float probable) {
            this.probable = probable;
        }

        @Override
        public int compareTo(Node o) {
            if (probable.equals(o.probable)) {
                return 0;
            } else {
                return probable - o.probable > 0 ? 1 : -1;
            }
        }
    }
}
