package ru.mipt.hsse.course1.basic.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class IOOperations {
	private static final Logger logger = Logger.getLogger(Streams.class.getSimpleName());

	public static void readFileStream() throws IOException {
		InputStream is = new FileInputStream("test.txt");
		var b = is.read();
		logger.info("Read from stream: " + b);
		is.close();
	}

	public static void fileReader() throws IOException {
		var reader = new FileReader("test.txt");
//		var reader = new FileReader("test.txt", Charset.forName("UTF-8"));
		char[] array = new char[100];
		var b = reader.read(array);
		logger.info("Read from reader: " + array);
		logger.info("Read from reader: " + new String(array));
		reader.close();
	}

	public static void printFirstLine(BufferedReader reader) throws IOException {
		var line = reader.readLine();
		logger.info("Read from buffered reader: " + line);
	}

	public static void bufferedReader() throws IOException {
		var reader = new BufferedReader(new FileReader("test.txt"));
		try {
			printFirstLine(reader);
		} finally {
			reader.close();
		}

	}

	public static void tryWithResource() throws IOException {
		try (var reader = new BufferedReader(new FileReader("test.txt"))) {
			var line = reader.readLine();
			logger.info("Read from buffered reader: " + line);
		}
	}


	public static void main(String[] args) throws IOException {
		tryWithResource();
	}
}
