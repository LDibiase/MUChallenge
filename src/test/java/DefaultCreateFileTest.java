import core.entities.DirectoryPath;
import core.usecases.impl.DefaultCreateFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DefaultCreateFileTest {

	private DefaultCreateFile defaultCreateFile;

	@Before
	public void before() {
		this.defaultCreateFile = new DefaultCreateFile();
	}

	@Test
	public void create_file_ok() {
		String[] command = new String[]{"touch", "test.js"};

		DirectoryPath dp = DirectoryPath.newBuilder()
				.withFullPath("/")
				.withParent(null)
				.withName("/")
				.withChildDirectories(new ArrayList<>())
				.withFiles(new ArrayList<>())
				.build();

		this.defaultCreateFile.execute(command, dp);

		Assert.assertEquals(1, dp.getFiles().size());
		Assert.assertEquals("test.js", dp.getFiles().get(0));
	}
}
