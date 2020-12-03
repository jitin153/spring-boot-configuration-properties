package com.demo.configprops.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.configprops.config.DBConfigProps;

@RestController
public class TestController {

	private final static Logger LOG = LoggerFactory.getLogger(TestController.class);

	@Value("${app.name}")
	private String appName;

	@Value("${display.msg}")
	private String displayMsg;

	@Value("${app.user.roles}")
	private List<String> userRoles;

	@Value("#{${ws.connection.props}}")
	private Map<String, String> webServiceConnectionProperties;

	/*
	 * Assign the default value to variable if properties isn't found.
	 */
	@Value("${app.temp:Any default value}")
	private String defaultValue;

	/*
	 * Don't need to inject each and every property one by one. Group them all into
	 * a configuration class. Spring will automatically do the magic for us.
	 */
	@Autowired
	private DBConfigProps dBConfigProps;

	@GetMapping("/test")
	public String test() {
		String allPropertyValues = new StringBuilder(appName).append("\n").append(displayMsg).append("\n").append(userRoles)
				.append("\n").append(webServiceConnectionProperties).append("\n").append(defaultValue).append("\n")
				.append(dBConfigProps.getUrl()).append("\n").append(dBConfigProps.getUsername()).append("\n")
				.append(dBConfigProps.getPassword()).toString();
		LOG.info(allPropertyValues);
		return allPropertyValues;
	}
}
