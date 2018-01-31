package com.shan.gradle.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by shanlehong on 2018/1/31.
 */
@EnableAutoConfiguration
@SpringBootApplication
public class StudyBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyBootApplication.class,args);
    }
}
