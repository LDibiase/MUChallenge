package core.usecases.impl;

import core.usecases.GetCommandParameter;

public class DefaultGetCommandParameter implements GetCommandParameter {

	@Override
	public String[] execute(String command) {
		String[] split = command.split(" ");

		return split;
	}
}
