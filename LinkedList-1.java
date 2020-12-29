/* Description: This program creates a linked list, adds values to the list, removes values from the list, and searches for values in the list
 * Author: Jeremy Reinert
 * Date: 4/8/19
 * Version: 1.0
 */

import java.util.Scanner;

public class LinkedList {
	//Vars
	Node head; // Point to the start of the linked list
	Node tail; // Point to the end of the linked list
	int size; // Size of list
	
	// Constructor
	public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public void addFirst(int value) {
		// Create a node
		Node t = new Node(value);
		
		// Set head if head is null and tail
		if(head == null) {
			head = t;
			tail = t;
			size++;
		}
		
		// Set head if head is not null
		else {
			t.next = head; // Set next to head so that it points to the next element in the list
			head = t; // Set head to t so that it points to the first element in the list
			size++;
		}
	}
	
	public void addLast(int value) {
		// Set tail if head is null
		if(head == null) {
			addFirst(value);
		}
		
		// Set tail if tail is not null
		else {
			Node t = new Node(value); // Create node
			tail.next = t; // Set tail.next (prior tail point to) to t 
			tail = t; // Set tail to t
			size++;
		}
	}
	
	public void insert(int value, int index) {
		
		// If index equals 0, call addFirst() method to handle
		if(index == 0) {
			addFirst(value);
		}
		
		// If index equals the last index, call addLast() method to handle
		else if (index == size - 1) {
			addLast(value);
		} 
		else {
			Node temp = new Node(value); // Create new node
			Node t = head; // Set t equal to the head
			
			// Search for Node at one index prior to insertion index
			for(int i = 0; i <= index - 1; i++) {
				t = t.next;
			}
			
			temp.next = t.next;
			t.next = temp;
			size--;
		}
	}
	
	public void remove(int index) {
		
		// If index equals 0, call removeFirst() method to handle
		if(index == 0) {
			removeFirst();
		}
		
		// If index equals last index, call removeLast() method to handle
		else if(index == size - 1) {
			removeLast();
		}
		else {
			Node temp; // Create Node temp
			Node t = head; // Create Node t and assign head's value
			
			// Iterate through LinkedList until at index one less than index indicated for removal
			for(int i = 0; i < index - 1; i++) {
				t = t.next;
			}
			
			temp = t.next; // Set temp to t.next
			t.next = temp.next; // Point to 1 index after temp
			temp.next = null; // Set temp's .next to null
			size--;
		}
		
	}
	
	public void removeFirst() {
		
		// Nothing to delete from an empty list
		if(head == null)
			return;
		else if(size == 1) {
			head = null;
			tail = null;
		}
		else {
			Node temp = head; // Set temp node to the head
			head = head.next; // Set head to the next Node
			temp.next = null; // Set next value to null
			size--;
		}
	}
	
	public void removeLast() {
		Node temp = head;
		
		// Loop through to find node we want to set to tail
		for(int i = 0; i < size-2; i++) {
			temp = temp.next;
		}
		
		tail = temp; // Set tail to temp
		temp.next = null; // Set temp to null
		size--;
	}
	
	public void display(){
		
		Node temp = head; // set temp to head
		
		for(int i = 0; i < size; i++) {
			System.out.print(temp.data + " --> ");
			temp = temp.next; // set temp to the next node
		}
		
		System.out.println(" ");
	}

	public int search(int value) {
		
		Node temp = head; // Create Node temp and set reference to head
		int index = 0; // Create index variable and set to 0
		
		for(int i = 0; i < size; i++) {
			// Check if temp.data is equal to value being searched for and if so, return the index		
			if(temp.data == value) {
				return index;
			}
			
			temp = temp.next; // Set temp to temp.next (iterating through linked list)
			index++; //increment index
		}
		
		return -1; // return -1 if value not found in the linked list
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		String response;
		int search;
		
		LinkedList l = new LinkedList();
		l.addFirst(20);
		l.addLast(5);
		l.addLast(10);
		l.addLast(15);
		l.addLast(100);
		
		l.display();
		
		l.addFirst(111);
		l.display();
		
		l.removeFirst();
		l.display();
		
		l.removeLast();
		l.display();
		
		l.insert(200, 3);
		l.display();
		
		l.remove(1);
		l.display();
		
		System.out.println("Would you like to search for number in the linked list?  Y for Yes and N for No");
		response = input.next();
		
		while(response.charAt(0) != 'N') {
			System.out.println("Enter a number to search the list for:");
			search = input.nextInt();
			
			if(l.search(search) >= 0) {
				System.out.println("The number " + search + " was found at index " + l.search(search));
			}
			else {
				System.out.println("The number " + search + " was not found in the linked list");
			}
			
			System.out.println("Would you like to search for another number in the linked list? Y for Yes and N for No");
			response = input.next();
		}
		
		input.close();
	}

}

class Node {
	// Vars
	int data;
	Node next;
	
	// Constructor
	public Node(int data) {
		this.data = data;
		next = null; // Because its a single node
	}
}
