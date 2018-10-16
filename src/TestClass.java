import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;


public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TreeMap<String, ArrayList<Double>> results = new TreeMap<String, ArrayList<Double>>();
		//Double array is wins, losses, points, playoffs, sackos
		results.put("Will", new ArrayList<Double>());
		results.put("Todd", new ArrayList<Double>());
		results.put("Mark", new ArrayList<Double>());
		results.put("PK", new ArrayList<Double>());
		results.put("Shane", new ArrayList<Double>());
		results.put("Shoey", new ArrayList<Double>());
		results.put("Dan", new ArrayList<Double>());
		results.put("Jimmy", new ArrayList<Double>());
		results.put("Mike", new ArrayList<Double>());
		results.put("Andy", new ArrayList<Double>());
		results.put("Isaac", new ArrayList<Double>());
		results.put("Sahil", new ArrayList<Double>());
		for (String s: results.keySet()){
			ArrayList<Double> emptyList = results.get(s);
			for (int q = 0; q < 5; q++){
				emptyList.add(0d);
			}
		}
		for (int i = 0; i < 100000; i ++){
			League l = new League();
			l.simulate();
			ArrayList<Team> teams = l.standings();
			for (int j = 0; j < teams.size(); j++){
				Team t = teams.get(j);
				ArrayList<Double> vals = results.get(t.name);
				vals.set(0, vals.get(0) + t.wins);
				vals.set(1, vals.get(1) + t.losses);
				vals.set(2, vals.get(2) + t.points);
				if (j < 4) vals.set(3, vals.get(3) + 1);
				if (j > 7) vals.set(4, vals.get(4) + 1);
			}
		}
		
		System.out.println();
		TreeMap<String, ArrayList<Double>> results2 = new TreeMap<String, ArrayList<Double>>(new ResultComparator(results));
		results2.putAll(results);
		
		System.out.println("                           PLAYOFF ODDS WEEK 6");
		System.out.println();
		String[] header = {"Team", "|", "Wins", "|", "Losses", "|", "Points", "|", "Playoff%", "|", "Sacko%"};
		System.out.format("%15s%3s%6s%3s%6s%3s%8s%3s%8s%3s%6s\n", header);
		System.out.println("          -------------------------------------------------------");
		for (String k: results2.keySet()){
			ArrayList<Double> teamFinal = results2.get(k);
			String[] toPrint = {k, "|", Math.floor((teamFinal.get(0) / 100000) * 100) / 100  + "", "|", 
								Math.floor((teamFinal.get(1) / 100000) * 100) / 100  + "", "|",
								Math.floor((teamFinal.get(2) / 100000) * 100) / 100  + "", "|", 
								Math.floor((teamFinal.get(3) / 100000) * 10000) / 100 + "", "|",
								Math.floor((teamFinal.get(4) / 100000) * 10000) / 100 + ""
			};
			System.out.format("%15s%3s%6s%3s%6s%3s%8s%3s%8s%3s%6s\n", toPrint);
			/*
			System.out.println(k + ", " + teamFinal.get(0) / 100000 + ", " + teamFinal.get(1) / 100000 + ", " + teamFinal.get(2) / 100000 + ", "
			+ teamFinal.get(3) / 100000 + ", " + teamFinal.get(4) / 100000 + ", "); */
		}

	}

}

class ResultComparator implements Comparator<String> 
{ 

	TreeMap<String, ArrayList<Double>> map = new TreeMap<String, ArrayList<Double>>();
	 
	public ResultComparator(TreeMap<String, ArrayList<Double>> map){
		this.map.putAll(map);
	}
	
    public int compare(String a, String b) 
    { 
        if (map.get(a).get(0) > map.get(b).get(0)){
        	return -1;
        } else if (map.get(a).get(0) < map.get(b).get(0)){
        	return 1;
        } else {
        	if (map.get(a).get(2) > map.get(b).get(2)){
        		return -1;
        	} else if (map.get(a).get(2) < map.get(b).get(2)){
        		return 1;
        	} else {
        		return 0;
        	}
        }
    } 
} 
