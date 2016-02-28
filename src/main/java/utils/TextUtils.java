package utils;

import constants.Constants;
import models.BlokString;
import models.ItemString;
import models.News;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dell on 08.12.2015.
 */
public class TextUtils {

    public static void cutNewsTextBlok(List<News> newsList) {
        for (News news : newsList) {
            cutStringBlokFromNews(news);
        }
    }

    private static void cutStringBlokFromNews(News news) {
        boolean removeOtherBloks = false;
        boolean removeOtherItems = false;

        int countLeter = 0;
        List<BlokString> newBloks = new LinkedList<>();
        if (newBloks.size() == 0)
            return;
        for (int b = 0;b < news.getNewsBlok().size(); b++ ) {
            BlokString blok = news.getNewsBlok().get(b);
            if (removeOtherBloks) {

            }else{
                char[] blokChars = blok.getText().toCharArray();
                for (int i = 0; i < blokChars.length; i++) {
                    countLeter++;
                    if (countLeter >= Constants.NUM_TEXT_LETERS_OF_NEWS) {
                        blok.setText(blok.getText().substring(0, i) + " ... ");
                        removeOtherItems = true;
                        removeOtherBloks = true;
                        break;
                    }
                }
                if (blok.hasItem()){
                    List<ItemString> newItems = new LinkedList<>();
                    for (int ii = 0;ii < blok.getItems().size(); ii++ ) {
                        ItemString item = blok.getItems().get(ii);
                        if (removeOtherItems) {

                        }else{
                            char[] itemChars = item.getItem().toCharArray();
                            for (int i = 0; i < itemChars.length; i++) {
                                countLeter++;
                                if (countLeter >= Constants.NUM_TEXT_LETERS_OF_NEWS) {
                                    item.setItem(item.getItem().substring(0, i) + " ... ");
                                    removeOtherItems = true;
                                    break;
                                }
                            }
                            newItems.add(item);
                        }
                    }
                    blok.setItems(newItems);
                }
                newBloks.add(blok);
            }
        }
        news.setNewsBlok(newBloks);
    }

    public static String editThePhoneNumber(String phoneNumber){
        if (phoneNumber == null){
            return null;
        }
        String tempString = phoneNumber.substring(5);
        String number = tempString.replaceAll("\\D", "");
        return number;
    }
}
