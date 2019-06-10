package com.instagram.downloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class InstagramDownloader {

    private static Document page;
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";

	// jsoup 파싱을 통한 mediaType 체크, Type(video, image)에 따른 URL 파싱
	public static String getDownloadUrl(String url, String path, String fileName) {
		String downloadUrl = "";

		InstagramUrlCheck.validateURL(url);
		try {
			page = Jsoup.connect(url).userAgent(USER_AGENT).get();
			String mediaType = page.select("meta[name=medium]").first().attr("content");

			switch (mediaType) {
			case "video":
				downloadUrl = page.select("meta[property=og:video]").first().attr("content");
				break;
			case "image":
				downloadUrl = page.select("meta[property=og:image]").first().attr("content");
				break;
			default:
				downloadUrl = "No media file found.";
				break;
			}
			download(downloadUrl, path, fileName);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return downloadUrl;
	}

	// 전달받은 URL을 통한 File Download 메소드

	private static void download(String url, String path, String fileName) {
		String[] tempName = url.split("/");
		String filename = tempName[tempName.length - 1].split("[?]")[0];
		filename = fileName + filename.substring(filename.lastIndexOf("."));

		try (InputStream inputStream = URI.create(url).toURL().openStream()) {
			HttpURLConnection conn = (HttpURLConnection) URI.create(url).toURL().openConnection();

			Path targetPath = new File(path + File.separator + filename).toPath();
			Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);

			int BYTES_PER_KB = 1024;
			double fileSize = ((double) conn.getContentLength() / BYTES_PER_KB);

			System.out.println(String.format("Location: %s", targetPath));
			System.out.println(String.format("Name: %s", filename));
			System.out.println(String.format("Size: %.2f kb", fileSize));
			System.out.println(String.format("Type: %s", InstagramUrlCheck.mediaType(filename)));

		} catch (HttpStatusException e) {
			System.out.println("[에러] HTTP Connection Error: " + e.getMessage());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
