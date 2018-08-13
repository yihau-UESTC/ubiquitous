package SwordToOffer;

import java.util.LinkedList;
import java.util.Queue;

public class Solution54 {

    private int[] map = new int[256];
    private Queue<Character> queue = new LinkedList<>();

    //Insert one char from stringstream
    public void Insert(char ch) {
        if (map[ch] != 0) {
            queue.remove(ch);
            map[ch]++;
        } else {
            map[ch]++;
            queue.add(ch);
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        Character peek = queue.peek();
        return peek == null ? '#' : peek;
    }
}
