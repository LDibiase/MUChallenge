package functional;

import core.entities.DirectoryPath;
import core.usecases.impl.DefaultChangeDirectory;
import core.usecases.impl.DefaultCreateDirectory;
import core.usecases.impl.DefaultCreateFile;
import core.usecases.impl.DefaultGetCommandParameter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class FunctionalTest {

	private DefaultGetCommandParameter defaultGetCommandParameter;
	private DefaultCreateFile defaultCreateFile;
	private DefaultChangeDirectory defaultChangeDirectory;
	private DefaultCreateDirectory defaultCreateDirectory;

	@Before
	public void before() {
		this.defaultCreateFile = new DefaultCreateFile();
		this.defaultGetCommandParameter = new DefaultGetCommandParameter();
		this.defaultChangeDirectory = new DefaultChangeDirectory();
		this.defaultCreateDirectory = new DefaultCreateDirectory();
	}

	@Test
	public void scenary_ok() {
		String[] createFileCommand;
		String[] createDirCommand;
		String[] changeDirectoryCommand;

		createFileCommand = this.defaultGetCommandParameter.execute("touch test.js");
		createDirCommand = this.defaultGetCommandParameter.execute("mkdir test");
		changeDirectoryCommand = this.defaultGetCommandParameter.execute("cd test");

		DirectoryPath dp = DirectoryPath.newBuilder()
				.withFullPath("/")
				.withParent(null)
				.withName("/")
				.withChildDirectories(new ArrayList<>())
				.withFiles(new ArrayList<>())
				.build();

		this.defaultCreateFile.execute(createFileCommand, dp);
		this.defaultCreateDirectory.execute(createDirCommand, dp);
		dp = this.defaultChangeDirectory.execute(changeDirectoryCommand, dp);

		Assert.assertEquals("test", dp.getName());
		Assert.assertEquals("/", dp.getParent().getName());
		Assert.assertEquals("test.js", dp.getParent().getFiles().get(0));
	}
}
