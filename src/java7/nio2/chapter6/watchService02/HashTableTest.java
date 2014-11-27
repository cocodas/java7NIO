package java7.nio2.chapter6.watchService02;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class HashTableTest {
	public static void main(String[] args) {
		System.out.println("---- 테스트 시작 ----");
		Hashtable ht = new Hashtable();
		ht.put(8, "A");
		ht.put(223, "B");
		ht.put(3, "C");
		ht.put(93, "D");
		ht.put(5, "E");
		
		Enumeration e = ht.keys();
		while (e.hasMoreElements())
			System.out.println(e.nextElement());
		
		System.out.println(" ******************** ");
		
		e = ht.elements();
		while(e.hasMoreElements())
			System.out.println(e.nextElement());
		
		System.out.println(" ******************** ");
		
		System.out.println("ht.entrySet() : " + ht.entrySet());
		System.out.println("ht.keySet() : " + ht.keySet());
		System.out.println("ht.values() : " + ht.values());
		System.out.println("ht : " + ht);
		
		System.out.println(" ******************** ");
		
		String s = (String)ht.put(3, "OK");
		System.out.println("s : " + s);
		System.out.println("ht.put(3, \"OK\") : " + ht);
		System.out.println("ht.contains(\"OK\") : " + ht.contains("OK"));
		System.out.println("ht.containsKey(0) : " + ht.containsKey(0));
		
		System.out.println(" ******************** ");
		
		Hashtable ht2 = new Hashtable();
		ht2.putAll(ht);
		System.out.println("ht2.putAll(ht) : " + ht2);
		System.out.println("ht2.get(3) : " + ht2.get(3));
		ht2.remove(3);
		System.out.println("ht2.remove(3) : " + ht2);
		
		System.out.println(" ******************** ");
		System.out.println("ht : " + ht);
		Set ss = ht.keySet();
		System.out.println("Set ss : " + ss);
		Iterator it = ss.iterator();
		while (it.hasNext()) {
			Object key = it.next();
			Object value = ht.get(key);
			System.out.println(key + " = " + value );
		}
		
		System.out.println(" ******************** ");
		
		ss = ht.entrySet();
		it = ss.iterator();
		
		while (it.hasNext()) {
			Object kv = it.next();
			System.out.println(kv);			
		}
		
		
	}

}
