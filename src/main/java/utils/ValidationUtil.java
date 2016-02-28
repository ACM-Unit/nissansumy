package utils;

import database.DataService;

import java.util.List;

/**
 * Created by dell on 24.08.2015.
 */
public class ValidationUtil {

    public static boolean isValidUrlToOpenMeeting(String urlToOpenMeeting, DataService service) {
        List<String> validUrls = service.getAllUrlToOpenMeetingByCity();
        for (String u : validUrls) {
            if (u.equalsIgnoreCase(urlToOpenMeeting))
                return true;
        }
        return false;
    }

    public static boolean isValidUrlToNews(String urlToNews, DataService service) {
        List<String> validUrls = service.getAllUrlToNewsByCity();
        for (String u : validUrls) {
            if (u.equalsIgnoreCase(urlToNews))
                return true;
        }
        return false;
    }

}
