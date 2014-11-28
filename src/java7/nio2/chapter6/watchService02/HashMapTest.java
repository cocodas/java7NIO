package java7.nio2.chapter6.watchService02;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

public class HashMapTest {

	public static void main(String[] args) {
		//hashTable �⺻�Ҵ� ���� 11 hashMap �⺻�Ҵ���� 16
		//���� Threadȯ�濡�� hashTable�� ���� ��û���� �ӵ��� ������
		System.out.println(" ---- �׽�Ʈ ���� ---- ");
		
		HashMap hm = new HashMap();
		hm.put("��", "A");
		hm.put("��", "B");
		hm.put("��", "C");
		hm.put("��", "D");
		hm.put("��", "E");
		
		System.out.println(" ***** ���� ������ ��� 1 ***** ");
		
		Enumeration e = Collections.enumeration(hm.entrySet());
		//Enumeration : java.util ��Ű���� �ִ� CollectionŬ������ enumeration() �޼ҵ带 �̿��Ͽ� ������ ������ �ִ�. 
		
		while (e.hasMoreElements())
			System.out.println(e.nextElement());
		
		System.out.println(" ***** ���� ������ ��� 2 ***** ");
		
		Iterator it = hm.entrySet().iterator();
		while(it.hasNext())
			System.out.println(it.next());
		
		System.out.println(" ***** ���� ������ ��� 3 ***** ");
		
		for (Object s : hm.entrySet()) {
			System.out.println(s);
			
		}
	}

}
