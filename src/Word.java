public class Word {
    private String word_target;
    private String word_explain;
    private String word_type;
    private String spell;

    /**
     * getWord_target.
     */
    public String getWord_target() {
        return word_target;
    }

    /**
     * setWord_target.
     * @param word_target
     */
    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    /**
     * getWord_explain.
     */
    public String getWord_explain() {
        return word_explain;
    }

    /**
     * setWord_explain.
     * @param word_explain
     */
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_type() {
        return word_type;
    }

    public void setWord_type(String word_type) {
        this.word_type = word_type;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public Word(String word_target, String word_explain, String word_type, String spell) {
        this.word_target = word_target;
        this.word_explain = word_explain;
        this.word_type = word_type;
        this.spell = spell;
    }
}
