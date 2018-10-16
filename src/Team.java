import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Team implements Comparable{

	public String name;
	public int wins;
	public int losses;
	public double points;
	public ArrayList<Double> gameScores;
	public double mean;
	public double stdDev;
	
	public Team(String newName, int w, int l, List<Double> gs){
		name = newName;
		wins = w;
		losses = l;
		points = 0;
		for (Double d: gs){
			points += d;
		}
		gameScores = (ArrayList<Double>) gs;
		mean = points / (double) gameScores.size();
		stdDev = getStdDev(gameScores);
	}
	
	public double getWeekScore(int week){
		return gameScores.get(week);
	}
	
	public void win(){
		wins++;
	}
	
	public void lose(){
		losses++;
	}
	
	public void addPoints(double p){
		points += p;
	}
	
	public double simulateGame(){
		Random r = new Random();
		return (r.nextGaussian() * stdDev) + mean;
	}

	public double getStdDev(ArrayList<Double> values){
		double s = 0;
		for (int i = 0; i < values.size(); i++){
			s += ((values.get(i) - mean) * (values.get(i) - mean)) / values.size();
		}
		return Math.sqrt(s);
	}

	@Override
	public int compareTo(Object o) {
		Team t2 = (Team) o;
		if (this.wins > t2.wins){
			return -1;
		} else if (this.wins < t2.wins){
			return 1;
		} else if (this.points > t2.points){
			return -1;
		} else {
			return 1;
		}
	}

}
