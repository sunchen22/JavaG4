import java.util.Map;
//import java.util.Map.Entry;

public class MapEntry<K, V> implements Map.Entry<K, V> {
	private K key;
	private V value;

	public MapEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		V old = this.value;
		this.value = value;
		return old;
	}

	public static void main(String[] args) {

		Map.Entry<String, Object> entry = new MapEntry<String, Object>("myKey", "myValue");
		System.out.println(entry.getKey());
		System.out.println(entry.getValue());

	}

}