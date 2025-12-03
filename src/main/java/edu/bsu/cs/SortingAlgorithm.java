package edu.bsu.cs;

import java.util.List;
import java.util.function.ToIntFunction;

public class SortingAlgorithm {
    public static List<Game> quickSort(List<Game> gameList, String time) {
        int lowIndex = 0;
        int highIndex = gameList.size() - 1;

        ToIntFunction<Game> extractor = time.equals("lastPlayedTimestamp")
                ? Game::lastPlayedTimestamp : Game::minutes;

        quickSortAlgo(gameList, lowIndex, highIndex, extractor);
        return gameList;
    }

    private static void quickSortAlgo(List<Game> gameList, int lowIndex, int highIndex, ToIntFunction<Game> extractor) {
        if (lowIndex >= highIndex) {
            return;
        }

        Game pivot = gameList.get(highIndex);
        int pivotIndex = partition(gameList, lowIndex, highIndex, pivot, extractor);

        quickSortAlgo(gameList, lowIndex, pivotIndex - 1, extractor);
        quickSortAlgo(gameList, pivotIndex + 1, highIndex, extractor);
    }

    private static int partition(List<Game> gameList, int lowIndex, int highIndex, Game pivot, ToIntFunction<Game> extractor) {
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        while (leftPointer < rightPointer) {
            while (extractor.applyAsInt(gameList.get(leftPointer)) <= extractor.applyAsInt(pivot) && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (extractor.applyAsInt(gameList.get(rightPointer)) >= extractor.applyAsInt(pivot) && leftPointer < rightPointer) {
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