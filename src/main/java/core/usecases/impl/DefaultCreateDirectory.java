package core.usecases.impl;

import core.entities.DirectoryPath;
import core.usecases.CreateDirectory;

import java.util.ArrayList;

public class DefaultCreateDirectory implements CreateDirectory {

	@Override
	public void execute(String[] command, DirectoryPath path) {
		String directory = command[1];

		path.getChildDirectories().add(DirectoryPath.newBuilder()
				.withChildDirectories(new ArrayList<>())
				.withFullPath(path.getFullPath() + directory + "/")
				.withParent(path)
				.withFiles(new ArrayList<>())
				.withName(directory)
				.build());
	}
}
