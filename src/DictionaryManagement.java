import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromCommandline() throws FileNotFoundException {
        File file = new File("C:\\Users\\ASUS\\Downloads\\vocab.txt");
        if (!file.exists()) {
            file.mkdir();
        }
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] arr = line.split("\t");
            if (arr.length >= 4) {
                String word_target = arr[0];
                String word_explain = arr[3];
                String word_type = arr[1];
                String spell = arr[2];

                Word s = new Word(word_target, word_explain, word_type, spell);

                Dictionary.addWord(s);
            }
        }
    }

    public static int lookUp(String s, ArrayList<Word> list, int l, int h) {
        if (l < h) {
            int mid = l + (h - l) / 2;
            if (list.get(mid).getWord_target().compareToIgnoreCase(s) == 0) {
                return mid;
            }
            if (list.get(mid).getWord_target().compareToIgnoreCase(s) < 0) {
                //return lookUp(s, list, l, mid - 1);
                return lookUp(s, list, mid + 1, h);
            }
            //return lookUp(s, list, mid + 1, h);
            if (list.get(mid).getWord_target().compareToIgnoreCase(s) > 0) {
                return lookUp(s, list, l, mid - 1);
            }
        }
        return -1;
    }

    public static ArrayList<Word> dictionaryLookup() {

        System.out.print("Moi nhap tu can tra: ");
        ArrayList<Word> arr = new ArrayList<Word>();
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int n = Dictionary.sizeOfWord();
        for (int i = 0; i < n; i++) {
            if (Dictionary.getWord(i).getWord_target().startsWith(s)) {
                arr.add(Dictionary.getWord(i));
            }
        }
        return arr;
    }
    public static void dictionaryInsert(ArrayList<Word> arr, int l, int h) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        Scanner scan = new Scanner(System.in);
        System.out.print("Moi nhap tu can them: ");
        String word_tar = scan.nextLine();
        int index = lookUp(word_tar, arr, l, h);
        /*int n = Dictionary.sizeOfWord();
        boolean check = true;
        for (int i=0; i<n; i++) {
            if (Dictionary.getWord(i).getWord_target().compareToIgnoreCase(word_tar) == 0) {
                check = false;
                break;
            }
        }*/
        if (index != -1) {
            System.out.println("Tu can them da co trong tu dien!");
        } else {
            String word_ex = null;
            String word_type = null;
            String spell = null;
            try {
                System.out.print("Moi nhap nghia cua tu: ");
                word_ex = scan.nextLine();
                System.out.print("Moi nhap tu loai cua tu: ");
                word_type = scan.nextLine();
                System.out.print("Moi nhap phien am cua tu: ");
                spell = scan.nextLine();
                File file = new File("C:\\Users\\ASUS\\Downloads\\vocab.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                fw = new FileWriter(file.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);
                bw.write(word_tar + "\t" + word_type + "\t" + spell + "\t" + word_ex + "\r\n");
                System.out.println("Done!");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                    if (fw != null) {
                        fw.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            Word s = new Word(word_tar, word_ex, word_type, spell);
            Dictionary.addWord(s);
            Dictionary.sortWord();
        }
    }

    public static void dictionaryDelete(ArrayList<Word> arr, int l, int h) throws IOException {
        System.out.print("Moi nhap tu can xoa: ");
        Scanner sc = new Scanner(System.in);
        String delete = sc.nextLine();
        int index = lookUp(delete, arr, l, h);
        System.out.println(index);
        Word s = null;
        if (index == -1) {
            System.out.println("Tu can xoa khong co trong tu dien!");
        } else {
            s = Dictionary.getWord(index);
            Dictionary.deleteWord_(s);
            File file = new File("C:\\Users\\ASUS\\Downloads\\vocab.txt");
            OutputStream ops = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(ops);
            for (int i=0; i<Dictionary.sizeOfWord(); i++) {
                outputStreamWriter.write(Dictionary.getWord(i).getWord_target() + "\t"
                                            + Dictionary.getWord(i).getWord_type() + "\t"
                                            + Dictionary.getWord(i).getSpell() + "\t"
                                            + Dictionary.getWord(i).getWord_explain());
                outputStreamWriter.write("\n");
            }
            System.out.println("Done!");
            //bắt chương trình chờ ghi dữ liệu xong mới kết thúc chương trình
            outputStreamWriter.flush();
        }
    }

    public static void dictionaryExportToFile() throws IOException {
        File file = new File("C:\\Users\\ASUS\\Downloads\\outputFile.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        for (int i=0; i<Dictionary.sizeOfWord(); i++) {
            outputStreamWriter.write(Dictionary.getWord(i).getWord_target() + "\t"
                                        + Dictionary.getWord(i).getWord_type() + "\t"
                                        + Dictionary.getWord(i).getSpell() + "\t"
                                        + Dictionary.getWord(i).getWord_explain());
            outputStreamWriter.write("\n");
        }
        System.out.println("Completed!");
        outputStreamWriter.flush();
    }

    public static void dictionaryFix(ArrayList<Word> arr, int l, int h) throws IOException {
        System.out.print("Moi nhap tu can sua: ");
        Scanner sc = new Scanner(System.in);
        String fix = sc.nextLine();
        Word s = null;
        int index = lookUp(fix, arr, l, h);
        if (index == -1) {
            System.out.println("Từ cần sửa không có trong từ điển !");
        } else {
            s = Dictionary.getWord(index);
            Dictionary.deleteWord_(s);
            System.out.print("Moi nhap tu loai moi: ");
            String word_type = sc.nextLine();
            System.out.print("Moi nhap phien am moi: ");
            String spell = sc.nextLine();
            System.out.print("Moi nhap nghia moi: ");
            String word_ex = sc.nextLine();
            Word ss = new Word(fix, word_ex, word_type, spell);
            Dictionary.addWord(ss);
            Dictionary.sortWord();
            File file = new File("C:\\Users\\ASUS\\Downloads\\vocab.txt");
            OutputStream ops = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(ops);
            for (int i = 0; i < Dictionary.sizeOfWord(); i++) {
                outputStreamWriter.write(Dictionary.getWord(i).getWord_target() + "\t"
                                            + Dictionary.getWord(i).getWord_type() + "\t"
                                            + Dictionary.getWord(i).getSpell() + "\t"
                                            + Dictionary.getWord(i).getWord_explain());
                outputStreamWriter.write("\n");
            }
            System.out.println("Done!");
            outputStreamWriter.flush();
        }
    }
}
