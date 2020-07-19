package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int fields = 3;
        int fields2 = 3;
        char[][] game = createGame(fields, fields2);
        STATE state = checkState(game);
        System.out.println(state.text);
    }


    public static char[][] createGame(int a, int b) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        char[][] game = new char[a][b];
        int temp = 0;
        for (int i = 0; i < a; i++) {
            for (int i2 = 0; i2 < b; i2++) {
                game[i][i2] = input.charAt(temp++);
            }
        }

        System.out.print("---------\n");
        for (int i = 0; i < a; i++) {
            System.out.print("| ");
            for (int i2 = 0; i2 < b; i2++) {
                System.out.print(game[i][i2] + " ");
            }
            System.out.println("|");
        }
        System.out.print("---------\n");

        return game;
    }

    public static STATE checkState(char[][] tab) {
        int counterX = 0;
        int counterO = 0;
        STATE state = STATE.GAME_NOT_FINISHED;
        for (int i = 0; i < tab.length; i++) {
            for (int i2 = 0; i2 < tab[0].length; i2++) {
                if (tab[i][i2] == 'O') {
                    counterO++;
                } else if (tab[i][i2] == 'X') {
                    counterX++;
                }
            }
        }
        int counterXinRow;
        int counterOinRow;
        int numberOfWins = 0;

        if (tab[0][0] == 'X' && tab[1][1] == tab[0][0] && tab[2][2] == tab[0][0] ||
                tab[0][2] == 'X' && tab[1][1] == tab[0][2] && tab[2][0] == tab[0][2]){
            state = STATE.X_WINS;
        }
        if (tab[0][0] == 'O' && tab[1][1] == tab[0][0] && tab[2][2] == tab[0][0] ||
                tab[0][2] == 'O' && tab[1][1] == tab[0][2] && tab[2][0] == tab[0][2]){
            state = STATE.O_WINS;
        }

        boolean ThereIsNoEmptyCell = true;
        for (int i = 0; i < tab.length; i++) {
            counterOinRow = 0;
            counterXinRow = 0;
            for (int i2 = 0; i2 < tab[0].length; i2++) {
                if (tab[i][i2] == '_') {
                    ThereIsNoEmptyCell = false;
                }
                if (tab[i][i2] == 'O') {
                    counterOinRow++;
                } else if (tab[i][i2] == 'X') {
                    counterXinRow++;
                }
                if (counterXinRow == 3) {
                    state = STATE.X_WINS;
                    numberOfWins++;
                } else if (counterOinRow == 3) {
                    state = STATE.O_WINS;
                    numberOfWins++;
                }
            }
            if (numberOfWins > 1) {
                state = STATE.ERROR;
            }
        }
        numberOfWins = 0;
        if (state != STATE.ERROR) {
            for (int i = 0; i < tab[0].length; i++) {
                counterOinRow = 0;
                counterXinRow = 0;
                for (int i2 = 0; i2 < tab.length; i2++) {
                    if (tab[i2][i] == 'O') {
                        counterOinRow++;
                    } else if (tab[i2][i] == 'X') {
                        counterXinRow++;
                    }
                    if (counterXinRow == 3) {
                        state = STATE.X_WINS;
                        numberOfWins++;
                    } else if (counterOinRow == 3) {
                        state = STATE.O_WINS;
                        numberOfWins++;
                    }
                }
                if (numberOfWins > 1) {
                    state = STATE.ERROR;
                }
            }
        }
        if (state != STATE.ERROR && state != STATE.O_WINS && state != STATE.X_WINS && ThereIsNoEmptyCell) {
            state = STATE.DRAW;
        } else if (state != STATE.ERROR && state != STATE.O_WINS && state != STATE.X_WINS) {
            state = STATE.GAME_NOT_FINISHED;
        }
        if (Math.abs(counterO - counterX) >= 2) {
            state = STATE.ERROR;
        }
        return state;
    }


    enum STATE {
        GAME_NOT_FINISHED("Game not finished"),
        DRAW("Draw"),
        X_WINS("X wins"),
        O_WINS("O wins"),
        ERROR("Impossible");

        String text;

        STATE(String text) {
            this.text = text;
        }
    }
}