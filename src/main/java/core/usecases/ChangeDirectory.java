package core.usecases;

import core.entities.DirectoryPath;

@FunctionalInterface
public interface ChangeDirectory {
	DirectoryPath execute(String[] command, DirectoryPath path);
}
