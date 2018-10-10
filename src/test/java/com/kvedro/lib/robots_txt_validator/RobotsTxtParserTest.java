package com.kvedro.lib.robots_txt_validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class RobotsTxtParserTest {


    @Test
    public void CheckValidPathWithoutWWW(){
        String source = "http://example.com/robots.txt";

        assertTrue(RobotsTxtParser.validateSource("http://example.com/",source));
        assertTrue(RobotsTxtParser.validateSource("http://example.com/folder/file", source));

        assertFalse(RobotsTxtParser.validateSource("http://other.example.com/", source));
        assertFalse(RobotsTxtParser.validateSource("https://example.com/", source));
        assertFalse(RobotsTxtParser.validateSource("http://example.com:8181/", source));
    }

    @Test
    public void CheckValidPathWithWWW(){
        String source = "http://www.example.com/robots.txt";

        assertTrue(RobotsTxtParser.validateSource("http://www.example.com/",source));

        assertFalse(RobotsTxtParser.validateSource("http://example.com/", source));
        assertFalse(RobotsTxtParser.validateSource("http://shop.www.example.com/", source));
        assertFalse(RobotsTxtParser.validateSource("http://www.shop.example.com/", source));
    }

    @Test
    public void CheckValidPathSubDirectory(){
        String source = "http://example.com/folder/robots.txt";


        assertFalse(RobotsTxtParser.validateSource("http://example.com/",source));
        assertFalse(RobotsTxtParser.validateSource("http://example.com/folder/file", source));

        assertFalse(RobotsTxtParser.validateSource("http://other.example.com/", source));
        assertFalse(RobotsTxtParser.validateSource("https://example.com/", source));
        assertFalse(RobotsTxtParser.validateSource("http://example.com:8181/", source));
        assertFalse(RobotsTxtParser.validateSource("http://www.example.com/",source));

        assertFalse(RobotsTxtParser.validateSource("http://example.com/", source));
        assertFalse(RobotsTxtParser.validateSource("http://shop.www.example.com/", source));
        assertFalse(RobotsTxtParser.validateSource("http://www.shop.example.com/", source));
    }

    @Test
    public void CheckValidPathFtp(){
        String source = "ftp://example.com/robots.txt";

        assertTrue(RobotsTxtParser.validateSource("ftp://example.com/",source));
        assertFalse(RobotsTxtParser.validateSource("http://example.com/",source));
    }


    @Test
    public void CheckValidPathIp(){
        String source = "http://212.96.82.21/robots.txt";

        assertTrue(RobotsTxtParser.validateSource("http://212.96.82.21/",source));
        assertFalse(RobotsTxtParser.validateSource("http://example.com/",source));
    }

    @Test
    public void CheckValidPathPort(){
        String source ="http://example.com:80/robots.txt";

        assertTrue(RobotsTxtParser.validateSource("http://example.com:80/",source));
        assertTrue(RobotsTxtParser.validateSource("http://example.com/",source));
        assertFalse(RobotsTxtParser.validateSource("http://example.com:81/",source));
    }
}