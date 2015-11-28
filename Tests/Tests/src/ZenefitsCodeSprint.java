import java.util.HashMap;
import java.util.Map;

public class ZenefitsCodeSprint {

	public static void main(String args[]) {
	//	System.out.println(candyShop(5,new int[]{1,2,4,5}));
	//	System.out.println(encryptionModule("abd","pqr"));
		String[] shoes = {"nike 3 blue L",
				"nike 4 green R",
				"adidas 4 green L",
				"nike 3 blue R",
				"adidas 4 green R",
				"adidas 4 green R",
				"puma 8 red L",
				"puma 8 red R"};
		System.out.println(miseryOfJarBinks(shoes));
	}
	
	// 1st problem: Find the number of ways in which given coins can be summed
	// to get sum as 'n' 
	public static int candyShop(int n,int[] coins) {
		int[] sum = new int[n+1];
		sum[0] = 1;
		for(int i=0;i<coins.length;i++) {
			for(int j=coins[i];j<=n;j++) {
				sum[j] += sum[j-coins[i]];
			}
		}
		return sum[n];
	}
	
	// 2nd problem: CaesarCipher problem, Given two String P and E`.
	// P - is original string, E- is string encrypted using caesar cipher using unknown shift.
	// E` is scrambled string of E. By scrambled, some of the characters are replaced. 
	// You have to find the min number of mismatches between E and E'. Try different shifts and see.
	// Example: P - abd,  E' - pqr, E in this case with min mismatches will be pqs and min mismatches = 1
	public static int encryptionModule(String P, String E) {
		int total_dif = Integer.MAX_VALUE;
		for(int i=0;i<26;i++) {
			String E_org = caesarCipher(P,i);
			if(E_org.equals(E))
				return 0;
			int dif = findDiffs(E_org,E);
			total_dif = Math.min(total_dif, dif);
		}
		return total_dif;
	}
	
	private static int findDiffs(String org, String scram) {
		int dif = 0;
		for(int i=0;i<org.length();i++) {
			if(org.charAt(i) != scram.charAt(i))
				dif++;
		}
		return dif;
	}
	
	private static String caesarCipher(String p, int shift) {
		StringBuilder sb = new StringBuilder("");
		for(int i=0;i<p.length();i++) {
			if(p.charAt(i)+shift > 'z') {
				sb.append((char)('a'+((p.charAt(i)+shift)-('z'))));
			}
			else 
				sb.append((char)(p.charAt(i)+shift));
		}
		return sb.toString();
	}
	
	// Problem 3: Misery of Jar Jar Binks.
	// Find the number of pairs of shoes that can be formed from the input
	public static int miseryOfJarBinks(String[] shoes) {
		if(shoes == null || shoes.length == 0 ) return 0;
		Map<String, Integer> map = new HashMap<String,Integer>();
		int pairs = 0;
		for(int i=0;i<shoes.length;i++) {
			String key = shoes[i].substring(0, shoes[i].length()-1);
			int lr = shoes[i].charAt(shoes[i].length()-1) == 'L' ? 1:-1;
			int val = map.getOrDefault(key, 0);
			if(Integer.signum(lr) == -Integer.signum(val))
				pairs++;
			map.put(key, val+lr);
		}
		return pairs;
	}
	
}
