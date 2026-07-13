package xml;

import java.util.Stack;

public class XMLParser {

    public XMLNode parse(String XML) {
        XMLTokenizer tokenizer = new XMLTokenizer(XML);
        Stack<XMLNode> stack = new Stack<>();
        XMLNode root = null;

        while (tokenizer.hasNext()) {
            XMLToken token = tokenizer.nextToken();

            switch (token.type) {
                case PROCESSING_INSTRUCTION:
                    // ignore per rules
                    break;

                case START_TAG:
                    XMLNode node = new XMLNode(token.name);
                    if (stack.isEmpty()) {
                        // first element → root
                        if (root != null) {
                            throw new RuntimeException("Multiple root elements");
                        }
                        root = node;
                    } else {
                        stack.peek().children.add(node);
                    }
                    stack.push(node);
                    break;

                case SELF_CLOSING_TAG:
                    XMLNode self = new XMLNode(token.name);
                    if (stack.isEmpty()) {
                        if (root != null) {
                            throw new RuntimeException("Multiple root elements");
                        }
                        root = self;
                    } else {
                        stack.peek().children.add(self);
                    }
                    break;

                case END_TAG:
                    if (stack.isEmpty()) {
                        throw new RuntimeException("Closing tag without opening: " + token.name);
                    }
                    XMLNode top = stack.pop();
                    if (!top.name.equals(token.name)) {
                        throw new RuntimeException("Mismatched tags: expected </" + top.name +
                                                   "> but found </" + token.name + ">");
                    }
                    break;

                case TEXT:
                    if (!stack.isEmpty()) {
                        // append text to current node
                        XMLNode current = stack.peek();
                        current.text += token.text;
                    }
                    break;
            }
        }

        if (!stack.isEmpty()) {
            throw new RuntimeException("Unclosed tags remain");
        }
        if (root == null) {
            throw new RuntimeException("No root element found");
        }

        return root;
    }
}
