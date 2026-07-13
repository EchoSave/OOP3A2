package xml;

public class XMLNode {
    public String name;
    public String text = "";
    public java.util.Map<String, String> attributes = new java.util.HashMap<>();
    public java.util.List<XMLNode> children = new java.util.ArrayList<>();

    public XMLNode(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "XmlNode{name=" + name + ", text=" + text + ", children=" + children.size() + "}";
    }
}
