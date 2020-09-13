package core.usecases;

import core.entities.DirectoryPath;

@FunctionalInterface
public interface CreateDirectory {
	void execute(String[] command, DirectoryPath path);
}
