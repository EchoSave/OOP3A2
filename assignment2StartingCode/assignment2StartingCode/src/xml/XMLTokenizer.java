package xml;

public class XMLTokenizer {

    private final String input;
    private int pos = 0;

    public XMLTokenizer(String input) {
        this.input = input;
    }

    public boolean hasNext() {
        // TODO: implement end-of-input check
        return false;
    }

    public XMLToken nextToken() {
        // TODO: scan input, detect:
        // - processing instructions: <?xml ... ?>
        // - start tags: <tag>
        // - end tags: </tag>
        // - self-closing tags: <tag/>
        // - text between tags
        return null;
    }
}
