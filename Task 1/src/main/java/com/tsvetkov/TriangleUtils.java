package com.tsvetkov;

public class TriangleUtils {

	/**
	 * ������ � ������������
	 * 
	 * ��� ���� ������ ���� ��������: a, b, c. ����� ������� true, ���� ���
	 * ����� ���� ��������� ������������ � false, ���� �� �����.
	 *
	 */

	public boolean isTriangle(int a, int b, int c) throws IllegalArgumentException {
		boolean result = false;
		if ((a < 0 || c < 0 || b < 0) || (a == 0 && b == 0 && c == 0)) {
			throw new IllegalArgumentException();
		}
		if ((a < b + c) && (b < a + c) && (c < a + b)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * ��� ���� ������ ���� ������ ������������: a, b, c. ���������� ���������
	 * ������� ������������.
	 */

	public double getTriangleArea(int a, int b, int c) throws IllegalArgumentException {
		double s = 0;
		int p = 0;
		if (isTriangle(a, b, c) != false) {
			p = a + b + c;
			s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
		}
		return s;
	}
}
