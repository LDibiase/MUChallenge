package core.usecases;

@FunctionalInterface
public interface GetCommandParameter {
	String[] execute(String command);
}
