# Steam Comparator

This project allows a user to enter multiple steam custom profile url and displays their account information, including display name, Steam ID, most played game and hours, and the five most recent games played with hours.
This program runs on command line, and in a graphical user interface.

## Authors
Group A: Caleb Langley, Lucas Lehman, Will McRoberts

## Running the Program
Both CLI and GUI work with the same idea, [enter the Steam profile URL](#obtaining-a-steam-profile-url) then press "enter" to see the stats! 
When using the GUI the user can either press the "enter" key or press the `Compare` button in the GUI.

### Running CLI
1. Right click on the `Main.java` file  
2. Click run

### Running GUI
1. Click on the Gradle icon in the top right
2. Click drop downs on FinalProject-SteamComparator -> Tasks -> application  
3. Double click run


## API Key
### Applying for a key

__You must have a Steam account that has spent $5 in order to get a key__

1. Head to this link: https://steamcommunity.com/dev/apikey  
2. Agree to the terms and services  
3. Click the "Register" button

### Giving the key to the Program

1. Head to the Intellij project<br>
2. Scroll down to the `.gitignore` and right click on it<br>
3. Click New -> File<br>
4. Name the file `config.properties`  
5. Inside `config.properties` input your key like this:

```
steam.api.key = ENTER_YOUR_KEY_HERE
```

## Obtaining a Steam profile URL
1. Head to a Steam account
2. Locate URL: near the top of the window, there is what appears to be a search bar with a link
3. Click on the link
4. It is now copied to you clipboard
