package it.unisa.progetto;

import org.jfugue.player.Player;

public class Fib {
	static int SIZE = 16;
	static int fib[] = new int[SIZE]; /* array statico "fib" che avrà una dimensione di 16 elementi. */

	/*
	 * Questa è la dichiarazione di un array statico "note" che contiene le note
	 * musicali da utilizzare nella successione di Fibonacci.
	 * Partendo dall'ottava sesta nella sequenza con l'annotazione anglossassone
	 * Nella notazione latina, le note corrispondono a:
	 * B = Si
	 * C = Do
	 * D = Re
	 * E = Mi
	 * F = Fa
	 * G = Sol
	 * A = La
	 * 
	 * Per una convenzione, strettamente legata all'armonia del risultato, alla
	 * cella 0 dell'array corrisponde B6.t che corrisponde a
	 * un Si di ottava sempre sesta con una durata di t che equivale alla durata di
	 * una biscroma, che ha una durata pari a 1/32, affiancondo esso
	 * con un punto la durata aumenta del 50% quindi sarà 1/32 + 1/64.
	 * 
	 * Le altre note hanno anch'esso una durata che oscilla tra h, che equivale a
	 * half cioè una minima che equivale alla durata di 1/2,
	 * accompagnata dal punto diventa 1/2 + 1/4, e tra q, che equivale a quarter
	 * cioè una semiminima, la sua durata equivale a 1/2
	 * 
	 */
	static String[] note = new String[] { "B6.t", "C6.h", "D6q", "E6h", "F6q", "G6h", "A6q" };

	public static void main(String[] args) {

		/*
		 * Si inializza la serie di fibonacci 11, per una scelta prettamente armonica
		 * musicale, per far sì
		 * che parta direttamente da DO
		 */

		fib[0] = 1;
		fib[1] = 1;
		String digits = "11";
		for (int i = 2; i < SIZE; i++) {
			fib[i] = fib[i - 2] + fib[i - 1];
			/*
			 * Si calcola il resto della divisione del valore ottenuto per 7.
			 * Usiamo l'operatore modulo affinché si utilizzi solo i 7 tasti (ad esempio di
			 * un pianoforte)
			 * e armonizzare al meglio la melodia
			 * 
			 */
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

		/*
		 * Ogni ciclo genera la stringa di musica "music" utilizzando i valori di
		 * "digits".
		 * Da music1, si utilizza il metodo substring per andare all'ottava successiva,
		 * eliminando
		 * le durate delle note ma che vengono poi decise a seconda dell'index
		 * dell'array
		 * 
		 */
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
			else
				music1 += "q";
			music1 = music1 + " " + newMusic[index1];
		}
		octave++;
		for (int j = 0; j < note.length; j++)
			newMusic1[j] = note[j].substring(0, 1) + octave;
		for (int x = 0; x < digits.length(); x++) {
			int index2 = Integer.parseInt("" + digits.charAt(x));
			if (index2 == 3 || index2 == 6)
				music2 += "h";
			else
				music2 += "q";
			music2 = music2 + " " + newMusic1[index2];

		}
		for (int j = 0; j < note.length; j++)
			newMusic2[j] = note[j].substring(0, 1) + octave;
		for (int x = 0; x < digits.length(); x++) {
			int index3 = Integer.parseInt("" + digits.charAt(x));
			if (index3 == 1 || index3 == 6)
				music3 += "h";
			else
				music3 += "q";
			music3 = music3 + " " + newMusic2[index3];

		}

		/* Stampa di tutte le note musicali prodotte */

		System.out.format("music=%s\n", music);
		System.out.format("music1=%s\n", music1);
		System.out.format("music2=%s\n", music2);
		System.out.format("music3=%s\n", music3);
		/*
		 * Viene creato un oggetto di classe Player con il nome playerMusic. La stringa
		 * totaleMusic viene poi assegnata all'oggetto playerMusic per essere
		 * riprodotta.
		 * La stringa totaleMusic è una combinazione delle quattro stringhe musicali,
		 * music, music1, music2 e music3,
		 * che verranno riprodotte con diversi canali V0, V1, V2 e V3 con le relative
		 * istruzioni per la riproduzione.
		 * 
		 */
		Player playerMusic = new Player();
		String totalMusic = "V0 I[FLUTE] T[Moderato] :CON(7, 70) " + music + " rw rw rw rw rw rw"
				+ " V1 I[FLUTE] T[Moderato] :CON(7, 70) | rw rw rw " + music1 + " rw rw"
				+ " V2 I[FLUTE] T[Moderato] :CON(7, 70) | rw " + music2 + " V3 I[FLUTE] T[Moderato] :CON(7, 70) | rw rw"
				+ music3 + " T[Moderato] D6q E6q F6q G6q A6q B6q C7q D7q E7q F7q G7q A7q B7q C8w";
		System.out.format("totalMusic = %s\n", totalMusic);
		playerMusic.play(totalMusic);
	}

}