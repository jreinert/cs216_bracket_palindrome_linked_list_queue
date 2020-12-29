/* Description: This program creates a queue using a doubly linked list, inserts a new student behind a user with a similar school or at the end of the queue of a 
 * matching school is not found, and removes a student from the front of the queue after they are registered. 
 * Author: Jeremy Reinert
 * Date: 4/8/19
 * Version: 1.0
 */
import java.util.*;
public class SQueue {
    
    Node front; // reference to front of queue
    Node rear; // reference to back of queue
    int size = 0; // size attribute to keep track of queue size for loop iterations every time an element is enqueued or dequeued
    public SQueue()
    {
        //initialize your front and rear
    	front = null;
    	rear = null;
    }
    
    public Node deQueue()
    {
        //implement the deQueue function, please keep in mind, it should return the deleted node
    	// Check if front is null and if so, return null
    	if(front == null) {
    		return null;
    	}
    	// Check if front is the same node as the rear and if so, set front and rear to null and decrease queue size
    	// Return temp variable holding the front nodes value
    	else if(front == rear) {
    		Node temp = front;
    		front = null;
    		rear = null;
    		size--;
    		return temp;
    	}
    	// Set temp variable equal to front and return to main method
    	// Move the front pointer to front.next
    	// Set temp.next to null
    	// Decrease queue size
    	else {
    		Node temp = front;
    		front = front.next;
    		temp.next = null;
    		size--;
    		return temp;	
    	}
    }
    
    public void enQueue(int s, int id)
    {
        // implement the enQueue function, based on the behaviour we described.
    	// Create new node t with parameters passed to enQueue()
    	Node t = new Node(s, id);
    	
    	// Check if front is not null and if so, set front and rear equal to t -- increment size by 1;
		if(front == null) {
			front = t;
			rear = t;
			size++;
		}
		// Check if front is equal to rear and if so, set rear.next equal to t
		// Set rear.prev equal to front
		// Set t.prev equal to rear
		// Set rear equal to t
		// Increment size by 1
		else if(front == rear) {
			rear.next = t;
			rear.prev = front;
			t.prev = rear;
			rear = t;
			size++;
		}
		else {
			// Create a Node temp and set equal to rear
			Node temp = rear;
			// Set int count equal to size for while loop
			int count = size;
			
			// While temp.school does not equal t.school and count is greater than or equal to 0, set temp equal to temp.prev and decrement count
			// This will stop when temp.school is equal to t.school if there are two entries with the same school, otherwise the loop will break when count is greater than or equal to 0
			// This helps find the insertion point for the new node
			while(temp.school != t.school && count >= 0) {
				temp = temp.prev;
				count--;
			}
			
			// If count is less than or equal to 0, call insertNode() method with t and rear as parameters
			if(count <= 0) {
				insertNode(t, rear);
			}
			// Call insertNode method with t and temp as parameters
			else {
				insertNode(t, temp);
			}
						
		}
    }

    // other utility functions go here
    // insertNode() method for inserting node into queue at designated spot
    public void insertNode(Node t, Node insertPoint) {
    	// Check if insertPoint is equal to rear and if so:
    	// 1) set rear.next to t
    	// 2) set t.prev to rear
    	// 3) set rear to t;
    	// 4) increment size
    	if(insertPoint == rear) {
    		rear.next = t;
    		t.prev = rear;
    		rear = t;
    		size++;
    	// ELSE:
    	// 1) set t.next to insertPoint.next
    	// 2) set t.prev to insertPoint
    	// 3) set insertPoint.next.prev t
    	// 4) set insertPoint.next to t
    	// 5) increment size
    	} else {
    		t.next = insertPoint.next;
    		t.prev = insertPoint;
    		insertPoint.next.prev = t;
    		insertPoint.next = t;
    		size++;
    	}
    }
    
    // Display method to print queue to console during testing
	public void display(){
		Node temp = front; // set temp to head
		for(int i = 0; i < size - 1; i++) {
			System.out.print(temp.school + " " + temp.id + " --> ");
			temp = temp.next; // set temp to the next node
		}
		System.out.println(" ");
		}


    // main method. Do NOT modify
    public static void main(String args[]) {
        
        SQueue s= new SQueue();
        
        Scanner input = new Scanner(System.in);
        int lines = input.nextInt();
        
        for (int i=0;i< lines;i++)
        {
            char operation_type = input.next().charAt(0);
            if (operation_type=='E'){
                int school = input.nextInt();
                int id= input.nextInt();
                 s.enQueue(school,id);
            }
            else{
                Node d = s.deQueue();
                if(d!=null)
                    System.out.println(d.school+" "+d.id);
            }
            
        } // end of input
        
        //s.display(); //display() method call to print out queue in testing
       
    } // end of main
    
    
} // end of class

class Node
{
    int school;
    int id;
    Node next;
    Node prev; // comment out if you just want to use a singly linked list
    public Node(int school, int id)
    {
        this.school=school;
        this.id=id;
        next=null;
        prev=null; // comment out if you just want to use a singly linked list
    }
}
