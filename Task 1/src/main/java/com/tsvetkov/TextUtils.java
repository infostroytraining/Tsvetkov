package com.tsvetkov;

import java.util.Iterator;

/**
 * �������� ����������
 * 
 * ��������� ���� �� �������� �������� �� ����������. ��������, �� ����� �����
 * ����������� � ��������� �����. ��� �� ������ ������ ����� ������ ����������.
 * 
 * ���� ������: ��������� �� ������.
 * 
 * ��� ����� �������:
 * 
 * 1. ������ ����� ����������� ������ ���������� � ��������� �����. 2. �����
 * ������ ���������� (����� � �������) ������ ���� �������.
 */
public class TextUtils {

	public String correctText(String text) {
		String[] s = text.split("\\.");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length; i++) {
			sb.append(Character.toUpperCase(s[i].charAt(0)));
			for (int j = 1; j < s[i].length(); j++) {
				sb = sb.append(s[i].charAt(j));
				System.out.println(s[i].charAt(j));
				if (s[i].charAt(j) == ',' || s[i].charAt(j) == '.') {
					if (s[i].charAt(j + 1) != ' ') {
						sb.append(" ");
					}
				}
			}
			sb.append(". ");

		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();

	}
}