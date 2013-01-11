package com.gfi.hfind;

import java.io.*;
import java.util.*;

public class HFind {

	// Options
	private boolean follow_links; 
	
	public void hfind(LinkedList<File> directory_list, int max_depth) {
		File current;
		int length = directory_list.size();
		for (int i = 0; i < length; i++) {
			// Something like that!
			current = directory_list.poll();
			File[] children = current.listFiles();
			for (int j = 0; j < children.length; j++) {
				if (children[j].isDirectory()) {
					directory_list.addLast(children[j]);
				}
			}
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

