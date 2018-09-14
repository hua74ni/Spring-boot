package com.landicorp.marketing.utill;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {
	public static String getEnvInfo(String name) {
		Properties propEnv = new Properties();
		String propValue = "";
		try {
			propEnv.load(new InputStreamReader(PropertiesUtil.class
					.getResourceAsStream("/com/landicorp/config/envconfig.properties"), "UTF-8"));
			propValue = propEnv.getProperty(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return propValue;
	}
}
