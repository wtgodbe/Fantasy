import java.util.ArrayList;


public class Schedule {

	public ArrayList<Matchup> sched;
	
	public Schedule(){
		sched = new ArrayList<Matchup>();
	}
	
	public void addMatchup(Matchup m){
		sched.add(m);
	}
	
	public void simulate(){
		for (Matchup m: sched){
			double t1p = m.team1.simulateGame();
			double t2p = m.team2.simulateGame();
			m.play(t1p, t2p);
		}
	}

}
