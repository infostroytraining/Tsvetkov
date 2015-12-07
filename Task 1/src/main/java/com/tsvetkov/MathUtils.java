package com.tsvetkov;

public class MathUtils {

	/**
	 * Returns the greatest common divider of given two numbers
	 * 
	 * @param firstNumber
	 *            - positive number
	 * @param secondNumber
	 *            - positive number
	 * @return greatest common divider of two numbers
	 */
	public int getGreatestCommonDivider(int firstNumber, int secondNumber) {
		if (secondNumber == 0)
			return firstNumber;
		int x = firstNumber % secondNumber;
		return getGreatestCommonDivider(secondNumber, x);

	}

	/**
	 * Returns sum of digits of the given number
	 * 
	 * @param number
	 *            - positive number
	 * @return the sum of digits of the given number
	 */
	public int getSumOfDigits(int number) {
		int result = 0;
		for (int i = number; i > 0; i /= 10) {
			result += i % 10;
		}

		return result;
	}

	/**
	 * Checks if the given number is prime or not
	 * 
	 * @param number
	 * @return true - if number is prime, if not return false
	 */
	public boolean isPrime(int number) {
		boolean result = true;
		if (number < 2) {
			result = false;
		} else {
			for (int i = 2; i <= Math.sqrt(number); i++) {
				if (number % i == 0) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Returns sum of row: 1! - 2! + 3! - 4! + 5! - ... + n!
	 * 
	 * @param n
	 *            - positive number
	 */
	public int getSumOfRow(int n) {
		int fact = 1;
		int sum = 0;
		int sign = 1; 
		for (int i = 1; i <= n; i++) {
			fact*=i;
			sum+=fact*sign;
			sign*=-1;
			
		}
		return sum;
	}

	/**
	 * Returns Fibonacci series of a specified length
	 * 
	 * @param length
	 *            - the length of the Fibonacci series
	 * @return array filled with Fibonacci series
	 */
	public int[] getFibonacciSeries(int length) {
		int[] array = new int[length];
		for (int i = 0; i < length; i++) {
			if (i < 2) {
				array[i] = 1;
			} else {
				array[i] = array[i - 1] + array[i - 2];
			}
		}
		return array;

	}

	/**
	 * Returns array with prime numbers
	 * 
	 * @param length
	 *            - the length of prime numbers series
	 * @return array filled with prime numbers
	 */
	public int[] getPrimeSeries(int length) {
		int[] array = new int[length];
		int number = 2;
		for (int i = 0; i < length; i++) {
			while (!isPrime(number)) {
				number++;
			}
			array[i] = number;
			number++;
		}
		return array;
	}
}
