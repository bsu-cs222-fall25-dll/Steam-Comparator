package edu.bsu.cs;

import java.util.List;

public class SortingAlgorithm {

    public static List<Game> quickSort(List<Game> gameList, String sortByField) {
        quickSortAlgo(gameList, 0, gameList.size() - 1, sortByField);
        return gameList;
    }

    private static void quickSortAlgo(List<Game> gameList, int lowIndex, int highIndex, String sortByField) {
        if (lowIndex >= highIndex) {
            return;
        }
        int pivotIndex = partition(gameList, lowIndex, highIndex, sortByField);
        quickSortAlgo(gameList, lowIndex, pivotIndex - 1, sortByField);
        quickSortAlgo(gameList, pivotIndex + 1, highIndex, sortByField);
    }

    private static long getSortValue(Game game, String sortByField) {
        if (sortByField.equals("minutes")) {
            return game.minutes();
        } else {
            return game.lastPlayedTimestamp();
        }
    }

    private static int partition(List<Game> gameList, int lowIndex, int highIndex, String sortByField) {
        long pivotValue = getSortValue(gameList.get(highIndex), sortByField);
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        while (leftPointer < rightPointer) {
            while (getSortValue(gameList.get(leftPointer), sortByField) <= pivotValue && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (getSortValue(gameList.get(rightPointer), sortByField) >= pivotValue && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(gameList, leftPointer, rightPointer);
        }
        swap(gameList, leftPointer, highIndex);
        return leftPointer;
    }

    private static void swap(List<Game> gameList, int i, int j) {
        Game temp = gameList.get(i);
        gameList.set(i, gameList.get(j));
        gameList.set(j, temp);
    }
}
