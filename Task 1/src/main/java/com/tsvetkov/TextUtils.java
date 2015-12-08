package com.tsvetkov;

import java.util.Comparator;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Проверка орфографии
 * 
 * Некоторые люди не обращают внимание на орфографию. Например, не пишут новое
 * предложение с заглавной буквы. Или не ставят пробел после знаков препинания.
 * 
 * Ваша задача: исправить их ошибки.
 * 
 * Что нужно сделать:
 * 
 * 1. Каждое новое предложение должно начинаться с заглавной буквы. 2. После
 * знаков препинания (точка и запятая) должны быть пробелы.
 */
public class TextUtils {
	public static void main(String[] arg) {
		TextUtils t = new TextUtils();
		System.out.println(t.correctText("l"));
	}

	public String correctText(String text) {
		/*
		 * String[] s = text.split("\\."); StringBuilder sb = new
		 * StringBuilder(); for (int i = 0; i < s.length; i++) {
		 * sb.append(Character.toUpperCase(s[i].charAt(0))); for (int j = 1; j <
		 * s[i].length(); j++) { sb = sb.append(s[i].charAt(j));
		 * System.out.println(s[i].charAt(j)); if (s[i].charAt(j) == ',' ||
		 * s[i].charAt(j) == '.') { if (s[i].charAt(j + 1) != ' ') { sb.append(
		 * " "); } } } sb.append(". ");
		 * 
		 * } sb.deleteCharAt(sb.length()-1); return sb.toString();
		 */
		if (text.charAt(text.length() - 1) != '.') {
			text = text.concat(".");
		}
		Matcher m = Pattern.compile("(?:^|[.!?])\\s*(\\W)").matcher(text);
		StringBuilder sb = new StringBuilder();
		int last = 0;
		while (m.find()) {
			sb.append(text.substring(last, m.start()));
			sb.append(m.group().toUpperCase());
			last = m.end();
		}
		sb.append(text.substring(last));
		text = sb.toString();
		text = text.replaceAll(",(?!\\s)", ", ");
		text = text.replaceAll("\\.(?!\\s)", ". ");
		if (text.charAt(text.length() - 1) == ' ') {
			text = text.substring(0, text.length() - 1);
		}
		return text;
	}
}