package core.entities;

import java.util.List;

public class DirectoryPath {

	private String name;
	private String fullPath;
	private DirectoryPath parent;
	private List<DirectoryPath> childDirectories;
	private List<String> files;

	private DirectoryPath(Builder builder) {
		setName(builder.name);
		setFullPath(builder.fullPath);
		setParent(builder.parent);
		setChildDirectories(builder.childDirectories);
		setFiles(builder.files);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public DirectoryPath getParent() {
		return parent;
	}

	public void setParent(DirectoryPath parent) {
		this.parent = parent;
	}

	public List<DirectoryPath> getChildDirectories() {
		return childDirectories;
	}

	public void setChildDirectories(List<DirectoryPath> childDirectories) {
		this.childDirectories = childDirectories;
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}

	public static final class Builder {
		private String name;
		private String fullPath;
		private DirectoryPath parent;
		private List<DirectoryPath> childDirectories;
		private List<String> files;

		private Builder() {
		}

		public Builder withName(String val) {
			name = val;
			return this;
		}

		public Builder withFullPath(String val) {
			fullPath = val;
			return this;
		}

		public Builder withParent(DirectoryPath val) {
			parent = val;
			return this;
		}

		public Builder withChildDirectories(List<DirectoryPath> val) {
			childDirectories = val;
			return this;
		}

		public Builder withFiles(List<String> val) {
			files = val;
			return this;
		}

		public DirectoryPath build() {
			return new DirectoryPath(this);
		}
	}
}
