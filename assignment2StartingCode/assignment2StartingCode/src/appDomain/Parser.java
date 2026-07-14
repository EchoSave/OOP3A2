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
	}

}
