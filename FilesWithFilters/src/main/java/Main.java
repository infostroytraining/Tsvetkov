package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import main.java.filters.DateFilter;
import main.java.filters.ExtentionFilter;
import main.java.filters.Filter;
import main.java.filters.NameFilter;
import main.java.filters.SizeFilter;

public class Main {

	public static void main(String[] args) throws ParseException {
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
			System.out.println("Искать по размеру файла ? (0\\1)");
			s = bufferedReader.readLine();
			if (s.equals("1")) {
				System.out.println("Введите размер файла");
				s = bufferedReader.readLine();
				filter = new SizeFilter(filter, Long.parseLong(s));

			}
			 System.out.println("Искать по дате обновления файла ? (0\\1)");
			 s = bufferedReader.readLine();
			 if (s.equals("1")) {
			 System.out.println("Введите дату (yyyy.MM.dd)");
			 s = bufferedReader.readLine();
			 SimpleDateFormat f = new SimpleDateFormat("yyyy.MM.dd");
			 Date d = f.parse(s);
			 long milliseconds = d.getTime();
			 filter = new DateFilter(filter, milliseconds);
			 }

		} catch (IOException e) {
			e.printStackTrace();
		}
		List<File> files = Arrays.asList(file.listFiles());
		List<File> result = new ArrayList<>();
		for (File current : files) {
			if ( filter != null && filter.accept(current)) {
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
