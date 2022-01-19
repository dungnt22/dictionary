public class DictionaryCommandline {
    public static void showAllWords() {
        int n = Dictionary.sizeOfWord();
        Dictionary.sortWord();
        System.out.println("No             |English               |Word_Type             |Spell                 |Vietnamese");
        for (int i=0; i<n; i++) {
            Word s = Dictionary.getWord(i);
            System.out.print(i+1);
            String ss = Integer.toString(i+1);
            for (int k=0; k<15 -ss.length(); k++) {
                System.out.print(" ");
            }
            System.out.print(s.getWord_target().trim());
            for (int j=1; j< 24 - s.getWord_target().length(); j++) {
                System.out.print(" ");
            }
            System.out.print(s.getWord_type().trim());
            for (int j=1; j< 24 - s.getWord_type().length(); j++) {
                System.out.print(" ");
            }
            System.out.print(s.getSpell().trim());
            for (int j=1; j< 24 - s.getSpell().length(); j++) {
                System.out.print(" ");
            }
            System.out.println(s.getWord_explain().trim());
            //System.out.println();
        }
    }
}
