package com.gfi.hfind;

import java.io.*;
import java.util.*;

public class HFind {

	// Options
	private boolean display_files       = false;
	private boolean display_directories = true;
	private int     min_depth = 1; // Included!
	private int     max_depth = 2; // Included!
	
	public void setMaxDepth(int max_depth) {
		this.max_depth = max_depth;
	}

	public int getMinDepth() {
		return min_depth;
	}

	public void setMinDepth(int min_depth) {
		this.min_depth = min_depth;
	}


	public void hfind(String directory_root) {
		LinkedList<File> directory_list = new LinkedList<File>();
		directory_list.add(new File(directory_root));
		hfind(directory_list);
	}
	
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
		
		object.hfind("/");
		
		System.exit(0);
	}
}
