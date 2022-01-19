import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandLine1 {

    public static int Action() {
        System.out.print("Nhap yeu cau: ");
        Scanner sc  = new Scanner(System.in);
        String action = sc.nextLine();
        if (action.compareToIgnoreCase("LOOK UP") == 0) {
            return 0;
        }
        if (action.compareToIgnoreCase("INSERT") == 0) {
            return 1;
        }
        if (action.compareToIgnoreCase("DELETE") == 0) {
            return 2;
        }
        if (action.compareToIgnoreCase("FIX") == 0) {
            return 3;
        }
        if (action.compareToIgnoreCase("TRANSLATE TEXT") == 0) {
            return 4;
        }
        if (action.compareToIgnoreCase("EXIT") == 0) {
            return -1;
        }
        return -1;
    }
    public static void dictionaryBasic() throws FileNotFoundException {
        DictionaryManagement.insertFromCommandline();
        DictionaryCommandline.showAllWords();
    }
    public static void dictionarySearcher() {
        ArrayList<Word> list = new ArrayList<Word>();
        list = DictionaryManagement.dictionaryLookup();
        int n = list.size();
        if (n == 0) {
            System.out.println("không có từ cần tìm trong danh sách!");
        } else {
            for (int i=0; i<n; i++) {
                Word s = list.get(i);
                System.out.print("        " + s.getWord_target());
                for (int k=0; k< 24 - s.getWord_target().length(); k++) {
                    System.out.print(" ");
                }
                System.out.print(s.getWord_type().trim());
                for (int k=0; k< 24 - s.getWord_type().length(); k++) {
                    System.out.print(" ");
                }
                System.out.print(s.getSpell().trim());
                for (int k=0; k< 24 - s.getSpell().length(); k++) {
                    System.out.print(" ");
                }
                System.out.println(s.getWord_explain().trim());
            }
        }
    }

    public static void translate_text() throws IOException {
        System.out.print("Moi nhap van ban: ");
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        //System.out.println();
        //Translator.text_translate(text);
        String s = Translator.text_translate(text);
        System.out.println(s);
    }
    public static void dictionaryAdvanced() throws IOException {

        DictionaryManagement.insertFromCommandline();
        DictionaryCommandline.showAllWords();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Word> arr = Dictionary.getArrayWord();
        int l = 0;
        int h = arr.size() - 1;
        boolean conti = true;
        while (conti) {
            int n = Action();
            switch (n) {
                case 0:
                    dictionarySearcher();
                    break;
                case 1:
                    DictionaryManagement.dictionaryInsert(arr, l, h);
                    break;
                case 2:
                    DictionaryManagement.dictionaryDelete(arr, l, h);
                    break;
                case 3:
                    DictionaryManagement.dictionaryFix(arr, l, h);
                    break;
                case 4:
                    translate_text();
                    break;
                case -1:
                    return;
            }
            System.out.print("Ban co muon tiep tuc(Yes/No): ");
            String s = scanner.next();
            while (s.compareToIgnoreCase("yes") != 0 && s.compareToIgnoreCase("no") != 0) {
                System.out.println("Sai cau lenh!");
                System.out.print("Ban co muon tiep tuc(Yes/No): ");
                s = scanner.next();
            }
            if (s.compareToIgnoreCase("no") == 0) {
                conti = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandLine1.dictionaryAdvanced();
        DictionaryManagement.dictionaryExportToFile();
    }
}

