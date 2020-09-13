package core.usecases.impl;

import core.entities.DirectoryPath;
import core.usecases.ListElements;

public class DefaultListElements implements ListElements {

	@Override
	public void execute(String[] commandAndParameter, DirectoryPath path) {
		boolean recursive = false;

		if (commandAndParameter.length > 1) {
			recursive = commandAndParameter[1].equalsIgnoreCase("-r");
		}

		if (recursive) {
			System.out.println(path.getFullPath());
			this.printDirectories(path);
			this.printFiles(path);

			path.getChildDirectories().forEach(x-> this.execute(commandAndParameter, x));
		} else {
			this.printDirectories(path);
			this.printFiles(path);
		}
	}

	private void printDirectories(DirectoryPath path) {
		path.getChildDirectories().forEach(x-> System.out.println(x.getName()));
	}

	private void printFiles(DirectoryPath path) {
		path.getFiles().forEach(System.out::println);
	}
}
