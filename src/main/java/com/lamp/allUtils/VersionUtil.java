package com.lamp.allUtils;

import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

public class VersionUtil {
    public static void main(String[] args) {
        String version =SpringVersion.getVersion();//5.2.3.RELESE
        String version1 = SpringBootVersion.getVersion();//2.2.4.RELESE

        System.out.println("----------"+version);
        System.out.println("----------"+version1);

    }
}
