package com.gfi.hfind;

import java.io.*;
import java.util.*;

public class HFind {

	// Options
	//private boolean follow_links;
	private boolean display_files       = false;
	private boolean display_directories = true;
	private int     min_depth = 1; // Not implemented yet!
	private int     max_depth = 2;
	
	public void hfind(LinkedList<File> directory_list) {
		File current;
		int depth = 0;
		
		while (directory_list.size() != 0 && depth <= max_depth) {
			int length = directory_list.size();
			for (int i = 0; i < length; i++) {
				// Something like that!
				current = directory_list.poll();
				if (display_directories && depth >= min_depth) {
					System.out.println(current);
				}
				if (depth < max_depth) {
					File[] children = current.listFiles();
					if (children != null) {
						for (int j = 0; j < children.length; j++) {
							if (children[j].isDirectory()) {
								directory_list.addLast(children[j]);
							} else if (display_files && children[j].isFile()) {
								System.out.println(children[j]);
							}
						}
					}
				}
			}
			depth++;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HFind object = new HFind();
		LinkedList<File> list = new LinkedList<File>();
		
		list.add(new File("/"));
		
		object.hfind(list);
		
		System.exit(0);
	}
}

