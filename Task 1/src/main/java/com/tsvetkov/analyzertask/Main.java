package com.tsvetkov.analyzertask;

import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		long time = System.currentTimeMillis();
		FileUtils f = new FileUtils("src/main/resources/file/read.txt");
		String[] freq = f.getFrequency(f.readWordFromFile());
		for (String string : freq) {
			System.out.println(string);
		}
		System.out.println(System.currentTimeMillis()-time);
		
		time = System.currentTimeMillis();
		List<String> list = f.getWordsDuplicates(f.readWordFromFile());
		for (String string : list) {
			System.out.println(string);
		}
		System.out.println(System.currentTimeMillis()-time);
		
		time = System.currentTimeMillis();
		List<String> listWordLenght = f.getWordsByLength(f.readWordFromFile());
		for (String string : listWordLenght) {
			System.out.println(string + " " + string.length());
		}
		System.out.println(System.currentTimeMillis()-time);
	}

}
