import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class RedisCSV {
	public static void main(String[] args) {
		// Connecting to Redis server on localhost

		run();
		// System.out.println("Server is running: " + jedis.ping());

	}

	public static void run() {
		/*
		 * BufferedReader br = null; try { String readCSV =
		 * "I:/tempdata/articlecategories_en.csv";
		 * 
		 * String line = ""; File writeCSV = new
		 * File("I:/tempdata/categories.csv"); if (!writeCSV.exists()) {
		 * writeCSV.createNewFile(); } FileWriter fw = new
		 * FileWriter(writeCSV.getAbsoluteFile()); BufferedWriter bw = new
		 * BufferedWriter(fw); br = new BufferedReader(new FileReader(readCSV));
		 * while ((line = br.readLine()) != null) { String[] result =
		 * line.split("\t"); String[] subs = result[2].split(":");
		 * 
		 * if (subs.length == 3) { String temp = subs[2]; bw.write(result[0] +
		 * "," + temp + "\n"); } } bw.close(); br.close(); }
		 */

		Jedis jedis = new Jedis("localhost");

		String csvFile = "I:/tempdata/pagelinks.csv";
		BufferedReader br = null;
		String line = "";
		// Map<String, Integer> counts = new TreeMap<String, Integer>();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] result = line.split(",");
				if (result.length == 2) {
					jedis.lpush("L:" + result[0], result[1]);
				} else if (result.length == 1) {
					jedis.lpush(result[0], "");
				}
			}
			/*
			 * int countTemp = 0; if (result.length > 1) { if
			 * (counts.get(result[1]) != null) { countTemp =
			 * counts.get(result[1]) + 1; counts.put(result[1], countTemp); }
			 * else { counts.put(result[1], countTemp); } }
			 */

			// }
			// br.close();

			// for (String key : counts.keySet()) { jedis.lpush("C:" + key, "" +
			// counts.get(key)); }

			/*
			 * Set<String> keys = jedis.keys(del + ":*"); for (String key :
			 * keys) { jedis.del(key); }
			 */
			jedis.close();
			// }

			// Jedis jedis = new Jedis("localhost");

			/*
			 * String csvFile = "I:/tempdata/disambiguation.csv"; BufferedReader
			 * br = null; String line = ""; try { br = new BufferedReader(new
			 * FileReader(csvFile)); while ((line = br.readLine()) != null) {
			 * String[] result = line.split(","); if (result.length == 2) {
			 * jedis.lpush("D:" + result[0], result[1]); } else if
			 * (result.length == 1) { jedis.lpush("D:" + result[0], ""); } }
			 * jedis.close(); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		// catch (IOException e) {
		// e.printStackTrace();
		// }
		// finally {
		// if (br != null) {
		// try {
		// br.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// }

	}
}
