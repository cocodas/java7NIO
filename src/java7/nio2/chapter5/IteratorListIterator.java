package java7.nio2.chapter5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class IteratorListIterator {

	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<>();
		al.add("A");
		al.add("B");
		al.add("C");
		al.add("D");
		al.add("E");
		
		System.out.print("ArrayList(itr) : ");
		
		Iterator<String> itr = al.iterator();
		while (itr.hasNext()) {
			String s = itr.next();
			System.out.print(s + " ");
			
		}
		
		System.out.println();
		System.out.println("=====================");
		
		System.out.print("ListIterator(forward) : ");
		
		ListIterator<String> litr = al.listIterator();
		while (litr.hasNext()) {
			String s = litr.next();
			System.out.print(s + " ");
			
		}
		
		System.out.println();
		System.out.println("=====================");
		
		System.out.print("ListIterator(backwards) : ");
		
		while (litr.hasPrevious()) {
			String s = litr.previous();
			System.out.print(s + " ");
		}
		
	}

}
