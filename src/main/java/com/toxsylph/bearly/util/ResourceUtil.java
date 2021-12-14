package com.toxsylph.bearly.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;


public class ResourceUtil {


	public static void saveDir(String text) {

		File file = new File("Bearly/");
		
		System.out.println("File Absoulute Path: " + file.getAbsolutePath());
		System.out.println("FIle Path: " + file.getPath());
	}
}
