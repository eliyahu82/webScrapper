import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MakoRobot extends BaseRobot {


    public MakoRobot() {
        super("https://www.mako.co.il/");
    }

    public Map<String, Integer> getWordsStatistics() {

        Map<String, Integer> map = new HashMap<String, Integer>();

        try {
            Document makoWebsite = Jsoup.connect(getRootWebsiteUrl()).get();
            Elements elementOfTitle = makoWebsite.select(".slider_image_inside");
            for (Element element : elementOfTitle) {
                Document innerArticle = Jsoup.connect(getRootWebsiteUrl() + element.selectFirst("a").attr("href")).get();
                Element articleBody = innerArticle.getElementsByClass("article-body").get(0);
                String[] words = articleBody.text().split("[\\s,]+");
                for (String word : words) {
                    if (word.startsWith("\"") || word.startsWith(")") || word.startsWith("(")|| word.startsWith("'") )
                        word = word.substring(1);
                    if (word.endsWith("\"") || word.endsWith(")") || word.endsWith("(")|| word.endsWith("'")|| word.endsWith(".") )
                        word = word.substring(0,word.length()-1);
                    if (!map.containsKey(word))
                        map.put(word, 1);
                    else
                        map.put(word, map.get(word) + 1);
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
            Elements mainArticles = makoWebsite.select(".slider_image_inside");
            for (Element element : mainArticles) {
                Document innerArticle = Jsoup.connect(getRootWebsiteUrl() + element.selectFirst("a").attr("href")).get();
                String[] wordsInsideMainArticle = element.text().split("[\\s,]+");
                for (String word : wordsInsideMainArticle){
                    if (word.equals(text))
                        count++;
                }
                Element subArticle = innerArticle.getElementsByTag("h2").get(0);
                String[] wordsInsideMainSubArticle = subArticle.text().split("[\\s,]+");
                for (String word : wordsInsideMainSubArticle){
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
            Document makoWebsite = Jsoup.connect(getRootWebsiteUrl()).get();
            Elements elementOfTitle = makoWebsite.select(".slider_image_inside");
            for (Element element : elementOfTitle) {
                Document innerArticle = Jsoup.connect(getRootWebsiteUrl() + element.selectFirst("a").attr("href")).get();
                Element articleBody = innerArticle.getElementsByClass("article-body").get(0);
                if (articleBody.text().length() > longestTitle.length())
                    longestTitle = element.getElementsByClass("headline").get(0).text();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return longestTitle;
    }
}
