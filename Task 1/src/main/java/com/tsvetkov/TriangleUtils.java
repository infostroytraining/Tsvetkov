package com.tsvetkov;

public class TriangleUtils {

	/**
	 * Задача о треугольнике
	 * 
	 * Вам даны длинны трех отрезков: a, b, c. Нужно вернуть true, если они
	 * могут быть сторонами треугольника и false, если не могут.
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
	 * Вам даны длинны трех сторон треугольника: a, b, c. Необходимо вычислить
	 * площадь треугольника.
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
