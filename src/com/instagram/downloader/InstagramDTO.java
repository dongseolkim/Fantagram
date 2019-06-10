package com.instagram.downloader;

public class InstagramDTO {
	private int no;
	private String name;
	private String link;
	private String type;
	private String version;
	private String relesed;
	private String path;

	public InstagramDTO() {
		// TODO Auto-generated constructor stub
	}

	public InstagramDTO(int no, String name, String link, String type, String version, String relesed, String path) {
		super();
		this.no = no;
		this.name = name;
		this.link = link;
		this.type = type;
		this.version = version;
		this.relesed = relesed;
		this.path = path;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRelesed() {
		return relesed;
	}

	public void setRelesed(String relesed) {
		this.relesed = relesed;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
