package com.tsvetkov.analyzertask;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator {

	Map map;
	public ValueComparator(Map map) {
		this.map=map;
	}



	@Override
	public int compare(Object o1, Object o2) {
		Comparable valueA = (Comparable) map.get(o2);
		Comparable valueB = (Comparable) map.get(o1);
		return valueA.compareTo(valueB);
	}

}
