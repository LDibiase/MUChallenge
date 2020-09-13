package core.usecases.impl;

import core.entities.DirectoryPath;
import core.usecases.CreateFile;

public class DefaultCreateFile implements CreateFile {

	@Override
	public void execute(String[] command, DirectoryPath path) {
		path.getFiles().add(command[1]);
	}
}
