package utils;

import constants.Constants;
import database.DataService;
import exception.UrlException;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;

/**
 * Created by dell on 15.08.2015.
 */
public class UrlUtils {

    public static String getFormOfStudyUrl(HttpServletRequest req) throws UrlException {
        String cityUrl = getCityUrl(req);
        String replaceStr = "/" + cityUrl + "form-of-study/";
        String formsName = req.getRequestURI().replaceAll(replaceStr, "");
        if (formsName.equalsIgnoreCase("group-training-online") ||
                formsName.equalsIgnoreCase("individual-training-online") ||
                formsName.equalsIgnoreCase("combined-training-in-classroom") ||
                formsName.equalsIgnoreCase("corporate-training") ||
                formsName.equalsIgnoreCase("group-training-in-classroom") ||
                formsName.equalsIgnoreCase("individual-training-in-classroom") ||
                formsName.equalsIgnoreCase("video-course-with-practice")) {
            return formsName;
        }
        throw new UrlException("uncurrent url to form of study: " + formsName);

    }

    public static String getOpenMeetingUrl(HttpServletRequest req, DataService service) throws UrlException {
        String cityUrl = getCityUrl(req);
        String replaceStr = "/" + cityUrl + "open-meeting/";
        String nameOfOpenMeeting = req.getRequestURI().replaceAll(replaceStr, "");
        if (ValidationUtil.isValidUrlToOpenMeeting(nameOfOpenMeeting, service)) {
            return nameOfOpenMeeting;
        }
        throw new UrlException("uncurrent url to open meeting: " + nameOfOpenMeeting);
    }

    public static String getOpenMeetingUrlFromEnroll(HttpServletRequest req, DataService service, int cityId) throws UrlException {
        String cityUrl = getCityUrl(req);
        String replaceStr = "/" + cityUrl + "open-meeting/enroll";
        String nameOfOpenMeeting = req.getRequestURI().replaceAll(replaceStr, "");
        if (nameOfOpenMeeting.equalsIgnoreCase("") || nameOfOpenMeeting.equalsIgnoreCase("/")){
            return "default";
        }
        String name = nameOfOpenMeeting.replaceAll("/", "");
        if (ValidationUtil.isValidUrlToOpenMeeting(name, service)) {
            return name;
        }
        throw new UrlException("uncurrent url to open meeting: " + nameOfOpenMeeting);
    }


    public static String getNewsUrl(HttpServletRequest req, DataService service) throws UrlException {
        String cityUrl = getCityUrl(req);
        String replaceStr = "/" + cityUrl + "news/";
        String newsUrl = req.getRequestURI().replaceAll(replaceStr, "");
        if (ValidationUtil.isValidUrlToNews(newsUrl, service))
            return newsUrl;
        throw new UrlException("uncurent url to news: " + newsUrl);
    }


    public static String getCityUrl(HttpServletRequest req) {
        String cityUrl = req.getRequestURI().replaceAll(Constants.CONTEXT, "");
        if (cityUrl.equals("") || cityUrl.equals("/")) {
            return "default";
        }
        StringTokenizer tokenizer = new StringTokenizer(cityUrl, "/");
        cityUrl = tokenizer.nextToken();
        return cityUrl + "/";
    }

    public static String getOpenMeetingNameByUrl(String url){
        if (url.equalsIgnoreCase("speaking-club")){
            return "English speaking club";
        }else if (url.equalsIgnoreCase("happy-sunday")){
            return "Happy Sunday";
        }else if (url.equalsIgnoreCase("it-meeting")){
            return "IT - meeting";
        }else if (url.equalsIgnoreCase("hr-meeting")){
            return "HR - meeting";
        }else {
            return "Open Day";
        }
    }

}