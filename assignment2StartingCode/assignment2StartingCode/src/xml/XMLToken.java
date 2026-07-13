package xml;

public class XMLToken {
    public enum Type {
        START_TAG, END_TAG, SELF_CLOSING_TAG, TEXT, PROCESSING_INSTRUCTION
    }

    public Type type;
    public String name;      // tag name
    public String text;      // for TEXT
    // attributes ignored for now per rules

    public XMLToken(Type type, String name, String text) {
        this.type = type;
        this.name = name;
        this.text = text;
    }
}
