import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class League {

	public Team will;
	public Team dan;
	public Team shane;
	public Team sahil;
	public Team isaac;
	public Team pk;
	public Team todd;
	public Team mike;
	public Team jimmy;
	public Team shoey;
	public Team andy;
	public Team mark;
	public Schedule sched;
	
	public League(){
		double[] willPoints = {127.5, 123.8, 99.6, 124.0, 146.0, 130.9};
		double[] danPoints = {105.6, 149.3, 104.9, 159.1, 91.0, 95.0};
		double[] shanePoints = {96.5, 106.4, 76.7, 76.1, 108.9, 80.3};
		double[] sahilPoints = {87.3, 99.8, 119.9, 146.5, 115.7, 67.7};
		double[] isaacPoints = {153.2, 106.2, 95.8, 120.3, 106.8, 135.7};
		double[] pkPoints = {105.7, 113.1, 103.8, 82.4, 101.0, 89.9};
		double[] toddPoints = {114.0, 108.9, 113.6, 138.5, 78.2, 87.3};
		double[] mikePoints = {122.3, 70.5, 73.4, 85.2, 87.7, 129.8};
		double[] jimmyPoints = {152.3, 89.2, 76.7, 108.6, 85.5, 120.1};
		double[] shoeyPoints = {120.5, 114.8, 132.2, 145.5, 93.7, 101.2};
		double[] andyPoints = {70.4, 122.2, 87.7, 130.4, 115.8, 136.4};
		double[] markPoints = {112.5, 142.7, 119.7, 129.4, 132.1, 144.3};
		
		will = new Team("Will", 5, 1, convert(willPoints));
		dan = new Team("Dan", 4, 2, convert(danPoints));
		shane = new Team("Shane", 2, 4, convert(shanePoints));
		sahil = new Team("Sahil", 1, 5, convert(sahilPoints));
		isaac = new Team("Isaac", 2, 4, convert(isaacPoints));
		pk = new Team("PK", 5, 1, convert(pkPoints));
		todd = new Team("Todd", 2, 4, convert(toddPoints));
		mike = new Team("Mike", 1, 5, convert(mikePoints));
		jimmy = new Team("Jimmy", 2, 4, convert(jimmyPoints));
		shoey = new Team("Shoey", 4, 2, convert(shoeyPoints));
		andy = new Team("Andy", 3, 3, convert(andyPoints));
		mark = new Team("Mark", 5, 1, convert(markPoints));
		
		sched = createSchedule();
	}
	
	public void simulate(){
		sched.simulate();
		ArrayList<Team> standings = standings();
		ArrayList<Matchup> week12 = new ArrayList<Matchup>();
		week12.add(new Matchup(standings.get(0), standings.get(11)));
		week12.add(new Matchup(standings.get(1), standings.get(10)));
		week12.add(new Matchup(standings.get(2), standings.get(5)));
		week12.add(new Matchup(standings.get(3), standings.get(4)));
		week12.add(new Matchup(standings.get(6), standings.get(9)));
		week12.add(new Matchup(standings.get(7), standings.get(8)));
		for (Matchup m: week12){
			double t1p = m.team1.simulateGame();
			double t2p = m.team2.simulateGame();
			m.play(t1p, t2p);
		}
	}
	
	public double getMean(){
		ArrayList<ArrayList<Double>> everyone = getEveryone();
		double total = 0;
		for (ArrayList<Double> guy: everyone){
			for (Double d: guy){
				total += d;
			}
		}
		return total / (12 * 11);
	}
	
	public double getStdDev(){
		double s = 0;
		double mean = getMean();
		ArrayList<ArrayList<Double>> everyone = getEveryone();
		for (ArrayList<Double> guy: everyone){
			for (Double d: guy){
				s += ((d - mean) * (d - mean)) / (12 * 11);
			}
		}
		return Math.sqrt(s);
	}
	
	public ArrayList<ArrayList<Double>> getEveryone(){
		ArrayList<ArrayList<Double>> everyone = new ArrayList<ArrayList<Double>>();
		everyone.add(andy.gameScores);
		everyone.add(mark.gameScores);
		everyone.add(shoey.gameScores);
		everyone.add(jimmy.gameScores);
		everyone.add(mike.gameScores);
		everyone.add(todd.gameScores);
		everyone.add(pk.gameScores);
		everyone.add(isaac.gameScores);
		everyone.add(sahil.gameScores);
		everyone.add(shane.gameScores);
		everyone.add(dan.gameScores);
		everyone.add(will.gameScores);
		return everyone;
	}
	
	public double getWeekScore(int week){
		double total = 0d;
		total += will.getWeekScore(week);
		total += mike.getWeekScore(week);
		total += jimmy.getWeekScore(week);
		total += dan.getWeekScore(week);
		total += shane.getWeekScore(week);
		total += sahil.getWeekScore(week);
		total += isaac.getWeekScore(week);
		total += mark.getWeekScore(week);
		total += shoey.getWeekScore(week);
		total += andy.getWeekScore(week);
		total += todd.getWeekScore(week);
		total += pk.getWeekScore(week);
		return total;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Team> standings(){
		ArrayList<Team> stand = new ArrayList<Team>();
		stand.add(will);
		stand.add(mike);
		stand.add(jimmy);
		stand.add(shane);
		stand.add(sahil);
		stand.add(shoey);
		stand.add(isaac);
		stand.add(todd);
		stand.add(mark);
		stand.add(andy);
		stand.add(pk);
		stand.add(dan);
		Collections.sort(stand);
		return stand;
	}
	
	private Schedule createSchedule(){
		Schedule s = new Schedule();
		
		/*
		//Week 3
		s.addMatchup(new Matchup(will, jimmy));
		s.addMatchup(new Matchup(shane, andy));
		s.addMatchup(new Matchup(pk, sahil));
		s.addMatchup(new Matchup(todd, mark));
		s.addMatchup(new Matchup(isaac, shoey));
		s.addMatchup(new Matchup(dan, mike));
		
		
		//Week 4
		s.addMatchup(new Matchup(will, todd));
		s.addMatchup(new Matchup(dan, sahil));
		s.addMatchup(new Matchup(mike, andy));
		s.addMatchup(new Matchup(shane, pk));
		s.addMatchup(new Matchup(mark, isaac));
		s.addMatchup(new Matchup(shoey, jimmy));
		
		//Week 5
		s.addMatchup(new Matchup(will, isaac));
		s.addMatchup(new Matchup(dan, pk));
		s.addMatchup(new Matchup(mike, shane));
		s.addMatchup(new Matchup(sahil, andy));
		s.addMatchup(new Matchup(jimmy, todd));
		s.addMatchup(new Matchup(shoey, mark));
		
		//Week 6
		s.addMatchup(new Matchup(will, shane));
		s.addMatchup(new Matchup(jimmy, dan));
		s.addMatchup(new Matchup(mike, isaac));
		s.addMatchup(new Matchup(sahil, shoey));
		s.addMatchup(new Matchup(mark, andy));
		s.addMatchup(new Matchup(pk, todd));
		*/
		
		//Week 7
		s.addMatchup(new Matchup(will, dan));
		s.addMatchup(new Matchup(todd, mike));
		s.addMatchup(new Matchup(shoey, shane));
		s.addMatchup(new Matchup(jimmy, sahil));
		s.addMatchup(new Matchup(isaac, andy));
		s.addMatchup(new Matchup(pk, mark));
		
		//Week 8
		s.addMatchup(new Matchup(will, mike));
		s.addMatchup(new Matchup(dan, mark));
		s.addMatchup(new Matchup(shane, jimmy));
		s.addMatchup(new Matchup(sahil, todd));
		s.addMatchup(new Matchup(andy, shoey));
		s.addMatchup(new Matchup(pk, isaac));
		
		//Week 9
		s.addMatchup(new Matchup(will, sahil));
		s.addMatchup(new Matchup(dan, todd));
		s.addMatchup(new Matchup(mike, mark));
		s.addMatchup(new Matchup(isaac, shane));
		s.addMatchup(new Matchup(jimmy, andy));
		s.addMatchup(new Matchup(pk, shoey));
		
		//Week 10
		s.addMatchup(new Matchup(will, pk));
		s.addMatchup(new Matchup(dan, shoey));
		s.addMatchup(new Matchup(jimmy, mike));
		s.addMatchup(new Matchup(mark, shane));
		s.addMatchup(new Matchup(sahil, isaac));
		s.addMatchup(new Matchup(andy, todd));
		
		//Week 11
		s.addMatchup(new Matchup(will, andy));
		s.addMatchup(new Matchup(dan, isaac));
		s.addMatchup(new Matchup(mike, shoey));
		s.addMatchup(new Matchup(shane, todd));
		s.addMatchup(new Matchup(sahil, mark));
		s.addMatchup(new Matchup(jimmy, pk));
		
		return s;
	}
	
	private ArrayList<Double> convert (double[] input){
		ArrayList<Double> result = new ArrayList<Double>();
		for (int i = 0; i < input.length; i++){
			result.add(input[i]);
		}
		return result;
	}

}
