package com.rra.base.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHelper {

	private static Logger _logger = LoggerFactory.getLogger(FileHelper.class);

	public synchronized static void addData(String content, String fileName) throws Exception {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(fileName, true);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.newLine();
		} catch (Exception e) {
			_logger.error("appendData error ", e);
			throw e;
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					_logger.error("bw error ", e);
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					_logger.error("fw error ", e);
				}
			}
		}
	}

	public synchronized static List<File> getAllFilesInThefolder(String folder) throws IOException {
		return Files.walk(Paths.get(folder)).filter(Files::isRegularFile).map(Path::toFile)
				.collect(Collectors.toList());
	}
	
	public static String getData(File f) throws IOException {

		FileReader fw = null;
		BufferedReader bw = null;
		StringBuilder sb = new StringBuilder();
		try {
			fw = new FileReader(f);
			bw = new BufferedReader(fw);
			String line = null;
			while ((line = bw.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (Exception e) {
			_logger.error("getData error ", e);
			throw e;
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					_logger.error("bw error ", e);
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					_logger.error("fw error ", e);
				}
			}
		}
		return sb.toString();
	
	}

	public synchronized static String getData(String fileName) throws Exception {
		FileReader fw = null;
		BufferedReader bw = null;
		StringBuilder sb = new StringBuilder();
		try {
			fw = new FileReader(fileName);
			bw = new BufferedReader(fw);
			String line = null;
			while ((line = bw.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (Exception e) {
			_logger.error("getData error ", e);
			throw e;
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					_logger.error("bw error ", e);
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					_logger.error("fw error ", e);
				}
			}
		}
		return sb.toString();
	}

}
