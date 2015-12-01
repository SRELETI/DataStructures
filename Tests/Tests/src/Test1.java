import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class Test1 {

	public static void main(String args[]) {
	/* Test Case for Mincoins problem:
	 * int[] coins = {10,20,50};
	 * int sum = 100;
	 * System.out.println(minCoins(sum,coins));
	 */
//	System.out.println(kmpSubString("test","thisisatesttext"));
//	System.out.println(longestSubString("()(()))))"));
	//	System.out.println(longestValidString("()(()))))"));
		
		TreeNode root  = new TreeNode(15);
		root.left = new TreeNode(10);
		root.right = new TreeNode(20);
		root.left.left = new TreeNode(8);
		root.left.right = new TreeNode(12);
		root.right.left = new TreeNode(16);
		root.right.right = new TreeNode(25);
	//	root.left.right.left = new TreeNode(10);
	//	root.left.right.right = new TreeNode(14);
		
	//	bottomView(root);
	//	int[] arr = {3,1,5,9,12};
	//	System.out.println(findPartition(arr));
	//	System.out.println(findParDP(arr));
		
		System.out.println(isPairPresent(root,33));
	}
	
	/*
	 * Minimum number of coins required to make a given value.
	 */
	public static int minCoins(int sum, int[] coins) {
		int[] sums = new int[sum+1];
		// initially max no of coins required to form each sum is Infinite. 
		Arrays.fill(sums,Integer.MAX_VALUE);
		// Zero number of coins required to form sum of zero.
		sums[0] = 0;
		// Use DP to compute min coins req for smaller sums. 
		for(int i=1;i<=sum;i++) {
			for(int j=0;j<coins.length;j++) {
				if(coins[j]<=i && sums[i-coins[j]] != Integer.MAX_VALUE && 1+sums[i-coins[j]]<sums[i])
					sums[i] = 1 + sums[i-coins[j]];
			}
		}
		return sums[sum];
	}
	
	/*
	 * Length of longest valid paranthesis substring
	 */
	public static int longestSubString(String para) {
		if(para == null || para.length() == 0) return 0;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(-1);
		int i =0;
		int max = 0;
		while(i<para.length()) {
			if(para.charAt(i)=='(') 
				stack.push(i);
			else {
				stack.pop();
				if(!stack.isEmpty()) {
					max = Math.max(max, i-stack.peek());
				}
				else 
					stack.push(i);
			}
			i++;
		}
		return max;
	}
	
	/*
	 * Brute force subString algorithm
	 */
	public static int isSubString(String s1, String s2) {
		for(int i=0;i<s2.length()-s1.length();i++) {
			int j=0;
			for(;j<s1.length();j++) {
				if(s2.charAt(i+j) != s1.charAt(j))
					break;
			}
			if(j==s1.length())
				return i;
		}
		return -1;
	}
	
	/*
	 * kMp Pattern Matching
	 */
	
	public static boolean kmpSubString(String pat, String text) {
		if(pat == null) return true;
		if(text == null) return false;
		if(pat.length()>text.length()) return false;
		if(pat.equals(text)) return true;
		int[] lps = preProcessPat(pat);
		int i = 0;
		int j=0;
		System.out.println(Arrays.toString(lps));
		while(i<text.length()) {
			if(j<pat.length() && pat.charAt(j) == text.charAt(i)) {
				i++;
				j++;
			}
			else {
				if(j==pat.length()) {
					System.out.println("Found at index "+(i-j));
					j=0;
					return true;
				}
				else {
					if(j!=0) 
						j = lps[j-1];
					else {
						i++;
					}
				}
			}
		}
		return false;
	}
	
	private static int[] preProcessPat(String pat) {
		int[] lps = new int[pat.length()];
		lps[0] = 0;
		int len = 0;
		int i = 1;
		while(i<pat.length()) {
			if(pat.charAt(i)==pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			}
			else {
				if(len != 0){
					len = lps[len-1];
				}
				else {
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;
	}
	
	
	/*
	 * Pattern Matching: Robin-Karp. 
	 */
	public static int isSubStringRK(String pat, String text) {
		if(pat == null) return 0;
		if(text == null) return -1;
		int h = 1;
		int q = 101;
		int d = 256;
		int p = 0;
		int t = 0;
		int M = pat.length();
		for(int i=0;i<M-1;i++)
			h = (h*d)%q;
		for(int i=0;i<M;i++) {
			p = (p*d+pat.charAt(i))%q;
			t = (t*d+text.charAt(i))%q;
		}
		
		for(int i=0;i<=text.length()-pat.length();i++) {
			if(p==t) {
				int j =0;
				for(;j<pat.length();j++) {
					if(text.charAt(i+j) != pat.charAt(j))
						break;
				}
				if(j==M)
					return i;
			}
			if(i != text.length()-pat.length()) {
				t = (d*(t-h*text.charAt(i)+text.charAt(i+M)))%q;
				if(t<0)
					t = t+q;
			}
		}
		return -1;
	}
	
	/*
	 * input: ((()
	 * Output: 2
	 * 
	 * input: )()())
	 * Output: 4
	 */
	public static int longestValidString(String input) {
		if(input == null || input.length() == 0) return 0;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(-1);
		int max = 0;
		for(int i=0;i<input.length();i++) {
			if(input.charAt(i)=='(')
				stack.push(i);
			else {
				stack.pop();
				if(!stack.isEmpty()) {
					max = Math.max(max, i-stack.peek());
				}
				else 
					stack.push(i);
			}
		}
		return max;
	}
	 
	
	/*
	 * Bottom View of a Binary Tree
	 * 
	 *             20
	 *           /    \
	 *          8      22
	 *        /   \   /   \
	 *      5      3 4     25
	 *           /  \
	 *         10    14
	 *     
	 *     Bottom View: 5, 10,4,14,25
	 */
	
	public static void bottomView(TreeNode root) {
		if(root == null) return;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		Map<Integer,Integer> map = new TreeMap<Integer,Integer>();
		
		while(!queue.isEmpty()) {
			root = queue.remove();
			map.put(root.hd, root.data);
			if(root.left != null) {
				root.left.hd = root.hd-1;
				queue.add(root.left);
			}
			if(root.right != null) {
				root.right.hd = root.hd+1;
				queue.add(root.right);
			}
		}
		Iterator<Integer> it = map.keySet().iterator();
		while(it.hasNext()) {
			int key = it.next();
			System.out.println(key+" : "+map.get(key));
		}
	}
	
	
	/*
	 * Partition Set Problem:
	 * Check if there are two subsets with same sum. 
	 * Recursive approach: Exponential time complexity
	 */
	public static boolean findPartition(int[] arr) {
		if(arr == null || arr.length == 0) return true;
		int sum = 0;
		for(int element:arr) 
			sum += element;
		if((sum&1) != 0)
			return false;
		return findParUtils(arr,arr.length,sum/2);
	}
	
	private static boolean findParUtils(int[] arr, int n, int sum) {
		if(sum == 0) return true;
		if(n == 0 && sum != 0) return false;
		if(arr[n-1]>sum)
			return findParUtils(arr,n-1,sum);
		return findParUtils(arr,n-1,sum) || findParUtils(arr,n-1,sum-arr[n-1]);
	}
	
	/*
	 * DP approach for Partition Set Problem.
	 * O(n*sum)
	 */
	
	private static boolean findParDP(int[] arr) {
		if(arr == null || arr.length == 0) return true;
		int sum = 0;
		for(int ele:arr)
			sum += ele;
		if((sum&1) == 1)
			return false;
		boolean[][] sumArr = new boolean[(sum/2)+1][arr.length+1];
		for(int i=0;i<arr.length+1;i++)
			sumArr[0][i] = true;
		for(int i=1;i<=sum/2;i++) 
			sumArr[i][0] = false;
		for(int i=1;i<=sum/2;i++) {
			for(int j=1;j<=arr.length;j++) {
				sumArr[i][j] = sumArr[i][j-1];
				if(i>=arr[j-1]) {
					sumArr[i][j] = sumArr[i][j] || sumArr[i-arr[j-1]][j-1];
				}
			}
		}
		return sumArr[sum/2][arr.length];
	}
	
	/*
	 * Find a pair in BST which sums up to a given number. 
	 * 
	 */
	public static boolean isPairPresent(TreeNode root, int target) {
		if(root == null) return false;
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		Stack<TreeNode> stack2 = new Stack<TreeNode>();
		
		boolean done1 = false;
		boolean done2 = false;
		
		TreeNode cur1 = root;
		TreeNode cur2 = root;
		int val1 = 0; int val2 = 0;
		while(true) {
			while(!done1) {
				if(cur1 != null) {
					stack1.push(cur1);
					cur1 = cur1.left;
				}
				else {
					if(stack1.isEmpty())
						done1 = true;
					else {
						cur1 = stack1.pop();
						val1 = cur1.data;
						cur1 = cur1.right;
						done1 = true;
					}
				}
			}
			
			while(!done2) {
				if(cur2 != null) {
					stack2.push(cur2);
					cur2 = cur2.right;
				}
				else {
					if(stack2.isEmpty()) 
						done2 = true;
					else {
						cur2 = stack2.pop();
						val2 = cur2.data;
						cur2 = cur2.left;
						done2 = true;
					}
				}
			}
			
			if(val1 != val2 && val1 + val2 == target) {
				System.out.println(val1+"+"+val2+"="+target);
				return true;
			}
			else if(val1+val2<target)
				done1 = false;
			else if(val1+val2>target)
				done2 = false;
			if(val1 >= val2)
				return false;
		}
	}
	
}

class TreeNode {
	public int data;
	public TreeNode left;
	public TreeNode right;
	public int hd;
	
	public TreeNode(int val) {
		data = val;
		hd =0;
		left = null;
		right = null;
	}
}