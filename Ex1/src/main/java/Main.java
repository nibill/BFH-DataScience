import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Main 
{
	// define paths
	final static String inputFile = new File("src/main/resources/hamlet.txt").getAbsolutePath();
	final static String outputFile = new File("src/main/resources/hamletOutput.txt").getAbsolutePath();

	public static void main(String[] args) throws IOException
	{			
		String content = FileReader.readFile(inputFile, Charset.defaultCharset()).toLowerCase();
		File f = new File(outputFile);
		
		// check if file exists
		if(f.exists()) 
		{ 
			// empty the file
			PrintWriter pw = new PrintWriter(outputFile);
			pw.close();
		}
		else
		{
			f.createNewFile();
		}
		
		Writer output = new BufferedWriter(new FileWriter(outputFile, true));
		Map<String, Integer> valueMap = new HashMap<String, Integer>();
		
		String[] keys = content.split("[\\W]+");
	    String[] uniqueKeys;
	    int wordCount = 0;
	    int count = 1;
	        
	    uniqueKeys = getUniqueKeys(keys);
	        
	    // iterate through uniqueKeys
	    for(String key: uniqueKeys)
	    {
	        if(null == key)
	        {
	            break;
	        }           
	        for(String s : keys)
	        {
	        	// if the searched word appeared already, wordCount + 1
	            if(key.equals(s))
	            {
	            	wordCount++;
	            }               
	        }
	            
	        // write the word with its counter into a map
	        valueMap.put(key, wordCount);
	            
	        // reset wordCount for next word
	        wordCount = 0;
	    }
	        
	    // sort map descending by value
	    valueMap = MapSorter.SortByValues(valueMap);
	    
	    // iterate through map in order to write its values into the text file
	    for(Entry<String, Integer> entry : valueMap.entrySet())
	    {
	    	// get keys/values
	        String word = entry.getKey();
	        Integer countedWords = entry.getValue();
	        	
	        // output to the text file
	        output.append(count + " " + word + " " + countedWords + "\n");

	        // index + 1
	        count ++;
	    }
	        
	    // close writer stream
	    output.close();
	        
	    // console message to inform user the operation finished
	    System.out.println("Finished!");
	}
	
	/**
	 * Method to retrieve all unique words
	 * @param words every word appearing in the text
	 * @return array with every unique word appearing in the text
	 */
	private static String[] getUniqueKeys(String[] words)
	{
		// define array
		String[] uniqueKeys = new String[words.length];

		uniqueKeys[0] = words[0];
	    int uniqueKeyIndex = 1;
	    boolean keyAlreadyExists = false;

	    // iterate through the words array
	    for(int i=1; i<words.length ; i++)
	    {
	    	for(int j=0; j<=uniqueKeyIndex; j++)
	        {
	    		// check if searched word exists already
	            if(words[i].equals(uniqueKeys[j]))
	             {
	                 keyAlreadyExists = true;
	             }
	         }           

	    	// check if key exists already
	        if(!keyAlreadyExists)
	        {
	        	// set unique key to the searched word
	        	uniqueKeys[uniqueKeyIndex] = words[i];
	        	uniqueKeyIndex++;               
	       }
	       keyAlreadyExists = false;
	    }       
	    return uniqueKeys;
	 }
}
