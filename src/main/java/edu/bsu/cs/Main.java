package edu.bsu.cs;


public class Main {
    public static void main(String[] args) throws Exception {
        String accountName = AccountParser.parseAccountName("https://steamcommunity.com/id/tigerlang/");
        String userData = UserFetcher.getUserDataAsString(accountName);
        String gameData = UserFetcher.getGameDataAsString(accountName);
        User user = UserParser.parseUserData(userData, gameData);

        System.out.println(User.printUser());
    }
}
