package core.usecases;

import core.entities.DirectoryPath;

@FunctionalInterface
public interface CreateFile {
	void execute(String[] command, DirectoryPath path);
}
