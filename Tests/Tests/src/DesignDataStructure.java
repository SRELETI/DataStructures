import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

	/*
	 * Design a datastructure which supports following operations in O(1) time
	 * Insert an element
	 * Delete an element
	 * Search an element
	 * GetRandom element from the list
	 */

public class DesignDataStructure {
	private Map<Integer,Integer> map;
	private List<Integer> arr;
	
	public DesignDataStructure() {
		map = new HashMap<Integer,Integer>();
		arr = new ArrayList<Integer>();
	}
	
	//insert
	public void insert(int val) {
		if(map.containsKey(val))
			return;
		arr.add(val);
		map.put(val, arr.size()-1);
	}
	//delete
	public void delete(int val) {
		if(!map.containsKey(val)) 
			return;
		//handle case when there is only one element in the datastructure.
		if(arr.size()==1) {
			map.remove(val);
			arr.remove(arr.size()-1);
		}
		else{
			int loc = map.get(val);
			map.remove(val);
			map.put(arr.get(arr.size()-1),loc);
			Collections.swap(arr, loc, arr.size()-1);
			arr.remove(arr.size()-1);
		}
	}
	// search
	public boolean search(int val) {
		return map.containsKey(val);
	}
	// getRandom
	public int getRandom() {
		Random rad = new Random();
		int index = rad.nextInt(arr.size());
		return arr.get(index);
	}
	
	
	public static void main(String args[]) {
		DesignDataStructure ds = new DesignDataStructure();
		ds.insert(10);
		ds.insert(20);
		ds.insert(30);
		ds.insert(40);
		System.out.println(ds.search(30));
		ds.delete(20);
		ds.insert(50);
		System.out.println(ds.search(50));
		System.out.println(ds.search(20));
		System.out.println(ds.getRandom());
		
	}
}
