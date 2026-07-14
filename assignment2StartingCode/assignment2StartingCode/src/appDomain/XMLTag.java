package appDomain;
 
/**
 * Represents an opening XML tag.
 * Stores both the tag name and the line where it appears.
 */
public class XMLTag {
 
    private String name;
    private int lineNumber;
 
    public XMLTag(String name, int lineNumber) {
        this.name = name;
        this.lineNumber = lineNumber;
    }
 
    public String getName() {
        return name;
    }
 
    public int getLineNumber() {
        return lineNumber;
    }
}