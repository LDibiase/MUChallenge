package core.usecases.impl;

import core.entities.DirectoryPath;
import core.usecases.ChangeDirectory;

public class DefaultChangeDirectory implements ChangeDirectory {

	@Override
	public DirectoryPath execute(String[] command, DirectoryPath path) {
		String directory = command[1];

		if (directory.equalsIgnoreCase("..")) {
			path = path.getParent();
		} else {
			path = path.getChildDirectories().stream()
					.filter(x -> directory.equals(x.getName()))
					.findAny()
					.orElse(null);
		}

		return path;
	}
}
