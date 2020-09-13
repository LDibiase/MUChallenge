import core.entities.DirectoryPath;
import core.usecases.impl.DefaultChangeDirectory;
import core.usecases.impl.DefaultCreateDirectory;
import core.usecases.impl.DefaultCreateFile;
import core.usecases.impl.DefaultGetCommandParameter;
import core.usecases.impl.DefaultListElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main implements Runnable {
	private static String command;
	private static final String EXIT = "quit";
	private static final Integer MAX_LENGTH = 100;
	private static final String PARENT_PARAMETER = "..";
	private static DirectoryPath root;
	private static Map<String, String> commands;

	private static DefaultGetCommandParameter defaultGetCommandParameter = null;
	private static DefaultListElements defaultListElements = null;
	private static DefaultCreateDirectory defaultCreateDirectory = null;
	private static DefaultChangeDirectory defaultChangeDirectory = null;
	private static DefaultCreateFile defaultCreateFile = null;

	public Main(DefaultGetCommandParameter defaultGetCommandParameter, DefaultListElements defaultListElements,
	            DefaultCreateDirectory defaultCreateDirectory, DefaultChangeDirectory defaultChangeDirectory,
	            DefaultCreateFile defaultCreateFile) {
		this.defaultGetCommandParameter = defaultGetCommandParameter;
		this.defaultListElements = defaultListElements;
		this.defaultCreateDirectory = defaultCreateDirectory;
		this.defaultChangeDirectory = defaultChangeDirectory;
		this.defaultCreateFile = defaultCreateFile;
	}

	public static void main(String[] args) {
		Main app = new Main(new DefaultGetCommandParameter(), new DefaultListElements(), new DefaultCreateDirectory(),
				new DefaultChangeDirectory(), new DefaultCreateFile());
		app.run();
	}

	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		this.fillCommands();
		this.createRootPath();

		do {
			System.out.println("Waiting command");
			command = scanner.nextLine();

			String[] commandAndParameter = defaultGetCommandParameter.execute(command);

			if (!commands.containsKey(commandAndParameter[0])) {
				System.out.println("Unrecognized command");
			}

			this.processCommand(commandAndParameter);

		} while (!command.equalsIgnoreCase(EXIT));
	}

	private void fillCommands() {
		commands = new HashMap<>();
		commands.put("pwd", "Prints full path");
		commands.put("ls", "List all in curren diretory");
		commands.put("mkdir", "Creates directory");
		commands.put("cd", "Change to sub-directory");
		commands.put("touch", "Creates a file");

	}

	private void createRootPath() {
		root = DirectoryPath.newBuilder()
				.withName("/")
				.withFullPath("/")
				.withParent(null)
				.withChildDirectories(new ArrayList<>())
				.withFiles(new ArrayList<>())
				.build();
	}

	private void processCommand(String[] commandAndParameter) {

		switch (commandAndParameter[0]) {
			case "pwd":
				System.out.println(root.getFullPath());
				break;
			case "ls":
				defaultListElements.execute(commandAndParameter, root);
				break;
			case "mkdir":
				if (this.isValidParameter(commandAndParameter[1]) && !this.directoryExists(commandAndParameter[1])) {
					defaultCreateDirectory.execute(commandAndParameter, root);
				} else {
					System.out.println("Directory already exists");
				}
				break;
			case "cd":
				if (this.isValidParameter(commandAndParameter[1]) && this.directoryExists(commandAndParameter[1])) {
					root = defaultChangeDirectory.execute(commandAndParameter, root);
				} else {
					System.out.println("Directory not found");
				}
				break;
			case "touch":
				if (this.isValidParameter(commandAndParameter[1])) {
					defaultCreateFile.execute(commandAndParameter, root);
				}

		}
	}

	private boolean isValidParameter(String parameter) {
		return parameter.length() <= MAX_LENGTH || parameter.equalsIgnoreCase(PARENT_PARAMETER);
	}

	private boolean directoryExists(String directory) {
		return (root.getChildDirectories().stream().anyMatch(x-> x.getName().equalsIgnoreCase(directory)) || directory.equalsIgnoreCase(PARENT_PARAMETER));
	}
}
