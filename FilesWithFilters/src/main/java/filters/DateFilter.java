package main.java.filters;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.io.Files;

public class DateFilter extends Filter {

	private long date;

	public DateFilter(Filter next, long date) {
		super(next);
		this.date = date;
	}

	@Override
	public boolean currentAccept(File file) {
		if (file != null) {

			return date > file.lastModified();
		}
		return false;
	}

}
