package com.tsvetkov.analyzertask;

import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		FileUtils f = new FileUtils();
		System.out.println(f.readWordFromFile());
		TreeMap map = f.readWordFromFile();
		System.out.println(Arrays.toString(f.getFrequency(f.readWordFromFile())));
		f.getWordsByLength(map);
		System.out.println(f.getWordsDuplicates(map));
	}

}
