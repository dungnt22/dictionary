import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Dictionary implements Serializable {
    static ArrayList<Word> Word = new ArrayList<Word>();
    public static void addWord(Word s) {
        Word.add(s);
    }
    public static int sizeOfWord() {
        return Word.size();
    }
    public static Word getWord(int index) {
        return Word.get(index);
    }
    public static void deleteWord_(Word s) {
        Word.remove(s);
    }
    public static void sortWord() {
        Collections.sort(Word, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return (o1.getWord_target().compareTo(o2.getWord_target()));
            }
        });
    }
    public static ArrayList<Word> getArrayWord() {
        return Word;
    }
}

