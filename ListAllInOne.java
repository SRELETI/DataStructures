import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class ListAllInOne {
	
	// Head node
	private ListInt head;
	
	// Constructor
	public ListAllInOne() {
		head = null;
	}
	
	// Adds node at the end of the list
	public void add(int val) {
		ListInt newNode = new ListInt(val);
		if(head==null) {
			head = newNode;
			return;
		}
		else {
			ListInt temp = head;
			while(temp.next!=null) {
				temp= temp.next;
			}
			temp.next = newNode;
		}
	}
	
	// Adds node at the front of the list
	public void addFront(int val) {
		ListInt newNode = new ListInt(val);
		if(head==null) {
			head = newNode;
			return;
		}
		else {
			newNode.next = head;
			head = newNode;
		}
	}
	
	// Adds node after a specific node in the list
	public void addAfter(int val, int after) {
		ListInt newNode = new ListInt(val);
		if(head==null) 
			return;
		else {
			ListInt temp = head;
			while(temp!=null && temp.data!=after) {
				temp = temp.next;
			}
			if(temp!=null) {
				ListInt pres_temp = temp.next;
				temp.next = newNode;
				newNode.next = pres_temp;
			}
		}
	}
	// Deletes the node in the list
	public boolean delete(int val) {
		if(head==null ) {
			return false;
		}
		else if(head.data==val){
			head = head.next;
			return true;
			}
		else {
			ListInt temp = head;
			while(temp.next!=null && temp.next.data!=val) {
				temp=temp.next;
			}
			if(temp.next!=null) {
				temp.next = temp.next.next;
				return true;
			}
			return false;
		}
	}
	
	
	// Displays the list
	public void display() {
		ListInt temp = head;
		while(temp!=null) {
			temp.display();
			temp = temp.next;
		}
		System.out.println();
	}
	
	// Returns the length of the list 
	public int length_iterative() {
		int length = 0;
		ListInt temp = head;
		while(temp!=null) {
			length++;
			temp = temp.next;
		}
		return length;
	}
	
	//Returns the length of the list - Recursive function 
	public int length_recursive(ListInt temp) {
		if(temp==null) {
			return 0;
		}
		return 1+length_recursive(temp.next);
	}
	
	public int getNthNode(int n) {
		ListInt temp = head;
		int count=1;
		while(temp!=null && count<n) {
			count++;
			temp = temp.next;
		}
		if(count==n) {
			return temp.data;
		}
		return -1;
	}
	
	//Get the middle node in the list
	public int getMiddle() {
		if(head==null) {
			return -1;
		}
		ListInt slow_ptr = head;
		ListInt fast_ptr = head;
		while(fast_ptr!=null && fast_ptr.next!=null) {
			fast_ptr = fast_ptr.next.next;
			slow_ptr = slow_ptr.next;
		}
		return slow_ptr.data;
	}
	// Get the middle element in the list
	public ListInt getMiddle(ListInt node) {
		if(node==null) {
			return null;
		}
		ListInt slow_ptr = node;
		ListInt fast_ptr = slow_ptr.next;
		while(fast_ptr!=null && fast_ptr.next!=null) {
			fast_ptr = fast_ptr.next.next;
			slow_ptr = slow_ptr.next;
		}
		return slow_ptr;
	}
	
	//Get the Nth node from the end of the list
	public int getNthNode_last(int n) {
		if(head==null || n<=0) {
			return -1;
		}
		int count=1;
		ListInt temp = head;
		while(temp!=null && count<n) {
			temp = temp.next;
			count++;
		}
		if(temp==null) {
			return -1;
		}
		ListInt nthNode= head;
		while(temp.next!=null) {
			nthNode = nthNode.next;
			temp = temp.next;
		}
		return nthNode.data;
	}
	
	// Reverses the list
	public void reverse() {
		if(head==null) {
			return;
		}
		ListInt next = null;
		ListInt cur = head;
		ListInt prev = null;
		while(cur!=null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		head = prev;
	}
	
	// Recursive function to reverse the list
	public ListInt rec_reverse(ListInt node) {
		if(node==null) 
			return null;
		if(node.next==null)
			return node;
		ListInt next = node.next;
		node.next=null;
		ListInt rev = rec_reverse(next);
		next.next = node;
		return rev;
	}
	
	//display function
	public void display(ListInt node) {
		while(node!=null) {
			node.display();
			node= node.next;
		}
		System.out.println();
	}
	
	// Print list in reverse order
	public void print_rev(ListInt node) {
		if(node==null) return ;
		ListInt next = node.next;
		print_rev(next);
		System.out.print(node.data+" ");
	}
	
	// checks if list is a palindrome
	public boolean isPalindrome(ListInt node) {
		if(node==null) return true;
		Stack<Integer> s = new Stack<Integer>();
		ListInt temp_node = node;
		while(temp_node!=null) {
			s.push(temp_node.data);
			temp_node =temp_node.next;
		}
		
		while(!s.isEmpty()) {
			int popped_value = s.pop();
			if(node.data!=popped_value) {
				return false;
			}
			node= node.next;
		}
		return true;
	}
	
	// checks if list is a palindrome
	public boolean isPalindrome_opt(ListInt node) {
		if(node==null)
			return true;
		ListInt slow=node;
		ListInt fast = node;
		ListInt slow_prev=null;
		ListInt middle = null; 
		
		// finds the middle element of list
		while(fast!=null && fast.next!=null) {
			slow_prev = slow;
			slow = slow.next;
			fast= fast.next.next;
		}
		// If the list has odd number of elements, save the middle element and move to next node
		if(fast!=null) {
			middle = slow;
			slow = slow.next;
		}
		// seperate list into two lists of equal size
		slow_prev.next = null;
		
		// Reverse second half of the list
		ListInt slow_new = rec_reverse(slow);
		
		// Checks if both halfs are same now
		boolean result = isSame(node,slow_new);
		// Reverse the second half of the list to bring it back to original order
		slow = rec_reverse(slow_new);
		// check if middle is not null, if so, attach the middle in between both lists
		if(middle!=null) {
			middle.next = slow;
			slow = middle;
		}
		// join both lists now.
		slow_prev.next = slow;
		// return result
		return result;
	}
	
	
	// Check two lists are same of not
	public boolean isSame(ListInt node1, ListInt node2) {
		if(node1==null && node2==null) return true;
		if(node1==null || node2==null) return false;
		return node1.data==node2.data ? isSame(node1.next,node2.next) : false;
	}
	
	// Sorted Insert 
	public void sorted_insert(int val) {
		ListInt new_node = new ListInt(val);
		if(head==null || head.data>val) {
			new_node.next = head;
			head = new_node;
		}
		else {
			ListInt cur = head;
			while(cur.next!=null && cur.next.data<val) 
				cur = cur.next;
			new_node.next = cur.next;
			cur.next = new_node;
		}
	}
	
	// Remove duplicates from sorted list 
	public void sorted_duplicates() {
		if(head==null)
			return;
		ListInt cur = head;
		while(cur!=null && cur.next!=null) {
			if(cur.data==cur.next.data) 
				cur.next = cur.next.next;
			else 
				cur = cur.next;
		}
	}
	
	//Remove duplicates from unsorted list
	public void unsorted_duplicates() {
		if(head==null) return;
		ListInt prev = null;
		ListInt cur = head;
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		while(cur!=null) {
			if(map.containsKey(cur.data)) {
				prev.next = cur.next;
			}
			else {
				map.put(cur.data, 1);
				prev = cur;
			}
			cur = cur.next;
		}
	}
	
	// Finds Intersection point of two LinkedList
	// First, finds the length of both the list and the difference in lengths of the lists
	public ListInt intersection(ListInt node1, ListInt node2) {
		if(node1==null || node2==null)
			return null;
		int nodes_first = length_recursive(node1);
		int nodes_second = length_recursive(node2);
		
		int diff = 0;
		
		if(nodes_first>nodes_second) {
			diff = nodes_first-nodes_second;
			return getIntersection(diff,node1,node2);
		}
		else {
			diff = nodes_second-nodes_first;
			return getIntersection(diff,node2,node1);
		}
	}
	
	// Moves the larger list by diff.
	// Then checks for the intersection point.
 	public ListInt getIntersection(int diff, ListInt first, ListInt second) {
 		
 		while(diff!=0) {
 			first = first.next;
 			diff--;
 		}
 		while(first!=null || second!=null) {
 			if(first.data==second.data) {
 				return first;
 			}
 			first= first.next;
 			second = second.next;
 		}
 		return null;
 	}
 	
 	// PairWise swap of elements in the list
 	public void swap_value() {
 		ListInt cur = head;
 		while(cur!=null && cur.next!=null) {
 			swap(cur,cur.data,cur.next.data);
 			cur = cur.next.next;
 		}
 	}
 	
 	// Recursive Swap of elements in the list 
 	public ListInt swap_value_rec(ListInt node) {
 		if(node!=null && node.next!=null) {
 			swap_value_rec(node.next.next);
 			swap(node,node.data,node.next.data);
 		}
 		return node;
 	}
 	
 	// swaps two values
 	public void swap(ListInt cur, int val1, int val2) {
 		int temp = val1;
 		cur.data  = val2;
 		cur.next.data = temp;
 	}
 	
 	// Move last element to the start of the list
 	public void moveFirst() {
 		if(head==null || head.next==null)
 			return;
 		ListInt cur = head;
 		ListInt prev = null;
 		while(cur.next!=null) {
 			prev = cur;
 			cur=cur.next;
 		}
 		
 		prev.next=null;
 		cur.next = head;
 		head = cur;
 	}
 	
 	// Intersection of two sorted lists
 	public ListInt intersection_list(ListInt node1, ListInt node2) {
 		if(node1==null || node2==null) return null;
 		ListInt dummy = new ListInt(0);
 		ListInt cur = dummy;
 		while(node1!=null && node2!=null) {
 			if(node1.data==node2.data) {
 				dummy.next = new ListInt(node1.data);
 				dummy = dummy.next;
 				node1 = node1.next;
 	 			node2 = node2.next;
 			}
 			else if(node1.data<node2.data) {
 				node1 = node1.next;
 			}
 			else {
 				node2 = node2.next;
 			}
 		}
 		return cur.next;
 	}
 	
 	//Recursive Intersection of two sorted lists
 	public ListInt rec_intersection_list(ListInt node1, ListInt node2) {
 		if(node1==null || node2==null) return null;
 		if(node1.data<node2.data) 
 			return rec_intersection_list(node1.next,node2);
 		if(node2.data<node1.data)
 			return rec_intersection_list(node2.next,node1);
 		ListInt temp = new ListInt(node1.data);
 		temp.next = rec_intersection_list(node1.next,node2.next);
 		return temp;
 	}
 	
 	// Delete Alternate Nodes in the list 
 	public void delete_alt() {
 		if(head==null)
 			return;
 		ListInt cur = head;
 		while(cur!=null && cur.next!=null) {
 			cur.next = cur.next.next;
 			cur = cur.next;
 		}
 	}
 	
 	// Recursive method to delete Alt nodes
 	public ListInt delete_alt_rec(ListInt node) {
 		if(node==null || node.next==null) 
 			return node;
 		node.next = delete_alt_rec(node.next.next);
 		return node;
 	}
 	
 	// Alternate Split of the List into two sub lists
 	public void splitIntoTwo(Wrapper wrapper) {
 		if(head==null) {
 			return;
 		}
 		ListInt dummy1 = new ListInt(0);
 		ListInt dummy2 = new ListInt(0);
 		ListInt cur1 = dummy1;
 		ListInt cur2 = dummy2;
 		ListInt cur = head;
 		while(cur!=null) {
 			cur1.next = new ListInt(cur.data);
 			cur1 = cur1.next;
 			cur = cur.next;
 			if(cur!=null) {
 				cur2.next = new ListInt(cur.data);
 				cur2 = cur2.next;
 				cur = cur.next;
 			}
 			else {
 				return;
 			}
 		}
 		wrapper.first = dummy1.next;
 		wrapper.second = dummy2.next;
 	}
 	
 	// Merge two lists into one list 
 	public ListInt merge(ListInt node1, ListInt node2) {
 		if(node1==null) {
 			return node2;
 		}
 		if(node2==null) {
 			return node1;
 		}
 		ListInt dummy = new ListInt(0);
 		ListInt cur = dummy;
 		while(node1!=null && node2!=null) {
 			if(node1.data==node2.data) {
 				cur.next = new ListInt(node1.data);
 				node1 = node1.next;
 				node2= node2.next;
 			}
 			else if(node1.data<node2.data) {
 				cur.next = new ListInt(node1.data);
 				node1 = node1.next;
 			}
 			else {
 				cur.next = new ListInt(node2.data);
 				node2 = node2.next;
 			}
 			cur = cur.next;
 		}
 		
 		while(node1!=null) {
 			cur.next = new ListInt(node1.data);
 			node1=node1.next;
 			cur = cur.next;
 		}
 		while(node2!=null) {
 			cur.next = new ListInt(node2.data);
 			node2 = node2.next;
 			cur = cur.next;
 		}
 		return dummy.next;
 	}
 	
 	// Recursive Merge method 
 	public ListInt rec_merge(ListInt node1, ListInt node2) {
 		if(node1==null)  return node2;
 		if(node2==null)  return node1;
 		
 		ListInt result = null;
 		
 		if(node1.data==node2.data) {
 			result = new ListInt(node1.data);
 			result.next = rec_merge(node1.next,node2.next);
 		}
 		else if(node1.data<node2.data) {
 			result = new ListInt(node1.data);
 			result.next = rec_merge(node1.next,node2);
 		}
 		else {
 			result = new ListInt(node2.data);
 			result.next = rec_merge(node1, node2.next);
 		}
 		return result;
 	}
 	
 	// Check if two lists are Identical 
 	public boolean isIdentical(ListInt node1, ListInt node2) {
 		while(true) {
 			if(node1==null && node2==null) return true;
 			if(node1==null || node2==null) return false;
 			if(node1.data!=node2.data) return false;
 			node1 = node1.next;
 			node2 = node2.next;
 		}
 	}
 	
 	// Recursive method, Checks if two lists are identical
 	public boolean rec_isIdentical(ListInt node1, ListInt node2) {
 		if(node1==null && node2==null) return true;
 		if(node1==null || node2==null) return false;
 		if(node1.data!=node2.data) return false;
 		return rec_isIdentical(node1.next,node2.next);
 	}
 	
 	
 	
 	/* Sorting in Linked List 
 	 * Merge Sort
 	 */
 	
 	public ListInt mergeSort(ListInt node) {
 		if(node==null || node.next==null) return node;
 		ListInt middle = getMiddle(node);
 		ListInt second = middle.next;
 		ListInt first = node;
 		middle.next= null;
 		first = mergeSort(first);
 		second = mergeSort(second);
 		return merge(first,second);
 	}
 	
 	
 	// Reverse Linked list in groups of K nodes
 	
 	/* PreCondition: k>0
 	 * PostCondition: returns null, if node is null, else returns a list with nodes reversed in groups of k size.
 	 * 
 	 * Input: 1->2->3->4->5->6->7->8
 	 * Output:3->2->1->6->5->4->7->8
 	 */
 	public ListInt reverse_groups(ListInt node,int k) {
 		if(node==null) return null;
 		
 		ListInt cur = node;
 		ListInt next = null;
 		ListInt prev = null;
 		int count =0;
 		while(count<k) {
 			if(cur==null) 
 				return node;
 			cur = cur.next;
 			count++;
 		}
 		cur = node;
 		count = 0;
 		while(cur!=null && count<k) {
 			next = cur.next;
 			cur.next = prev;
 			prev = cur;
 			cur = next;
 			count++;
 		}
 		
 		if(next!=null) {
 			node.next = reverse_groups(next,k);
 		}
 		return prev;
 	}
 	
 	
 	/* PreCondition: k>0
 	 * PostCondition: returns null, if node is null, else returns node with elements reversed in groups of k size
 	 * 
 	 * Input: 1->2->3->4->5->6->7->8
 	 * Output: 3->2->1->6->5->4->8->7
 	 * 
 	 */
 		public ListInt reverse_kGroups(ListInt node, int k) {
 			if(node==null) return null;
 			
 			ListInt cur = node;
 			ListInt prev = null;
 			ListInt next = null;
 			
 			int count = 0;
 			
 			while(cur!=null && count<k) {
 				next = cur.next;
 				cur.next = prev;
 				prev = cur;
 				cur = next;
 				count++;
 			}
 			
 			if(next!=null) {
 				node.next = reverse_kGroups(next,k);
 			}
 			return prev;
 		}
 	
 		
 	/* Reverse alternate K nodes in a linked list
 	 * 
 	 * PreCondition: k>0
 	 * PostCondition: returns null, if node is null, else returns node with alternate k nodes reversed
 	 * 
 	 * Input: 1->2->3->4->5->6->7->8
 	 * Output: 3->2->1->4->-5>-6->8->7
 	 */
 		
 	public ListInt reverse_alt_kNodes(ListInt node, int k) {
 		if(node==null) return null;
 		
 		ListInt cur = node;
 		ListInt next = null;
 		ListInt prev = null;
 		
 		int count = 0;
 		while(cur!=null && count<k) {
 			next = cur.next;
 			cur.next = prev;
 			prev = cur;
 			cur = next;
 			count++;
 		}
 		node.next = next;
 		count=1;
 		while(cur!=null && count<k) {
 			cur = cur.next;
 			count++;
 		}
 		if(cur!=null) {
 			cur.next = reverse_alt_kNodes(cur.next,k);
 		}
 		return prev;
 	}
 	
 	/* Reverse Alternate K nodes Optimized method
 	 * 
 	 * PreCondition: k>0
 	 * PostCondition: Returns null if node is null, else returns list with alternate k nodes reversed.
 	 * 
 	 * Input: 1->2->3->4->5->6->7->8
 	 * Output: 3->2->1->4->-5>-6->8->7
 	 */
 	
 	public ListInt reverse_alt_knodes_opt(ListInt node, int k, boolean decide) {
 		if(node==null) return null;
 		
 		ListInt cur = node;
 		ListInt next = null;
 		ListInt prev = null;
 		
 		int count = 0;
 		
 		while(count<k && cur!=null) {
 			next = cur.next;
 			
 			if(decide) {
 				cur.next = prev;
 			}
 			
 			prev = cur;
 			cur = next;
 			count++;
 		}
 		
 		if(decide) {
 			node.next = reverse_alt_knodes_opt(next,k,!decide);
 			return prev;
 		}
 		
 		else {
 			prev.next = reverse_alt_knodes_opt(cur,k,!decide);
 			return node;
 		}
 	}
 	
 	
 	/* Reverse the list from a position m to a position n
 	 * PreCondition: 1<=m<=n<=length of list
 	 * PostCondition: List with nodes between m and n reversed.
 	 * 
 	 * Input: 1->2->3->4->5->NULL, m=2 and n=4
 	 * Output: 1->4->3->2->5->NULL
 	 */
 	
 	public ListInt reverse_mn(ListInt node, int m, int n) {
 		if(node ==null) {
 			return null;
 		}
 		if(m==n || m>n) {
 			return node;
 		}
 		ListInt cur = node;
 		ListInt prev = null;
 		ListInt next = null;
 		ListInt start = null;
 		
 		int count = 0;
 		
 		while(count<m) {
 			next = cur.next;
 			prev = cur;
 			cur = next;
 			count++;
 		}
 		start = prev;
 		count = m;
 		while(count<=n) {
 			next = cur.next;
 			cur.next = prev;
 			prev = cur;
 			cur=next;
 			count++;
 		}
 		
 		if(start!=null) {
 			start.next.next = cur;
 			start.next = prev;
 			return node;
 		}
 		else {
 			node.next = cur;
 			return prev;
 		}
 	}
 	
 	/*
 	 *  Delete Nodes which have greater value on the right side
 	 *  
 	 */
 	public ListInt delete_greater_rightSide(ListInt node) {
 		if(node == null) 
 			return null;
 		
 		ListInt reversed = rec_reverse(node);
 		
 		if(reversed==null)
 			return null;
 		
 		ListInt cur = reversed;
 		ListInt prev = cur;
 		
 		int heighest = cur.data;
 	
 		cur = cur.next;
 		
 		while(cur!=null) {
 			if(cur.data>heighest) {
 				heighest = cur.data;
 				prev = cur;
 			}
 			else {
 				prev.next = cur.next;
 			}
 			cur = cur.next;
 		}
 		return rec_reverse(reversed);
 	}
 	
 	/* Segregate Even and Odd Nodes in a Linked List
 	 * 
 	 * Input: 17->15->8->12->10->5->4->1->7->6->NULL
 	 * Output: 8->12->10->4->6->17->15->5->1->7->NULL
 	 */
 	
 	public ListInt segregateEvenOdd(ListInt node) {
 		if(node==null || node.next==null) return node;
 		
 		ListInt start = node;
 		ListInt end = start;
 		
 		while(end.next!=null) {
 			end = end.next;
 		}
 		
 		while(start.data%2 !=0) {
 			end.next = start;
 			end = end.next;
 			start = start.next;
 			end.next = null;
 			if(start==node) 
 				return node;
 		}
 		
 		ListInt new_head = start;
 		ListInt prev_end = end;
 		ListInt prev = start;
 		start = start.next;	
 		
 		while(start!=null && start!=prev_end) {
 			if(start.data%2!=0) {
 				end.next = start;
 				end = end.next;
 				prev.next = start.next;
 				start = start.next;
 				end.next= null;
 			}
 			else {
 				prev = start;
 				start = start.next;
 			}
 		}
 		
 		if(start==prev_end && start.data%2!=0) {
 			end.next = start;
 			end = end.next;
 			prev.next = start.next;
 			end.next = null;
 		}
 		return new_head;
 	}
 	
 	/* Detect and remove Loop in the LinkedList
 	 * 
 	 * 
 	 */
 	
 	public ListInt removeLoop(ListInt node) {
 		if(node==null || node.next==null ) return node;
 		
 		ListInt fast = node;
 		ListInt slow = node;
 		
 		while(fast!=null && fast.next!=null) {
 			slow = slow.next;
 			fast = fast.next.next;
 			if(slow==fast) break;
 		}
 		
 		if(fast==null || fast.next==null) 
 			return node;
 		int count= 0;
 		do {
 			fast = fast.next;
 			count++;
 		}while(fast!=slow);
 		
 		ListInt prev = null;
 		
 		int counter=0;
 		fast = node;
 		slow = node;
 		while(counter<count) {
 			prev=fast;
 			fast = fast.next;
 			counter++;
 		}
 		
 		while(slow!=fast) {
 			slow = slow.next;
 			prev = fast;
 			fast = fast.next;
 		}
 		prev.next = null;
 		return node;
 	}
 	
 	/* Add two numbers represented by Linked Lists
 	 * 
 	 */
 	public ListInt addTwoNumbers(ListInt node1, ListInt node2) {
 		if(node1==null && node2==null) return null;
 		if(node1==null) return node2;
 		if(node2==null) return node1;
 		
 		int carry = 0;
 		int total = 0;
 		
 		ListInt dummy = new ListInt(0);
 		ListInt moving_dummy = dummy;
 		
 		while(node1!=null || node2!=null) {
 			total = (node1==null ? 0:node1.data) + (node2==null ? 0 :node2.data) + carry;
 			carry = total/10;
 			total = total%10;
 			moving_dummy.next = new ListInt(total);
 			moving_dummy = moving_dummy.next;
 			node1 = (node1==null ? null : node1.next);
 			node2 = (node2==null ? null : node2.next);
 		}
 		
 		if(carry!=0) {
 			ListInt carryNode = new ListInt(carry);
 			moving_dummy.next = carryNode;
 		}
 		return dummy.next;	
 	}
 	
 	/* Rotate the linked list by k 
 	 * 
 	 * Input: 10->20->30->40->50->60->NULL
 	 * Output: 50->60->10->20->30->40->NULL
 	 */
 	
 	public ListInt rotate(ListInt node, int k) {
 		if(node == null || node.next==null) return node;
 		
 		ListInt new_end = node;
 		ListInt new_head = null;
 		int count = 0;
 		while(new_end!=null && count<k-1) {
 			new_end = new_end.next;
 			count++;
 		}
 		if(new_end==null || new_end.next==null) 
 			return node;
 		
 		new_head = new_end.next;
 		new_end.next=null;
 		
 		ListInt end = new_head;
 		while(end.next!=null) {
 			end = end.next;
 		}
 		end.next = node;
 		return new_head;
 	}
 	
 	
 	/* Flatten a multi-level list
 	 * 
 	 * 
 	 */
 	public ListIntExtra flatten(ListIntExtra node) {
 		if(node==null || node.right==null) {
 			return node;
 		}
 		return merge(node,flatten(node.right));
 	}
 	
 	public ListIntExtra merge(ListIntExtra node1, ListIntExtra node2) {
 		if(node1==null) {
 			return node2;
 		}
 		if(node2==null) {
 			return node1;
 		}
 		ListIntExtra dummy = new ListIntExtra(0);
 		ListIntExtra cur = dummy;
 		while(node1!=null && node2!=null) {
 			if(node1.data==node2.data) {
 				cur.next = new ListIntExtra(node1.data);
 				node1 = node1.next;
 				node2= node2.next;
 			}
 			else if(node1.data<node2.data) {
 				cur.next = new ListIntExtra(node1.data);
 				node1 = node1.next;
 			}
 			else {
 				cur.next = new ListIntExtra(node2.data);
 				node2 = node2.next;
 			}
 			cur = cur.next;
 		}
 		
 		while(node1!=null) {
 			cur.next = new ListIntExtra(node1.data);
 			node1=node1.next;
 			cur = cur.next;
 		}
 		while(node2!=null) {
 			cur.next = new ListIntExtra(node2.data);
 			node2 = node2.next;
 			cur = cur.next;
 		}
 		return dummy.next;
 	}
 	
 	/* Add Two numbers represented as Linked List
 	 * 
 	 * Input: 5->6->3
 	 *        8->4->2
 	 *        
 	 * Output: 1->4->0->5
 	 */
 	
 	public ListInt addRightRep(ListInt node1, ListInt node2) {
 		if(node1==null && node2==null) return null;
 		if(node1==null) return node2;
 		if(node2==null) return node1;
 		
 		int length1=0;
 		int length2=0;
 		
 		length1 = getSize(node1);
 		length2 = getSize(node2);
 		ListInt final_result = null;
 		Wrapper_carry w = new Wrapper_carry();
  		if(length1==length2) {
  			final_result = sameSizeAdd(node1,node2,w);
  		}
  		else {
 		
  			int diff = Math.abs(length1-length2);
 		
  			if(length1<length2) {
  				ListInt temp = node1;
  				node1 = node2;
  				node2 = temp;
  			}
  			ListInt cur=node1;
  			int count=0;
  			while(count<diff) {
  				cur = cur.next;
  				count++;
  			}
 		
  			ListInt result = sameSizeAdd(cur,node2,w);
 		
  			final_result = addRemaining(node1,cur,w);
 		
  			cur = final_result;
  			while(cur.next!=null) cur = cur.next;
 		
  			cur.next = result;
 		
  		}
  		if(w.carry!=0) {
  			ListInt carryNode = new ListInt(w.carry);
 			carryNode.next = final_result;
 			final_result = carryNode;
 		}
 		
 		return final_result;
 	}
 	
 	// returns the size of the list
 	public int getSize(ListInt node) {
 		if(node==null) return 0;
 		return 1+getSize(node.next);
 	}
 	
 	// Adds two lists of same size
 	public ListInt sameSizeAdd(ListInt node1, ListInt node2, Wrapper_carry w) {
 		if(node1==null) return null;
 		
 		ListInt result = new ListInt(0);
 		
 		result.next = sameSizeAdd(node1.next,node2.next,w);
 		
 		int total = node1.data+node2.data+w.carry;
 		
 		w.carry = total/10;
 		
 		total = total%10;
 		result.data  = total;
 		return result;
 	}
 	// adds the carry to the extra part of the larger linked list
 	public ListInt addRemaining(ListInt node1, ListInt node1_middle, Wrapper_carry w) {
 		ListInt res = null;
 		if(node1!=node1_middle) {
 			res = new ListInt(0);
 			res.next = addRemaining(node1.next,node1_middle,w);
 			int total = node1.data+w.carry;
 			w.carry = total/10;
 			total = total%10;
 			res.data = total;
 		}
 		return res;
 	}
 	
 	/* Sort a linked list containing 0's, 1's, 2's
 	 * 
 	 * 
 	 */
 	public ListInt sort012(ListInt node) {
 		if(node==null) return null;
 		
 		int[] count = new int[3];
 		
 		ListInt cur = node;
 		
 		while(cur!=null) {
 			count[cur.data]++;
 			cur = cur.next;
 		}
 		
 		cur = node;
 		int i=0;
 		while(cur!=null) {
 			if(count[i]==0) i++;
 			else {
 				cur.data = i;
 				count[i]--;
 				cur = cur.next;
 			}
 		}
 		return node;
 	}
 	
 	/* Flatten MultiLevel Linked List
 	 * 
 	 * Input: 10 -> 5 -> 12 -> 7 -> 11
 	 *        |                |
 	 *        4  -> 20-> 13   17 -> 6
 	 *              |    |     |
 	 *              2    16    9 -> 8
 	 *                   |     |
 	 *                   3    19 -> 15
 	 * 
 	 * Output: 10->5->12->7->11->4->20->13->17->6->2->16->9->8->3->19->15
 	 */
 	
 	public ListIntExtra flattenList(ListIntExtra node) {
 		if( node == null) return null;
 		
 		ListIntExtra cur = node;
 		ListIntExtra dummy = new ListIntExtra(0);
 		ListIntExtra tail = node;
 		while(tail.right!=null) {
 			tail = tail.right;
 		}
 		
 		
 		while(cur!=tail) {
 			if(cur.next!=null) {
 				tail.right = cur.next;
 				ListIntExtra temp = cur.next;
 				while(temp.right!=null) {
 					temp = temp.right;
 				}
 				tail = temp;
 			}
 			cur = cur.right;
 		}
 		return node;
 	}
 	
 	/* Pairwise swap of elements in the linked list by changing the links
 	 * 
 	 */
 	public ListInt pairWiseSwapLinks(ListInt node) {
 		if(node == null || node.next == null) return node;
 		
 		ListInt cur = node.next;
 		ListInt prev = node;
 		ListInt next = null;
 		node = cur;
 		
 		while(true) {
 			next = cur.next;
 			cur.next = prev;
 			
 			if(next == null || next.next == null) {
 				prev.next = next;
 				break;
 			}
 			prev.next = next.next;
 			
 			prev = next;
 			cur = prev.next;
 		}
 		return node;
 	}
 	
 	/*
 	 *  Recursive version of Pairwise swap of elements by changing the links
 	 */
 	public ListInt rec_pairWiseSwapLinks(ListInt node) {
 		if(node==null || node.next==null) return node;
 		
 		ListInt new_head = node.next;
 		ListInt next = node.next.next;
 		node.next.next = node;
 		node.next = rec_pairWiseSwapLinks(next);
 		return new_head;
 	}
 	
 	/*
 	 * Remove middle points from a linked list of line segments
 	 * 
 	 */
 	
 	public ListIntExtra removeMiddlePoints(ListIntExtra node) {
 		if(node==null || node.next==null || node.next.next==null) return node;
 		
 		ListIntExtra cur = node;
 		ListIntExtra next = node.next;
 		ListIntExtra nextNext = node.next.next;
 		
 		if(cur.data==next.data) {
 			
 			while(nextNext!=null && next.data==nextNext.data) {
 				next =nextNext;
 				nextNext = nextNext.next;
 			}
 			if(nextNext==null) 
 				return node;
 			else 
 				node.next = nextNext;
 		}
 		else if(cur.data2 == next.data2) {
 			while(nextNext!=null && next.data2==nextNext.data2) {
 				next = nextNext;
 				nextNext = nextNext.next;
 			}
 			if(nextNext==null) 
 				return node;
 			else 
 				node.next = nextNext;
 		}
 		else {
 			return null;
 		}
 		node.next = removeMiddlePoints(node.next);
 		return node;
 	}
 	
 	/* Returns Maximum sum out of two sorted lists having some common nodes
 	 * 
 	 */
 	
 	public ListInt maxSum(ListInt node1, ListInt node2) {
 		
 		// Check if any of the Lists are empty
 		if(node1==null && node2==null) return null;
 		if(node1==null) return node2;
 		if(node2==null) return node1;
 		
 		
 		ListInt prev1 = node1;
 		ListInt prev2 = node2;
 		
 		//Iterators 
 		ListInt cur1 = node1;
 		ListInt cur2 = node2;
 		ListInt result = null;
 		
 		
 		while(cur1!=null || cur2!=null) {
 			
 			int sum1 = 0; int sum2=0;
 			while(cur1!=null && cur2!=null && cur1.data!=cur2.data) {
 				if(cur1.data<cur2.data) {
 					sum1 +=cur1.data;
 					cur1 = cur1.next;
 				}
 				else {
 					sum2 +=cur2.data;
 					cur2 = cur2.next;
 				}
 			}
 			
 			if(cur1==null) {
 				while(cur2!=null) {
 					sum2 +=cur2.data;
 					cur2 = cur2.next;
 				}
 			}
 			
 			if(cur2==null) {
 				while(cur1!=null) {
 					sum1+=cur1.data;
 					cur1 = cur1.next;
 				}
 			}
 			
 			if(prev1==node1 && prev2==node2) {
 				result = sum1>sum2 ? node1:node2;
 			}
 			else {
 				if(sum1>sum2) {
 					prev2.next = prev1.next;
 				}
 				else{
 					prev1.next = prev2.next;
 				}
 			}
 			prev1 = cur1;
 			prev2 = cur2;
 			
 			if(cur1!=null) cur1 = cur1.next;
 			if(cur2!=null) cur2 = cur2.next;
 		}
 		return result;
 	}
 	
 	
	// Main Method
	public static void main(String args[]) {
		ListAllInOne l = new ListAllInOne();
	/*
		l.sorted_insert(70);
		l.sorted_insert(90);
		l.sorted_insert(60);
		l.sorted_insert(80);
		l.sorted_insert(50);
		l.sorted_insert(50);
		l.sorted_insert(70);
		l.sorted_insert(90);
	*/
		
		l.add(1);
		l.add(3);
		l.add(30);
		l.add(90);
		l.add(120);
		l.add(240);
		l.add(511);
		
		l.display();
		
		ListAllInOne l2 = new ListAllInOne();
		
		l2.add(0);
		l2.add(3);
		l2.add(12);
		l2.add(32);
		l2.add(90);
		l2.add(125);
		l2.add(240);
		l2.add(249);
		
		l2.display();
	//	ListInt result = l.reverse_groups(l.head, 3);
		
	//	ListInt result = l.mergeSort(l.head);
	//	ListInt result = l.rec_merge(l.head, l2.head);
	//	l.display(result);
		
	//	l.display();
		
		ListInt result2 = l.maxSum(l.head, l2.head);
		l.display(result2);
		
	//	Wrapper wrapper = new Wrapper();
		
	//	l.splitIntoTwo(wrapper);
	//	ListInt res = l.delete_alt_rec(l.head);
	//	l.display(wrapper.first);
	//	l.display(wrapper.second);
	//	ListInt res = l.swap_value_rec(l.head);
	//	l.moveFirst();
	//	l.display();
		
	//	ListAllInOne l2 = new ListAllInOne();
	//	l2.sorted_insert(90);
		
	//	ListInt result = l.intersection(l.head, l2.head);
	//	System.out.println(result==null?"null":result.data);
	//	System.out.println(l.isPalindrome_opt(l.head));
	//	l.print_rev(l.head);
	//	ListInt result = l.rec_reverse(l.head);
	//	l.display(result);
		/*
		System.out.println("Nth node from end "+l.getNthNode_last(0));
		System.out.format("The element at index 3 is %d ",l.getNthNode(3));
		System.out.println(l.length_iterative());
		System.out.println(l.length_recursive(l.head));
		*/
	}
	
	/*
	 * Utility class
	 * Wrapper class which contains two nodes
	 */
	@SuppressWarnings("unused")
	private class Wrapper {
		
		ListInt first;
		ListInt second;
		
	}
	

	/*
	 * Utility class
	 * Wrapper class which contains an integer representing carry.
	 */
	private class Wrapper_carry {
		public int carry=0;
	}
}

 /*
  * Utility Class
  * 
  * Defines a node in the list
  * 
  */

class ListIntExtra {
	public int data;
	public ListIntExtra next;
	public ListIntExtra right;
	public int data2;
	public ListIntExtra(int val) {
		data = val;
		next = null;
		right = null;
	}
	
	public ListIntExtra(int val, int val2) {
		data = val;
		data2 = val2;
		next = null;
		right = null;
	}
	
	public void display() {
		System.out.print(data+" ");
	}
	
	public void display2() {
		System.out.print(data2+" ");
	}
}

class ListInt {
	
	public int data;
	public ListInt next;
	
	public ListInt(int data) {
		this.data = data;
		this.next = null;
	}
	
	public void display() {
		System.out.print(data+" ");
	}
}
