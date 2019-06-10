package com.instagram.downloader;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class InstagramUrlCheck {

	public static String validateURL(String url) {
		if (url.length() <= 0) {
			return "��ũ(link)�� �Է��Ͻñ� �ٶ��ϴ�.";
//			throw new IllegalArgumentException("URL�� �Է��� �ֽñ� �ٶ��ϴ�.");
		} else if (url.startsWith("www.") || url.startsWith("instagram.com")) {
			return "�߸��� URL �Դϴ�. https:// �� �����ؾ� �մϴ�.";
//			throw new IllegalArgumentException("https:// �� �����ؾ� �մϴ�.");
		} else if (!(url.startsWith("https://") || url.startsWith("http://"))) {
			return "�߸��� URL �Դϴ�. https:// �� �����ؾ� �մϴ�.";
//			throw new IllegalArgumentException("�߸��� URL �Դϴ�.");
		} else if (!url.contains("instagram.com/p/") && !url.contains("instagram.com/tv/")) {
			return "�������� �ʴ� URL �Դϴ�.";
//			throw new IllegalArgumentException("�߸��� URL �Դϴ�.");
		} 
		else {
			return "success";
		}
	}

	public static String mediaType(String fileName) {
		if (fileName.endsWith(".mp4")) {
			return "video";
		} else if (fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
			return "image";
		} else {
			return "media type not found";
		}
	}

	public static String mediaExtension(String url) {
		InstagramUrlCheck.validateURL(url);
		Document page;
		String extension = null;
		try {
			page = Jsoup.connect(url).get();
			String mediaType = page.select("meta[name=medium]").first().attr("content");

			if (mediaType.equals("video")) {
				extension = "mp4";
			} else if (mediaType.equals("image")) {
				extension = "jpg";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return extension;
	}

	// �� �߰�����..
	public static String validatePath(String path) {
		if (path.length() <= 0) {
			return "��θ� �Է��Ͻñ� �ٶ��ϴ�.";
//			throw new IllegalArgumentException("URL�� �Է��� �ֽñ� �ٶ��ϴ�.");
		} else {
			return "success";
		}
	}
}
