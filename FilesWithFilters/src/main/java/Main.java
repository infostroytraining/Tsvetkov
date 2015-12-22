package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.filters.ExtentionFilter;
import main.java.filters.Filter;
import main.java.filters.NameFilter;

public class Main {

	public static void main(String[] args) {
		File file = new File("D:\\filter");
		Filter filter = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Искать по имени файла ? (0\\1)");
			String s = bufferedReader.readLine();
			if (s.equals("1")) {
				System.out.println("Введите имя файла");
				s = bufferedReader.readLine();
				filter = new NameFilter(filter, s);
			}
			System.out.println("Искать по расширению файла ? (0\\1)");
			s = bufferedReader.readLine();
			if (s.equals("1")) {
				System.out.println("Введите расширение");
				s = bufferedReader.readLine();
				filter = new ExtentionFilter(filter, s);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		List<File> files = Arrays.asList(file.listFiles());
		List<File> result = new ArrayList<>();
		for (File current : files) {
			if (filter.accept(current)) {
				result.add(current);
			}
		}
		if (result.isEmpty()) {
			System.out.println("Нет файлов по вашему запросу");
		} else {
			System.out.println(result);
		}

	}

}
