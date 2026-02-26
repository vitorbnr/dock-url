package com.vitorbnr.dock_url;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DockUrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockUrlApplication.class, args);
	}

}
