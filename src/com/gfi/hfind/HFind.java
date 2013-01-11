package com.gfi.hfind;

import java.io.*;
import java.util.*;

public class HFind {

	// Options
	private boolean follow_links; 
	
	public void hfind(LinkedList<File> directory_list, int max_depth) {
		int length = directory_list.size();
		for (int i = 0; i < length; i++) {
			// Something like that!
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HFind object = new HFind();
		LinkedList<File> list = new LinkedList<File>();
		
		list.add(new File("."));
		
		object.hfind(list, 10);
		
		System.exit(0);
	}

}
