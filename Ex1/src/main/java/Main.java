import java.io.IOException;
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
	final static String inputFile = "C:\\Users\\buche\\Documents\\Git\\BFH-DataScience\\Ex1\\src\\main\\resources\\hamlet.txt";
	final static String outputFile = "C:\\Users\\buche\\Documents\\Git\\BFH-DataScience\\Ex1\\src\\main\\resources\\hamletOutput.txt";
	
	public static void main(String[] args) throws IOException
	{	
		String content = FileReader.readFile(inputFile, Charset.defaultCharset()).toLowerCase();
		
		File f = new File(outputFile);
		
		// check if file exists
		if(!f.exists()) { 
		    f.createNewFile();
		}
		
		Writer output = new BufferedWriter(new FileWriter(outputFile, true));
		Map<String, Integer> valueMap = new HashMap<String, Integer>();
		
	        String[] keys = content.split("[\\W]+");
	        String[] uniqueKeys;
	        int wordCount = 0;
	        int count = 1;
	        
	        uniqueKeys = getUniqueKeys(keys);

	        for(String key: uniqueKeys)
	        {
	            if(null == key)
	            {
	                break;
	            }           
	            for(String s : keys)
	            {
	                if(key.equals(s))
	                {
	                	wordCount++;
	                }               
	            }
	            
	            valueMap.put(key, wordCount);
	            wordCount=0;
	        }
	        
	        valueMap = MapSorter.SortByValues(valueMap);
	        
	        for(Entry<String, Integer> entry : valueMap.entrySet())
	        {
	        	String word = entry.getKey();
	        	Integer countedWords = entry.getValue();
	        	
	        	output.append(count + " " + word + " " + countedWords + "\n");
	        	count ++;
	        }
	        
	        output.close();
	        System.out.println("Finished!");
	}
	
	private static String[] getUniqueKeys(String[] keys)
	{
		String[] uniqueKeys = new String[keys.length];

		uniqueKeys[0] = keys[0];
	    int uniqueKeyIndex = 1;
	    boolean keyAlreadyExists = false;

	    for(int i=1; i<keys.length ; i++)
	    {
	    	for(int j=0; j<=uniqueKeyIndex; j++)
	        {
	            if(keys[i].equals(uniqueKeys[j]))
	             {
	                 keyAlreadyExists = true;
	             }
	         }           

	         if(!keyAlreadyExists)
	         {
	             uniqueKeys[uniqueKeyIndex] = keys[i];
	             uniqueKeyIndex++;               
	         }
	         keyAlreadyExists = false;
	     }       
	     return uniqueKeys;
	 }
}
