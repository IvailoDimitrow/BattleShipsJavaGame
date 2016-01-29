import java.util.Random;
import java.util.Scanner;

public class BattleShips {
	
	public static void initBoard(int[][] board) {
		for (int row = 0; row < 5; row++) {
			for (int cols = 0; cols < 5; cols++) {
				board[row][cols] = -1;

			}
		}
	}

	public static void showBoard(int[][] board) {

		for (int row = 0; row < 5; row++) {
			System.out.print((row + 1) + "");
			for (int column = 0; column < 5; column++) {
				if (board[row][column] == -1) {
					System.out.print(" " + "~");
				} else if (board[row][column] == 0) {
					System.out.print(" " + "*");
				} else if (board[row][column] == 1) {
					System.out.print(" " + "X");
				}

			}
			System.out.println();
		}
	}

	public static void initShips(int[][] ships) {
		Random random = new Random();

		for (int ship = 0; ship < 3; ship++) {
			ships[ship][0] = random.nextInt(5);
			ships[ship][1] = random.nextInt(5);
			for (int last = 0; last < ship; last++) {
				if ((ships[ship][0] == ships[last][0]) && (ships[ship][1] == ships[last][1]))
					do {
						ships[ship][0] = random.nextInt(5);
						ships[ship][1] = random.nextInt(5);
					} while ((ships[ship][0] == ships[last][0]) && (ships[ship][1] == ships[last][1]));
			}

		}

	}

	public static void shoot(int[] shoot) {
		Scanner input = new Scanner(System.in);

		System.out.print("Row:");
		shoot[0] = input.nextInt();
		shoot[0]--;
		System.out.print("Column:");
		shoot[1] = input.nextInt();
		shoot[1]--;

	}

	public static boolean hit(int[] shoot, int[][] ships) {
		for (int ship = 0; ship < ships.length; ship++) {
			if ((shoot[0] == ships[ship][0]) && (shoot[1] == ships[ship][1])) {
				return true;

			}
		}
		return false;
	}

	public static void changeBoard(int[] shoot, int[][] ships, int[][] board) {
		if (hit(shoot, ships))
			board[shoot[0]][shoot[1]] = 1;
		else
			board[shoot[0]][shoot[1]] = 0;
	}

	public static void main(String[] args) {
		int[][] board = new int[5][5];
		int[][] ships = new int[3][2];
		int[] shoot = new int[2];
		int destroyedShips = 0;
		initBoard(board);
		initShips(ships);
		do {
			showBoard(board);
			shoot(shoot);
			if (hit(shoot, ships)) {
				changeBoard(shoot, ships, board);
				System.out.println("you hit a ship");
				destroyedShips++;
			} else {
				changeBoard(shoot, ships, board);
			}

		} while (destroyedShips != 3);
		System.out.println("Battleships game finished,you are the winner!");
		showBoard(board);

	}

}
