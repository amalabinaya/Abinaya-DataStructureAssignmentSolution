package com.greatlearning.dataStructureAssignmentSolution;

import java.util.*;
public class ArchitectSkyscrapper {

	private int noOfFloors = 0;
	private int[] floorSizePerDay, floorSizeInAscOrder;
	private Stack<Integer> floorSizeInStack;
	Scanner scanner = new Scanner(System.in);


	public static void main(String[] args) {
		ArchitectSkyscrapper skyscraperService = new ArchitectSkyscrapper();
		skyscraperService.implementSkyscraperApp();
	}


	public void implementSkyscraperApp() {

		System.out.println("enter the total no of floors in the building");
		noOfFloors = scanner.nextInt();

		floorSizePerDay = new int[noOfFloors];

		getFloorSizePerDay();
		scanner.close();

		storeFloorSizeInAscOrder();

		storeFloorSizeInStack();

		displayFloorsToBeConstructed();
	}

	private void getFloorSizePerDay() {
		for (int i = 0; i < noOfFloors; i++) {
			System.out.println("enter the floor size given on day : " + (i + 1));
			floorSizePerDay[i] = scanner.nextInt();
		}
	}

	private void storeFloorSizeInAscOrder() {
		floorSizeInAscOrder = floorSizePerDay.clone();
		sort(floorSizeInAscOrder, 0, floorSizeInAscOrder.length - 1);
	}

	private void storeFloorSizeInStack() {
		floorSizeInStack = new Stack<>();
		for (int size : floorSizeInAscOrder) {
			floorSizeInStack.push(size);
		}
	}

	private void displayFloorsToBeConstructed() {
		System.out.println();
		System.out.println("The order of construction is as follows");

		Stack<Integer> tempStack = new Stack<>();
		for (int i = 0; i < noOfFloors; i++) {
			System.out.println("Day " + (i + 1));
			int floorSizeOfTheDay = floorSizePerDay[i];

			int largestFloorSize = floorSizeInStack.peek();
			if (floorSizeOfTheDay != largestFloorSize) {
				tempStack.push(floorSizeOfTheDay);
			} else {
				System.out.print(floorSizeInStack.pop() + " ");
				while (!tempStack.isEmpty() && Objects.equals(floorSizeInStack.peek(), tempStack.peek())) {
					System.out.print(floorSizeInStack.pop() + " ");
					tempStack.pop();
				}
			}
			System.out.println();
		}
	}

	public static void sort(int[] arr, int l, int r) {
		if (l < r) {

			int m = l + (r - l) / 2;
			sort(arr, l, m);
			sort(arr, m + 1, r);
			merge(arr, l, m, r);
		}
	}

	private static void merge(int[] arr, int l, int m, int r) {

		int n1 = m - l + 1;
		int n2 = r - m;

		int[] L = new int[n1];
		int[] R = new int[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		int i = 0, j = 0;

		int k = l;

		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}
}
