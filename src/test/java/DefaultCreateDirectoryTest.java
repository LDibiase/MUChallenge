import core.entities.DirectoryPath;
import core.usecases.impl.DefaultCreateDirectory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DefaultCreateDirectoryTest {

	private DefaultCreateDirectory defaultCreateDirectory;

	@Before
	public void before() {
		this.defaultCreateDirectory = new DefaultCreateDirectory();
	}

	@Test
	public void create_directory_ok() {
		String[] command = new String[]{"mkdir", "test"};

		DirectoryPath dp = DirectoryPath.newBuilder()
				.withFullPath("/")
				.withParent(null)
				.withName("/")
				.withChildDirectories(new ArrayList<>())
				.withFiles(new ArrayList<>())
				.build();

		this.defaultCreateDirectory.execute(command, dp);

		Assert.assertEquals(1, dp.getChildDirectories().size());
		Assert.assertEquals("test", dp.getChildDirectories().get(0).getName());
	}
}
