package com.bridgelabz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.bridgelabz.zuulfilter.SimpleFilter;

/**
 * Purpose : Main class for ToDo Zuul application.
 * 
 * @author Sameer Saurabh
 * @version 1.0
 * @Since 11/08/2018
 */
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class ToDoZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoZuulApplication.class, args);
	}

	@Bean
	public SimpleFilter simpleFilter() {
		return new SimpleFilter();
	}
}
