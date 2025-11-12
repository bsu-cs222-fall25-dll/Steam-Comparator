package edu.bsu.cs;

import java.util.Scanner;

public class CommandLine {
    public static void run() {
        Scanner userInput = new Scanner(System.in);

        while (true) {
            System.out.print("Enter Steam profile link or (q)uit: ");
            String steamLink = userInput.nextLine();

            if (steamLink.equalsIgnoreCase("q")) {
                break;
            }

            try {
                String accountName = AccountParser.parseAccountName(steamLink);
                String userData = UserFetcher.getUserDataAsString(accountName);
                String gameData = UserFetcher.getOwnedGamesAsString(accountName);
                String recentData = UserFetcher.getRecentlyPlayedDataAsString(accountName);
                User user = UserParser.parseUserData(userData, gameData, recentData);
                System.out.println("\n" + user.printUser() + "\n");
            } catch (SteamApiException e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }

    }
}
