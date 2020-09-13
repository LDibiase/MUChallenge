package core.usecases;

import core.entities.DirectoryPath;

@FunctionalInterface
public interface ListElements {
	void execute(String[] commandAndParameter, DirectoryPath path);
}
