import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapSorter
{	
	/**
	 * sorts the given map by values descending
	 * @param map map containing the key/value pair
	 * @return sorted map
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V>SortByValues( Map<K, V> map )
	{
	    List<Map.Entry<K, V>> list =
	            new LinkedList<Map.Entry<K, V>>( map.entrySet() );
	    
	    // sort by values
	    Collections.sort( list, new Comparator<Map.Entry<K, V>>()
	    {
	    	/**
	    	 * method to compare two value pairs
	    	 */
	        public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
	        {	
	        	//compare by values
	            return (o2.getValue()).compareTo( o1.getValue() );
	        }
	    } );

	    Map<K, V> result = new LinkedHashMap<K, V>();
	    
	    // iterate through map
	    for (Map.Entry<K, V> entry : list)
	    {
	        result.put( entry.getKey(), entry.getValue() );
	    }
	    
	    return result;
	}
}
