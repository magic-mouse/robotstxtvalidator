package com.kvedro.lib.robots_txt_validator.groups;

import com.kvedro.lib.robots_txt_validator.RobotsTxtParser;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class RobotsTxtTest {

    @Test
    public void parseSimpleFile(){
        String source = "http://example.com/";

        String fileContent = "user-agent: a\n" +
                "disallow: /c\n" +
                "\n" +
                "user-agent: b\n" +
                "disallow: /d\n" +
                "\n" +
                "user-agent: e\n" +
                "user-agent: f\n" +
                "disallow: /g";

        RobotsTxt robotsTxt = RobotsTxtParser.generateRobotsTxt(source, fileContent);


        assertEquals(source, robotsTxt.getPath());

        assertSame(RobotsTxt.class, robotsTxt.getClass());

        Optional<Groups> userAgent = robotsTxt.getGroupsList().stream().filter(groups->groups.userAgent.equals("a")).findFirst();
        assertEquals(userAgent.get().userAgent, "a");
        assertArrayEquals(new String[]{"/c"}, userAgent.get().disallowed.toArray(new String[userAgent.get().disallowed.size()]));
        Optional<Groups> userAgentb = robotsTxt.getGroupsList().stream().filter(groups->groups.userAgent.equals("b")).findFirst();
        assertEquals(userAgentb.get().userAgent, "b");
        assertArrayEquals(new String[]{"/d"}, userAgentb.get().disallowed.toArray(new String[userAgent.get().disallowed.size()]));
        Optional<Groups> userAgente = robotsTxt.getGroupsList().stream().filter(groups->groups.userAgent.equals("f")).findFirst();
        assertEquals(userAgente.get().userAgent, "f");
        assertArrayEquals(new String[]{"/g"}, userAgente.get().disallowed.toArray(new String[userAgent.get().disallowed.size()]));
        Optional<Groups> userAgenth = robotsTxt.getGroupsList().stream().filter(groups->groups.userAgent.equals("h")).findFirst();
        assertFalse(userAgenth.isPresent());
            }

}