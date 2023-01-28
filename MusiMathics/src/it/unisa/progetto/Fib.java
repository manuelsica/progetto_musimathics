package it.unisa.progetto;

import org.jfugue.player.Player;

public class Fib {
	static int SIZE = 16;
	static int fib[] = new int[SIZE];

	static String[] note = new String[] { "B6.t", "C6.h", "D6q", "E6h", "F6", "G6h", "A6q" };

	public static void main(String[] args) {
		fib[0] = 1;
		fib[1] = 1;
		String digits = "11";
		for (int i = 2; i < SIZE; i++) {
			fib[i] = fib[i - 2] + fib[i - 1];
			fib[i] = fib[i] % 7;
			digits = digits + fib[i];
		}

		System.out.format("digits=%s\n", digits);

		String music = "";
		String music1 = "";
		String music2 = "";
		String music3 = "";
		String[] newMusic = new String[note.length];
		String[] newMusic1 = new String[note.length];
		String[] newMusic2 = new String[note.length];
		int octave = 5;
		for (int i = 0; i < digits.length(); i++) {
			int index = Integer.parseInt("" + digits.charAt(i));
			music = music + " " + note[index];
		}
		for (int j = 0; j < note.length; j++)
			newMusic[j] = note[j].substring(0, 1) + octave;
		for (int x = 0; x < digits.length(); x++) {
			int index1 = Integer.parseInt("" + digits.charAt(x));
			if (index1 == 0 || index1 == 6)
				music1 += "h";
			music1 = music1 + " " + newMusic[index1];
		}
		octave++;
		for (int j = 0; j < note.length; j++)
			newMusic1[j] = note[j].substring(0, 1) + octave;
		for (int x = 0; x < digits.length(); x++) {
			int index2 = Integer.parseInt("" + digits.charAt(x));
			if (index2 == 3 || index2 == 6)
				music2 += "h";
			music2 = music2 + " " + newMusic1[index2];

		}
		for (int j = 0; j < note.length; j++)
			newMusic2[j] = note[j].substring(0, 1) + octave;
		for (int x = 0; x < digits.length(); x++) {
			int index3 = Integer.parseInt("" + digits.charAt(x));
			if (index3 == 1 || index3 == 6)
				music3 += "h";
			music3 = music3 + " " + newMusic2[index3];

		}

		System.out.format("music=%s\n", music);
		System.out.format("music1=%s\n", music1);
		System.out.format("music2=%s\n", music2);
		System.out.format("music3=%s\n", music3);

		Player playerMusic = new Player();
		String totalMusic = "V0 I[FLUTE] T[Moderato] :CON(7, 70) " + music + " rw rw rw rw rw rw"
							+ " V1 I[FLUTE] T[Moderato] :CON(7, 70) | rw rw rw " + music1 + "rw rw"
							+ " V2 I[FLUTE] T[Moderato] :CON(7, 70) | rw " + music2
							+ " V3 I[FLUTE] T[Moderato] :CON(7, 70) | rw rw" + music3
							+ "D6q E6q F6q G6q A6q B6q C7q D7q E7q F7q G7q A7q B7q C7w";
		System.out.format("totalMusic = %s\n", totalMusic);
		playerMusic.play(totalMusic);
	}
}