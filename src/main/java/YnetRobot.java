import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class YnetRobot extends BaseRobot {


    public YnetRobot() {
        super("https://www.ynet.co.il/home/0,7340,L-8,00.html");
    }

    public Map<String, Integer> getWordsStatistics() {

        Map<String, Integer> map = new HashMap<String, Integer>();

        try {
            Document makoWebsite = Jsoup.connect(getRootWebsiteUrl()).get();
            Elements elementOfTitle = makoWebsite.select(".textDiv");
            for (Element element : elementOfTitle) {
                Document insideArticle = Jsoup.connect(element.selectFirst("a").attr("href")).get();
                Element bodyOfArticle = insideArticle.selectFirst(".article-body");
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
            Document makoWebsite = Jsoup.connect(getRootWebsiteUrl()).get();
            Elements allMainArticles = makoWebsite.select(".textDiv");
            for (Element element : allMainArticles) {
                Document insideArticle = Jsoup.connect(element.selectFirst("a").attr("href")).get();

                Element subArticle = insideArticle.selectFirst(".subTitle");
                Element mainArticle = insideArticle.selectFirst(".mainTitle");
                if (mainArticle != null && subArticle != null) {
                    String[] wordsInsideSubArticle = subArticle.text().split("[\\s,]+");
                    for (String word : wordsInsideSubArticle) {
                        if (word.equals(text))
                            count++;
                    }
                    String[] wordsInsideMainArticle = mainArticle.text().split("[\\s,]+");
                    for (String word : wordsInsideMainArticle) {
                        if (word.equals(text))
                            count++;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public String getLongestArticleTitle() {

        String longestTitle = "";

        try {
            Document makoWebsite = Jsoup.connect(getRootWebsiteUrl()).get();
            Elements elementOfTitle = makoWebsite.select(".textDiv");
            for (Element element : elementOfTitle) {
                Document insideArticle = Jsoup.connect(element.selectFirst("a").attr("href")).get();
                Element bodyOfArticle = insideArticle.selectFirst(".article-body");
                Element article2 = insideArticle.selectFirst(".article");
                if (article2 != null || bodyOfArticle != null && bodyOfArticle.text().length() > longestTitle.length())
                    if ( bodyOfArticle == null) {
                        Element anotherArticle = article2.selectFirst(".article-header");
                        String anotherTitle = anotherArticle.getElementsByTag("h1").text();
                        longestTitle = anotherTitle;
                    } else {
                        longestTitle = insideArticle.getElementsByClass("mainTitle").get(0).text();
                    }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return longestTitle;
    }
}
