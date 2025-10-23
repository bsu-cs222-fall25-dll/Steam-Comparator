package edu.bsu.cs;


public class Main {
    public static void main(String[] args) throws Exception {
        String accountName = AccountParser.parseAccountName("https://steamcommunity.com/id/tigerlang/");
        String userData = UserFetcher.getUserDataAsString(accountName);
        User user = UserParser.parseUserData(userData);

        System.out.println(user);
    }
}
