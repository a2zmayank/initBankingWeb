package com.initBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties pro;

	public ReadConfig() {

		File src = new File("./Configrations/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();

			pro.load(fis);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getBaseURL() {
		String url = pro.getProperty("baseURL");
		return url;

	}

	public String getUserName() {
		String username = pro.getProperty("username");
		return username;

	}

	public String getPassworg() {
		String passworg = pro.getProperty("password");
		return passworg;

	}

	public String getChromeDriverPath() {
		String chromePath = pro.getProperty("chromepath");
		return chromePath;

	}

}
