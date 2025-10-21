package edu.bsu.cs;


public class Main {
    public static void main(String[] args) throws Exception {
        String accountName = AccountParser.parseAccountName("https://steamcommunity.com/id/tigerlang/");
        String UserData = UserFetcher.getUserDataAsString(accountName);
        User user = UserParser.parseUserData(UserData);

        System.out.println(user);
    }
}
