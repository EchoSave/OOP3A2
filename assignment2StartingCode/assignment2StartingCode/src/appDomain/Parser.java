package appDomain;
 
import implementations.MyStack;
 
import java.io.BufferedReader;

import java.io.File;

import java.io.FileReader;

import java.util.regex.Matcher;

import java.util.regex.Pattern;
 
public class Parser {
 
    // Match every XML tag

    private static final Pattern TAG_PATTERN =

            Pattern.compile("<[^>]+>");
 
    private static int errorCount = 0;
 
    /**

     * Print one parsing error.

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

            System.out.println("java Parser <xml-file>");
 
            return;

        }
 
        File file = new File(args[0]);
 
        if (!file.exists()) {
 
            System.out.println("File not found.");
 
            return;

        }
 
        BufferedReader reader =

                new BufferedReader(new FileReader(file));
 
        MyStack<XMLTag> stack = new MyStack<>();
 
        boolean rootFound = false;
 
        String line;
 
        int lineNumber = 0;
 
        while ((line = reader.readLine()) != null) {
 
            lineNumber++;
 
            line = line.trim();
 
            Matcher matcher = TAG_PATTERN.matcher(line);
 
            while (matcher.find()) {
 
                String fullTag = matcher.group();
 
                //-----------------------------------------

                // Ignore XML declaration

                //-----------------------------------------

                if (fullTag.startsWith("<?")) {

                    continue;

                }
 
                //-----------------------------------------

                // Ignore comments

                //-----------------------------------------

                if (fullTag.startsWith("<!--")) {

                    continue;

                }
 
                //-----------------------------------------

                // Check tag type

                //-----------------------------------------

                boolean closingTag = fullTag.startsWith("</");
 
                boolean selfClosingTag =

                        fullTag.endsWith("/>");
 
                //-----------------------------------------

                // Extract tag name

                //-----------------------------------------

                String tagName;
 
                if (closingTag) {
 
                    tagName =

                            fullTag.substring(2,

                                    fullTag.length() - 1);
 
                } else {
 
                    String temp =

                            fullTag.substring(1);
 
                    if (selfClosingTag) {
 
                        temp =

                                temp.substring(

                                        0,

                                        temp.length() - 2);
 
                    } else {
 
                        temp =

                                temp.substring(

                                        0,

                                        temp.length() - 1);

                    }
 
                    int space = temp.indexOf(' ');
 
                    if (space != -1) {
 
                        tagName =

                                temp.substring(0, space);
 
                    } else {
 
                        tagName = temp;

                    }

                }
 
                //-----------------------------------------

                // Ignore self-closing tags

                //-----------------------------------------

                if (selfClosingTag) {

                    continue;

                }
 
                //-----------------------------------------

                // Closing tag

                //-----------------------------------------

                if (closingTag) {
 
                    if (stack.isEmpty()) {
 
                        reportError(lineNumber, fullTag);
 
                        continue;

                    }
 
                    XMLTag top = stack.peek();
 
                    if (top.getName().equals(tagName)) {
 
                        stack.pop();
 
                    } else {
 
                        reportError(

                                top.getLineNumber(),

                                "<" + top.getName() + ">");
 
                        reportError(

                                lineNumber,

                                fullTag);

                    }
 
                    continue;

                }
 
                //-----------------------------------------

                // Opening tag

                //-----------------------------------------

                if (!rootFound) {
 
                    rootFound = true;
 
                } else if (stack.isEmpty()) {
 
                    reportError(lineNumber, fullTag);

                }
 
                stack.push(

                        new XMLTag(

                                tagName,

                                lineNumber));

            }

        }
 
        reader.close();
 
        //-----------------------------------------

        // Remaining opening tags

        //-----------------------------------------

        while (!stack.isEmpty()) {
 
            XMLTag tag = stack.pop();
 
            reportError(

                    tag.getLineNumber(),

                    "<" + tag.getName() + ">");

        }
 
        //-----------------------------------------

        // Success

        //-----------------------------------------

        if (errorCount == 0) {
 
            System.out.println(

                    "XML document is constructed correctly.");

        }

    }

}
 
