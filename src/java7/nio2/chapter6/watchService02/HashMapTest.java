package java7.nio2.chapter6.watchService02;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

public class HashMapTest {

	public static void main(String[] args) {
		//hashTable 기본할당 공간 11 hashMap 기본할당공간 16
		//단일 Thread환경에서 hashTable에 비해 엄청나게 속도가 빠르다
		System.out.println(" ---- 테스트 시작 ---- ");
		
		HashMap hm = new HashMap();
		hm.put("가", "A");
		hm.put("나", "B");
		hm.put("다", "C");
		hm.put("라", "D");
		hm.put("마", "E");
		
		System.out.println(" ***** 루프 돌리는 방법 1 ***** ");
		
		Enumeration e = Collections.enumeration(hm.entrySet());
		//Enumeration : java.util 패키지에 있는 Collection클래스의 enumeration() 메소드를 이용하여 루프를 돌릴수 있다. 
		
		while (e.hasMoreElements())
			System.out.println(e.nextElement());
		
		System.out.println(" ***** 루프 돌리는 방법 2 ***** ");
		
		Iterator it = hm.entrySet().iterator();
		while(it.hasNext())
			System.out.println(it.next());
		
		System.out.println(" ***** 루프 돌리는 방법 3 ***** ");
		
		for (Object s : hm.entrySet()) {
			System.out.println(s);
			
		}
	}

}
