import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Choose one of the options: ");
        System.out.println("For Mako, press - 1");
        System.out.println("For Ynet, press - 2");
        System.out.println("For Walla, press - 3");
        Scanner scanner = new Scanner(System.in);
        int userNumber = scanner.nextInt();
        int numOfTries = 5;
        int points = 0;
        switch (userNumber) {

            case 1:

                MakoRobot makoBot = new MakoRobot();
                Map<String, Integer> makoMap = makoBot.getWordsStatistics();
                System.out.println("Your points will be as much as words that in the article.");
                System.out.print("I will give you a clue:");
                System.out.println(makoBot.getLongestArticleTitle());
                System.out.println("This is the title of the longest article");
                while (numOfTries > 0) {
                    System.out.println("You can choose only 5 words");
                    System.out.print("Insert your word: ");
                    String userWord = scanner.next();
                    if (makoMap.containsKey(userWord)) {
                        points += makoMap.get(userWord);
                    }
                    if (makoMap.get(userWord) != null) {
                        System.out.println("You your score for now is: " + makoMap.get(userWord) + " points");
                    } else {
                        System.out.println("You your score is 0 for now");
                    }
                    numOfTries--;
                }
                System.out.println("You your score for now is: " + points);
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Now lets go for the big prize. insert a text 1-20 letters. if the text is in the article");
                System.out.println("you will get 250 points. ----- you can be mistake only for 2 letters");
                String userTextMako = scanner.next();
                int numberOfWordsMako = makoBot.countInArticlesTitles(userTextMako);
                System.out.print("How many times do you think you will find this word : ");
                int userNumberMako = scanner.nextInt();
                if (userTextMako.length() < 1 || userTextMako.length() > 20) {
                    System.out.println("Only between 1-20!!!");
                    System.out.println("your score for now: " + points);
                } else if (userNumberMako < 0) {
                    System.out.println("Only 0 and bigger then 0");
                    System.out.println("your score for now: " + points);
                } else if (userNumberMako > numberOfWordsMako - 3 && userNumberMako < numberOfWordsMako + 3) {
                    System.out.println("Good choice. you get 250 more points");
                    points += 250;
                    System.out.println("your score for now: " + points);
                } else {
                    System.out.println("your score for now: " + points);
                }
                break;

            case 2:
                YnetRobot ynetRobot = new YnetRobot();
                Map<String, Integer> ynetMap = ynetRobot.getWordsStatistics();
                System.out.println("Your points will be as much as words that in the article.");
                System.out.print("I will give you a clue:");
                System.out.println(ynetRobot.getLongestArticleTitle());
                System.out.println("this is the title of the longest article");
                points = 0;
                while (numOfTries > 0) {
                    System.out.println("You can choose only 5 words");
                    System.out.print("Insert your word: ");
                    String userWord = scanner.next();
                    if (ynetMap.containsKey(userWord)) {
                        points += ynetMap.get(userWord);
                    }
                    if (ynetMap.get(userWord) != null) {
                        System.out.println("You your score for now is: " + ynetMap.get(userWord) + " points");
                    } else {
                        System.out.println("You your score is 0 for now");
                    }
                    numOfTries--;
                }
                System.out.println("You your score for now is: " + points);
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Now lets go for the big prize. insert a text 1-20 letters. if the text is in the article");
                System.out.println("you will get 250 points. ----- you can be mistake only for 2 letters");
                String userTextYnet = scanner.next();
                int numberOfWordsYnet = ynetRobot.countInArticlesTitles(userTextYnet);
                System.out.print("How many times do you think you will find this word : ");
                int userNumberYnet = scanner.nextInt();
                if (userTextYnet.length() < 1 || userTextYnet.length() > 20) {
                    System.out.println("Only between 1-20!!!");
                    System.out.println("your score for now: " + points);
                } else if (userNumberYnet < 0) {
                    System.out.println("Only 0 and bigger then 0");
                    System.out.println("your score for now: " + points);
                } else if (userNumberYnet > numberOfWordsYnet - 3 && userNumberYnet < numberOfWordsYnet + 3) {
                    System.out.println("Good choice. you get 250 more points");
                    points += 250;
                    System.out.println("your score for now: " + points);
                } else {
                    System.out.println("your score for now: " + points);
                }
                break;

            case 3:

                WallaRobot wallaBot = new WallaRobot();
                Map<String, Integer> wallaMap = wallaBot.getWordsStatistics();
                System.out.println("Your points will be as much as words that in the article.");
                System.out.print("I will give you a clue:");
                System.out.println(wallaBot.getLongestArticleTitle());
                System.out.println("this is the title of the longest article");
                points = 0;
                while (numOfTries > 0) {
                    System.out.println("You can choose only 5 words");
                    System.out.print("Insert your word: ");
                    String userWord = scanner.next();
                    if (wallaMap.containsKey(userWord)) {
                        points += wallaMap.get(userWord);
                    }
                    if (wallaMap.get(userWord) != null) {
                        System.out.println("You your score for now is: " + wallaMap.get(userWord) + " points");
                    } else {
                        System.out.println("You your score is 0 for now");
                    }
                    numOfTries--;
                }
                System.out.println("You your score for now is: " + points);
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Now lets go for the big prize. insert a text 1-20 letters. if the text is in the article");
                System.out.println("you will get 250 points. ----- you can be mistake only for 2 letters");
                String userTextWalla = scanner.next();
                int numberOfWordsWalla = wallaBot.countInArticlesTitles(userTextWalla);
                System.out.print("How many times do you think you will find this word : ");
                int userNumberWalla = scanner.nextInt();
                if (userTextWalla.length() < 1 || userTextWalla.length() > 20) {
                    System.out.println("Only between 1-20!!!");
                    System.out.println("your score for now: " + points);
                } else if (userNumberWalla < 0) {
                    System.out.println("Only 0 and bigger then 0");
                    System.out.println("your score for now: " + points);
                } else if (userNumberWalla > numberOfWordsWalla - 3 && userNumberWalla < numberOfWordsWalla + 3) {
                    System.out.println("Good choice. you get 250 more points");
                    points += 250;
                    System.out.println("your score for now: " + points);
                } else {
                    System.out.println("your score for now: " + points);
                }
                break;

            default:
                System.out.println("Only 1-3");
                main(args);
        }
    }
}