package com.gfi.hfind;

import java.io.*;
import java.util.*;

public class HFind {

	// Options
	private boolean display_files       = false;
	private boolean display_directories = true;
	private int     min_depth = 1; // Value is included!
	private int     max_depth = 2; // Value is included!
	
	public void setDisplayFiles(boolean display) {
		display_files = display;
	}
	
	public void setDisplayDirectories(boolean display) {
		display_directories = display;
	}
	
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

	public void hfind(String directory_root) {
		LinkedList<File> directory_list = new LinkedList<File>();
		directory_list.add(new File(directory_root));
		hfind(directory_list);
	}

	/** Display usage message 
	 * Another message
	 */
	public static void displayUsage() {
		System.err.println("Usage: hfind [--min-depth x] [--max-depth y] <directories...>");
		System.err.println("Other options: ");
		System.err.println("  --display-files");
		System.err.println("  --do-not-display-files");
		System.err.println("  --display-directories");
		System.err.println("  --do-not-display-directories");
		System.err.println("  --level l");
		System.err.println("  --help");
	}
	
	public void hfind(LinkedList<File> directory_list) {
		File current;
		int depth = 0;
		
		// Warn if finding regular files in the directory list...
		for (int i = 0; i < directory_list.size(); i++) {
			if (!directory_list.get(i).isDirectory()) {
				System.err.println("Warning! File \"" + directory_list.get(i) + "\" is not a directory. Continuing...");
			}
		}
		
		// Go down into hierarchy:
		// Until there are no directories left/max depth reached...
		// Using a FIFO to go through hierarchy: expecting that the FIFO will be empty at some point
		while (directory_list.size() != 0 && depth <= max_depth) {
			int length = directory_list.size();
			for (int i = 0; i < length; i++) {
				current = directory_list.poll();
				if (display_directories && current.isDirectory() && depth >= min_depth) {
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
				} else if (args[i].equals("--level")) {
					assert(Integer.getInteger(args[i + 1]) >= 0); // We need to be careful!
					object.setMinDepth(Integer.getInteger(args[i + 1]));
					object.setMaxDepth(Integer.getInteger(args[i + 1]));
					i++;
				} else if (args[i].equals("--display-files")) {
					object.setDisplayFiles(true);
				} else if (args[i].equals("--do-not-display-files")) {
					object.setDisplayFiles(false);
				} else if (args[i].equals("--display-directories")) {
					object.setDisplayDirectories(true);
				} else if (args[i].equals("--do-not-display-directories")) {
					object.setDisplayDirectories(false);
				} else if (args[i].equals("--help") || args[i].equals("-h")) {
					displayUsage();
					System.exit(1);
				} else {
					System.err.println("Unknown argument \"" + args[i] + "\"...");
					displayUsage();
					System.exit(1);
				}
				i++;
			}
			
			// Assuming all remaining arguments are directories to go through...
			while (i < args.length) {
				list.add(new File(args[i]));
				i++;
			}
		} else {
			// Assuming no argument means from the current directory
			list.add(new File("."));
		}
		
		//object.hfind(list);
		object.hfind("/");
		
		System.exit(0);
	}
}
