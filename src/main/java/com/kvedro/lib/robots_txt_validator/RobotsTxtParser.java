package com.kvedro.lib.robots_txt_validator;

import com.kvedro.lib.robots_txt_validator.groups.Groups;
import com.kvedro.lib.robots_txt_validator.groups.RobotsTxt;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RobotsTxtParser {

    public static RobotsTxt generateRobotsTxt(String source, String fileContent) {
        RobotsTxt robotsTxt = new RobotsTxt();
        robotsTxt.setPath(source);

        String[] userAgentGroups = fileContent.split("(?=user-agent:)");

        Arrays.asList(userAgentGroups).forEach(parseString -> {
            String[] rows = parseString.split("\n");
            Groups groups = new Groups();
            List<String> disallow = new ArrayList<>();
            List<String> allow = new ArrayList<>();

            Arrays.asList(rows).forEach(
                    row -> {
                        String[] rowSegment = row.split(" ");
                        switch(rowSegment[0]){
                            case "user-agent:":
                                groups.setUserAgent(rowSegment[1]);
                                break;
                            case "disallow:":
                                disallow.add(rowSegment[1]);
                                break;
                            case "allow:":
                                allow.add(rowSegment[1]);
                                break;
                        }
                    }
            );
            groups.setAllowed(allow);
            groups.setDisallowed(disallow);
            robotsTxt.appendGroup(groups);
        } );

        return robotsTxt;
    }


    public static boolean validateSource(String path, String source) {
        try{
            URL sourceUrl = new URL(source);
            URL pathUrl = new URL(path);

            if(StringUtils.countMatches(sourceUrl.getPath(),"/")>1){
                return false;
            }

            if(isEmptyPortEquals80(sourceUrl, pathUrl)){

               boolean hostMatch = sourceUrl.getHost().equals(pathUrl.getHost());
               boolean protocolMatch = sourceUrl.getProtocol().equals(pathUrl.getProtocol());

               return hostMatch && protocolMatch;
            }

            return sourceUrl.getAuthority().equals(pathUrl.getAuthority())
                    && sourceUrl.getProtocol().equals(pathUrl.getProtocol());

        }catch(MalformedURLException e){
            e.printStackTrace();
            return false;
        }

    }

    private static boolean isEmptyPortEquals80(URL sourceUrl, URL pathUrl) {
        return (sourceUrl.getPort() == -1 && pathUrl.getPort() == 80)||(sourceUrl.getPort() == 80 && pathUrl.getPort() == -1);
    }
}
