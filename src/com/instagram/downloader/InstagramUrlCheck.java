package com.instagram.downloader;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class InstagramUrlCheck {

	public static String validateURL(String url) {
		if (url.length() <= 0) {
			return "링크(link)를 입력하시기 바랍니다.";
//			throw new IllegalArgumentException("URL을 입력해 주시기 바랍니다.");
		} else if (url.startsWith("www.") || url.startsWith("instagram.com")) {
			return "잘못된 URL 입니다. https:// 로 시작해야 합니다.";
//			throw new IllegalArgumentException("https:// 로 시작해야 합니다.");
		} else if (!(url.startsWith("https://") || url.startsWith("http://"))) {
			return "잘못된 URL 입니다. https:// 로 시작해야 합니다.";
//			throw new IllegalArgumentException("잘못된 URL 입니다.");
		} else if (!url.contains("instagram.com/p/") && !url.contains("instagram.com/tv/")) {
			return "제공하지 않는 URL 입니다.";
//			throw new IllegalArgumentException("잘못된 URL 입니다.");
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

	// 더 추가하자..
	public static String validatePath(String path) {
		if (path.length() <= 0) {
			return "경로를 입력하시기 바랍니다.";
//			throw new IllegalArgumentException("URL을 입력해 주시기 바랍니다.");
		} else {
			return "success";
		}
	}
}
