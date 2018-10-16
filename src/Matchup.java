
public class Matchup {

	public Team team1;
	public Team team2;
	
	public Matchup(Team t1, Team t2){
		team1 = t1;
		team2 = t2;
	}
	
	public void play (double team1Points, double team2Points){
		if (team1Points > team2Points){
			team1.win();
			team2.lose();
		} else {
			team1.lose();
			team2.win();
		}
		team1.addPoints(team1Points);
		team2.addPoints(team2Points);
	}
}
