import core.usecases.impl.DefaultGetCommandParameter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultGetCommandParameterTest {

	private DefaultGetCommandParameter defaultGetCommandParameter;

	@Before
	public void before() {
		this.defaultGetCommandParameter = new DefaultGetCommandParameter();
	}

	@Test
	public void split_with_parameter_ok() {
		String example = "mkdir test";
		String[] result;

		result = this.defaultGetCommandParameter.execute(example);

		Assert.assertEquals(2, result.length);
		Assert.assertEquals("mkdir", result[0]);
		Assert.assertEquals("test", result[1]);

	}

	@Test
	public void split_without_parameter_ok() {
		String example = "mkdir";
		String[] result;

		result = this.defaultGetCommandParameter.execute(example);

		Assert.assertEquals(1, result.length);
		Assert.assertEquals("mkdir", result[0]);
	}
}
