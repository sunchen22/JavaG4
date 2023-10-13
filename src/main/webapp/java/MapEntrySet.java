import java.util.*;
//import java.util.Map.Entry;

public class MapEntrySet {

	public static String toXml(Map<String, String> myMap) {

		StringBuilder xml = new StringBuilder();

		xml.append("<xml>");

		for (Map.Entry<String, String> myEntry : myMap.entrySet()) {

			String myKey = myEntry.getKey();
			String myValue = myEntry.getValue();

			if (myValue.trim().length() == 0)
				continue;

			xml.append("<").append(myKey).append(">");
			xml.append(myValue);
			xml.append("</").append(myKey).append(">");
		}

		xml.append("</xml>");

		return xml.toString();
	}

	public static void main(String args[]) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("A", "1");
		map.put("B", "2");
		map.put("C", "3");
        
		String xml = MapEntrySet.toXml(map);
		System.out.println("xml = "+xml);

	}
}