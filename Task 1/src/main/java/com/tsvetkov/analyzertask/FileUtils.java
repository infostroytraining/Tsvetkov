package com.tsvetkov.analyzertask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class FileUtils {
	public String path;

	public FileUtils(String path) {
		this.path = path;
	}

	public TreeMap readWordFromFile() throws FileNotFoundException {
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		String[] result;
		File file = new File(path);
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while (br.ready()) {
				sb.append(br.readLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = sb.toString().split(" ");
		for (String string : result) {
			if (map.containsKey(string)) {
				map.put(string, map.get(string) + 1);
			} else {
				map.put(string, 1);
			}

		}
		return map;
	}

	public TreeMap sortByValue(Map unsortdeMap) {
		TreeMap sortedMap = new TreeMap(new ValueComparator(unsortdeMap));
		sortedMap.putAll(unsortdeMap);
		return sortedMap;
	}

	public String[] getFrequency(TreeMap map) {
		map = sortByValue(map);
		String[] str = new String[2];
		int i = 0;
		Iterator iterator = map.keySet().iterator();
		while (i < 2) {
			String key = (String) iterator.next();
			str[i] = key + " " + map.get(key);
			i++;
		}
		Arrays.sort(str);
		return str;

	}

	public List getWordsByLength(TreeMap map) {
		List list = new ArrayList<>(map.keySet());
		Collections.sort(list, new LengthComparator());
		return list;

	}

	public List<String> getWordsDuplicates(TreeMap map) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		List list = new ArrayList<>();
		Iterator iterator = map.keySet().iterator();
		while (i < 3 && iterator.hasNext()) {
			String key = (String) iterator.next();
			if ((int) map.get(key) > 1) {
				list.add(sb.append(key.toUpperCase()).reverse().toString());
				;
				i++;
			}
			sb.setLength(0);
		}
		return list;

	}

}
