# Steam Comparator

This project allows a user to enter two steam custom profile URLs and display their account information including display name, Steam ID, most played game with hours, and the five most recent games played with hours.
This program runs on a graphical user interface.

## Authors
Group A: Caleb Langley, Lucas Lehman, Will McRoberts
## Setup
### Cloning the Project
1. Copy this link:
```
    `https://github.com/bsu-cs222-fall25-dll/Steam-Comparator.git`
```
2. Open Intellij
3. In the top right click `Clone Repository`
4. Paste the copied URL
5. Click the blue `Clone` button in the bottom left
6. Setup [API key](#api-key)

#### Running the Program
1. Click on the Gradle icon in the top right
2. Click the drop down arrows on `FinalProject-SteamComparator` -> `Tasks` -> `application`
3. Double click run
   
### Use instructions
In the GUI input boxes, [enter the Steam profile URL](#obtaining-a-steam-profile-url) then press "enter" or "compare" to see the stats! __You must have an [API key](#api-key) to run the program.__


## API Key
### Applying for a key

__You must have a Steam account that has spent $5 in order to get a key__

1. Head to this link: https://steamcommunity.com/dev/apikey  
2. Agree to the terms and services  
3. Click the "Register" button and follow instructions

### Giving the key to the program

1. Head to the Intellij project
2. Scroll down to the `.gitignore` and right click on it
3. Click `New` -> `File`
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

### Example Steam profile URLs
```
https://steamcommunity.com/id/tigerlang
```

```
https://steamcommunity.com/id/YouAreStrongerThanYouKnow
```
