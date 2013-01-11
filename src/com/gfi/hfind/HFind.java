package com.gfi.hfind;

import java.io.*;
import java.util.*;

public class HFind {

	// Options
	//private boolean follow_links;
	private boolean display_files       = false;
	private boolean display_directories = true;
	private int     min_depth = 0; // Not implemented yet!
	private int     max_depth = 0; // Not implemented yet!
	
	public int getMaxDepth() {
		return max_depth;
	}

	public void setMaxDepth(int max_depth) {
		this.max_depth = max_depth;
	}

	public int getMinDepth() {
		return min_depth;
	}

	public void setMinDepth(int min_depth) {
		this.min_depth = min_depth;
	}

	public void hfind(LinkedList<File> directory_list, int max_depth) {
		File current;
		
		while (directory_list.size() != 0 && max_depth >= 0) {
			int length = directory_list.size();
			for (int i = 0; i < length; i++) {
				// Something like that!
				current = directory_list.poll();
				if (display_directories) {
					System.out.println(current);
				}
				if (max_depth >= 1) {
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
			max_depth--;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i;
		HFind object = new HFind();
		LinkedList<File> list = new LinkedList<File>();
		
		if (args.length > 0) {
			i = 0;
			while (i < (args.length - 1)) {
				if (args[i].equals("--max-depth")) {
					object.setMaxDepth(Integer.getInteger(args[i + 1]));
					i++;
				} else if (args[i].equals("--min-depth")) {
					object.setMinDepth(Integer.getInteger(args[i + 1]));
					i++;
				} else {
					System.err.println("Unknown argument \"" + args[i] + "\"...");
					System.exit(1);
				}
				i++;
			}
			list.add(new File(args[i]));
		} else {
			//list.add(new File("."));
			list.add(new File("/"));
		}
		
		object.hfind(list, 2);
		
		System.exit(0);
	}
}

