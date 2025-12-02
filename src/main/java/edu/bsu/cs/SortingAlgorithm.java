package edu.bsu.cs;

import java.util.List;

public class SortingAlgorithm {
    public static List<Game> quickSort(List<Game> gameList, String time)
    {
        int lowIndex = 0;
        int highIndex= gameList.size() - 1;
        quickSortAlgo(gameList, lowIndex, highIndex, time);
        return gameList;
    }

    private static void quickSortAlgo(List<Game> gameList, int lowIndex, int highIndex, String time){
        if (time.equals("rTime")) {

            if (lowIndex >= highIndex) {
                return;
            }

            Game pivot = gameList.get(highIndex);
            int pivotIndex = partitionRTime(gameList, lowIndex, highIndex, pivot);
            quickSortAlgo(gameList, lowIndex, pivotIndex - 1, time);
            quickSortAlgo(gameList, pivotIndex + 1, highIndex, time);
        } else {
            if (lowIndex >= highIndex) {
                return;
            }

            Game pivot = gameList.get(highIndex);
            int pivotIndex = partitionMinutes(gameList, lowIndex, highIndex, pivot);
            quickSortAlgo(gameList, lowIndex, pivotIndex - 1, time);
            quickSortAlgo(gameList, pivotIndex + 1, highIndex, time);
        }
    }

    private static int partitionRTime(List<Game> gameList, int lowIndex, int highIndex, Game pivot){
        int leftPointer=lowIndex;
        int rightPointer=highIndex;
        while(leftPointer<rightPointer) {
            while (gameList.get(leftPointer).rTime() <= pivot.rTime() && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (gameList.get(rightPointer).rTime() >= pivot.rTime() && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(gameList, leftPointer, rightPointer);
        }
        swap(gameList, leftPointer, highIndex);
        return leftPointer;
    }

    private static int partitionMinutes(List<Game> gameList, int lowIndex, int highIndex, Game pivot){
        int leftPointer=lowIndex;
        int rightPointer=highIndex;
        while(leftPointer<rightPointer) {
            while (gameList.get(leftPointer).minutes() <= pivot.minutes() && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (gameList.get(rightPointer).minutes() >= pivot.minutes() && leftPointer < rightPointer) {
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