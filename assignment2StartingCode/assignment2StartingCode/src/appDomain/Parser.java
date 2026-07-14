package appDomain;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Parser {

	private static final Pattern TAG_PATTERN =
	        Pattern.compile("<(/?)([^>/\\s]+)[^>]*(/?)>");
	
	public static void main(String[] args) throws Exception{
		
		if(args.length == 0 ) {
			System.out.println("For parsing XML files");
			return;
		}
		
		File file = new File (args[0]);
		BufferedReader reader = new BufferedReader (new FileReader(file));
		
		Stack <String> stack = new Stack<>();
		
		boolean rootFound = false;
		String line;
		int lineNum = 0 ;
		
		while ((line = reader.readLine()) != null) {
			lineNum++;
			
			if(line.trim().startsWith("<?xml")) continue;
			
			Matcher match = TAG_PATTERN.matcher(line);
			
			while (match.find()) {
                String isClosing = match.group(1);
                String tagName = match.group(2);
                String isSelfClosing = match.group(3);
                
                if (isSelfClosing.equals("/")) {
                	continue;
                }
                
                if (isClosing.equals("/")) {
                	if(stack.isEmpty()) {
                		System.out.println("Line " + lineNum +
                                ": Closing tag </" + tagName +
                                "> has no matching opening tag.");
                            continue;
                	}
                	
                	 String openTag = stack.pop();
                     if (!openTag.equals(tagName)) {
                         System.out.println("Line " + lineNum +
                             ": Tag mismatch. Expected </" +
                             openTag + "> but found </" +
                             tagName + ">.");
                     }
                     continue;
                }
                
                if (!rootFound) {
                    rootFound = true;
                } else if (stack.isEmpty()) {
                    System.out.println("Line " + lineNum +
                        ": Multiple root elements detected.");
                }
                
                stack.push(tagName);
			}
			
            if (line.contains("<") && !match.find()) {
                System.out.println("Line " + lineNum +
                    ": Malformed tag syntax.");
            }
        }

        reader.close();

        while (!stack.isEmpty()) {
            System.out.println("Unclosed tag at end: <" + stack.pop() + ">");
        }
    }
}
