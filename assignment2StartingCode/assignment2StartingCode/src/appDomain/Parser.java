package appDomain;
 
import implementations.MyStack;
import java.io.*;
import java.util.regex.*;
 
public class Parser {
 
    // Matches:
    // <tag>
    // </tag>
    // <tag attr="value">
    // <tag/>
    private static final Pattern TAG_PATTERN =
            Pattern.compile("<(/?)([^>/\\s]+)[^>]*(/?)>");
 
    // Counts total parsing errors
    private static int errorCount = 0;
 
    /**
     * Prints one parsing error.
     */
    private static void reportError(int line, String tag) {
 
        System.out.println(
                "Error at line: "
                        + line
                        + " "
                        + tag
                        + " is not constructed correctly.");
 
        errorCount++;
    }
 
    public static void main(String[] args) throws Exception {
 
        if (args.length == 0) {
 
            System.out.println("Usage:");
            System.out.println("java -jar Parser.jar sample.xml");
            return;
        }
 
        File file = new File(args[0]);
 
        BufferedReader reader =
                new BufferedReader(new FileReader(file));
 
        // Store opening tags
        MyStack<XMLTag> stack = new MyStack<>();
 
        boolean rootFound = false;
 
        String line;
        int lineNumber = 0;
 
        while ((line = reader.readLine()) != null) {
 
            lineNumber++;
 
            line = line.trim();
 
            // Ignore XML declaration
            if (line.startsWith("<?xml"))
                continue;
 
            Matcher matcher = TAG_PATTERN.matcher(line);
 
            while (matcher.find()) {
 
                String slash = matcher.group(1);
 
                String tagName = matcher.group(2);
 
                String selfClosing = matcher.group(3);
 
                String fullTag = matcher.group();
 
                //----------------------------------------------------
                // Ignore self-closing tags
                //----------------------------------------------------
                if (selfClosing.equals("/")) {
                    continue;
                }
 
                //----------------------------------------------------
                // Closing tag
                //----------------------------------------------------
                if (slash.equals("/")) {
 
                    if (stack.isEmpty()) {
 
                        reportError(lineNumber, fullTag);
                        continue;
                    }
 
                    XMLTag top = stack.peek();
 
                    // Correct closing tag
                    if (top.getName().equals(tagName)) {
 
                        stack.pop();
                    }
                    else {
 
                        // Opening tag is incorrect
                        reportError(
                                top.getLineNumber(),
                                "<" + top.getName() + ">");
 
                        // Closing tag is incorrect
                        reportError(
                                lineNumber,
                                "</" + tagName + ">");
                    }
 
                    continue;
                }
 
                //----------------------------------------------------
                // Opening tag
                //----------------------------------------------------
 
                // Check for multiple root tags
                if (!rootFound) {
 
                    rootFound = true;
                }
                else if (stack.isEmpty()) {
 
                    reportError(lineNumber, fullTag);
                }
 
                stack.push(
                        new XMLTag(tagName, lineNumber));
            }
        }
 
        reader.close();
 
        //----------------------------------------------------
        // Remaining opening tags never got closed
        //----------------------------------------------------
 
        while (!stack.isEmpty()) {
 
            XMLTag tag = stack.pop();
 
            reportError(
                    tag.getLineNumber(),
                    "<" + tag.getName() + ">");
        }
 
        //----------------------------------------------------
        // Final result
        //----------------------------------------------------
 
        if (errorCount == 0) {
 
            System.out.println("XML document is constructed correctly.");
        }
 
    }
 
}
