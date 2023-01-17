package it.unisa.progetto;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.player.Player;

public class Fib {
	static int SIZE = 17;
	static int fib[] = new int[SIZE];
	
	static String[] note = new String[] {"C","D", "E", "F", "G", "A", "B"};
	public static void main(String[] args) {
		fib[0]=1;
		fib[1]=1;
		String digits = "11";
		for (int i=2; i<SIZE; i++) {
			fib[i]=fib[i-2]+fib[i-1];
			fib[i] = fib[i]%7;
			digits = digits + fib[i];
		}
		System.out.format("digits=%s\n",digits);
		
		String music = "";
		String music1 = "";
		String[] newMusic = new String [note.length];
		int octave = 5;
		for (int i=0; i<digits.length(); i++) {
			int index = Integer.parseInt(""+digits.charAt(i));
			if (index == 2 || index== 6) 
				music += "w";
			music = music + " " + note[index];
		}
		
		octave++;
		for(int j = 0; j < note.length; j++)
			newMusic[j] = note[j].substring(0, 1) + octave;
		for (int i=0; i<digits.length(); i++) {
			int index = Integer.parseInt(""+digits.charAt(i));
			music1 = music1 + " " + newMusic[index];
		} 
		
		/*Player playerViolin = new Player();
		System.out.format("music=%s\n",music1);
		Pattern violino1 = new Pattern();
		violino1.add("I[TROMBONE]" + music1);
		violino1.setTempo(140);
		playerViolin.play(violino1);*/
		

		Player playerPiano = new Player();
		System.out.format("music=%s\n",music);
		Pattern violino = new Pattern();
		violino.add("I[VIOLIN]" + music);
		violino.setTempo(140);
		playerPiano.play(violino);
		
	}

}
