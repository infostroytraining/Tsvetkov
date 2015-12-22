package main.java.filters;

import java.io.File;

import com.google.common.io.Files;

public class SizeFilter extends Filter {
	private long size;

	public SizeFilter(Filter next, long size) {
		super(next);
		this.size = size;

	}

	@Override
	public boolean currentAccept(File file) {
		if (file != null) {
			long fileSize = file.length();
			return fileSize < size;
		}
		return false;
	}
}
