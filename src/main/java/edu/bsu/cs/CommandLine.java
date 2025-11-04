package edu.bsu.cs;

import java.net.URL;
import java.util.Scanner;

public class CommandLine {
    public static void run() throws Exception {
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
                String gameData = UserFetcher.getGameDataAsString(accountName);
                User user = UserParser.parseUserData(userData, gameData);
                System.out.println("\n" + user.printUser() + "\n");
            } catch (SteamApiException e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }

    }
}
