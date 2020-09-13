import core.entities.DirectoryPath;
import core.usecases.impl.DefaultChangeDirectory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class DefaultChangeDirectoryTest {

	private DefaultChangeDirectory defaultChangeDirectory;

	@Before
	public void before() {
		this.defaultChangeDirectory = new DefaultChangeDirectory();
	}

	@Test
	public void change_directory_ok() {
		String[] command = new String[]{"cd", "test"};
		DirectoryPath result;

		DirectoryPath dp = DirectoryPath.newBuilder()
				.withFullPath("/")
				.withParent(null)
				.withName("/")
				.withChildDirectories(Arrays.asList(DirectoryPath.newBuilder()
						.withName("test")
						.build()))
				.withFiles(new ArrayList<>())
				.build();

		result = this.defaultChangeDirectory.execute(command, dp);

		Assert.assertEquals("test", result.getName());
	}
}
