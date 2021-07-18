import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class WallaRobot extends BaseRobot {


    public WallaRobot() {
        super("https://www.walla.co.il/");
    }


    public Map<String, Integer> getWordsStatistics() {

        Map<String, Integer> map = new HashMap<String, Integer>();

        try {
            Document wallaWebsite = Jsoup.connect(getRootWebsiteUrl()).get();
            Element bigTitle = wallaWebsite.selectFirst(".with-roof");
            Document insideBigArticle = Jsoup.connect(bigTitle.getElementsByTag("a").get(0).attr("href")).get();
            Element longestArticle = insideBigArticle.selectFirst(".article-content");
            if (longestArticle != null) {
                String[] words = longestArticle.text().split("[\\s,]+");
                for (String word : words) {
                    if (word.startsWith("\"") || word.startsWith(")") || word.startsWith("(") || word.startsWith("'"))
                        word = word.substring(1);
                    if (word.endsWith("\"") || word.endsWith(")") || word.endsWith("(") || word.endsWith("'") || word.endsWith("."))
                        word = word.substring(0, word.length() - 1);
                    if (!map.containsKey(word))
                        map.put(word, 1);
                    else
                        map.put(word, map.get(word) + 1);
                }
            }

            Element smallTitle = wallaWebsite.selectFirst(".right");
            Elements smallTitleByTag = smallTitle.getElementsByTag("a");
            for (Element element : smallTitleByTag) {
                Document insideTheArticle = Jsoup.connect(element.attr("href")).get();
                Element bodyOfArticle = insideTheArticle.selectFirst(".article-content");
                if (bodyOfArticle != null) {
                    String[] words = bodyOfArticle.text().split("[\\s,]+");
                    for (String word : words) {
                        if (word.startsWith("\"") || word.startsWith(")") || word.startsWith("(") || word.startsWith("'"))
                            word = word.substring(1);
                        if (word.endsWith("\"") || word.endsWith(")") || word.endsWith("(") || word.endsWith("'") || word.endsWith("."))
                            word = word.substring(0, word.length() - 1);
                        if (!map.containsKey(word))
                            map.put(word, 1);
                        else
                            map.put(word, map.get(word) + 1);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public int countInArticlesTitles(String text) {

        int count = 0;
        try {
            Document wallaWebsite = Jsoup.connect(getRootWebsiteUrl()).get();

            Element bigTitle = wallaWebsite.selectFirst(".with-roof");
            Document insideBigArticle = Jsoup.connect(bigTitle.getElementsByTag("a").get(0).attr("href")).get();
            Element mainBigArticle = insideBigArticle.getElementsByTag("header").get(2);
            String[] wordsInsideMainArticle = mainBigArticle.text().split("[\\s,]+");
            for (String word : wordsInsideMainArticle) {
                if (word.equals(text))
                    count++;
            }

            Element smallTitle = wallaWebsite.selectFirst(".right");
            Elements smallTitleByTag = smallTitle.getElementsByTag("a");
            for (Element element : smallTitleByTag) {
                Document insideArticle = Jsoup.connect(element.attr("href")).get();
                Element mainArticle = insideArticle.getElementsByTag("header").get(2);
                String[] wordsInsideSmallArticle = mainArticle.text().split("[\\s,]+");
                for (String word : wordsInsideSmallArticle) {
                    if (word.equals(text))
                        count++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    public String getLongestArticleTitle() {

        String longestTitle = "";

        try {
            Document wallaWebsite = Jsoup.connect(getRootWebsiteUrl()).get();

            Element bigTitle = wallaWebsite.selectFirst(".with-roof");
            Document insideBigArticle = Jsoup.connect(bigTitle.getElementsByTag("a").get(0).attr("href")).get();
            Element bodyOfBigArticle = insideBigArticle.selectFirst(".article-content");
            if (bodyOfBigArticle != null && bodyOfBigArticle.text().length() > longestTitle.length())
                longestTitle = insideBigArticle.getElementsByTag("h1").get(0).text();


            Element smallTitle = wallaWebsite.selectFirst(".right");
            Elements smallTitleByTag = smallTitle.getElementsByTag("a");
            for (Element element : smallTitleByTag) {
                Document insideArticle = Jsoup.connect(element.attr("href")).get();
                Element bodyOfArticle = insideArticle.selectFirst(".article-content");
                if (bodyOfArticle != null && bodyOfArticle.text().length() > longestTitle.length()) {
                    longestTitle = insideArticle.getElementsByTag("h1").get(0).text();

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return longestTitle;
    }
}
