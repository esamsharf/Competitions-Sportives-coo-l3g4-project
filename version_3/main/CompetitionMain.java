package main;


import java.util.*;


public class CompetitionMain {
    public static void play(Competition competition){
        try{
            competition.play();
            System.out.println("**** Ranking ****");
            Set<Map.Entry<Competitor, Integer>> competitorEntry = competition.ranking().entrySet();
            Iterator<Map.Entry<Competitor, Integer>> iterator = competitorEntry.iterator();
            while (iterator.hasNext()){
                Map.Entry<Competitor, Integer> entry = iterator.next();
                System.out.println(entry.getKey().toString() + "-" + entry.getValue());
            }
        }catch (EmptyCompetitorListException | ListSizeIsNotPowerOfTwoException except){
            System.out.println(except.getMessage());
        }
    }
 
    public static void main(String []args){
        Competition competition;
        SelectTeamMethodStrategy selectTeamMethod;
        List<Competitor> competitorList = new ArrayList<>();
        for(int i=1; i<args.length; i++){
            Competitor competitor = new Competitor(args[i]);
            competitorList.add(competitor);
        }
        switch (args[0]){
            case "L":
                competition = new League(competitorList);
                competition.addObserver(new Journalists("Canal+"));
                competition.addObserver(new BookMakers("Bet+", competitorList));
                play(competition);
                break;
            case "T":
                competition = new Tournament(competitorList);
                competition.addObserver(new Journalists("Canal+"));
                competition.addObserver(new BookMakers("Bet+", competitorList));
                play(competition);
                break;
            case "M16":
                selectTeamMethod = new SixteenTeamStrategy();
                competition = new Master(competitorList, selectTeamMethod);
                competition.addObserver(new Journalists("Canal+"));
                competition.addObserver(new BookMakers("Bet+", competitorList));
                play(competition);
                break;
            case "M24":
                selectTeamMethod = new TwentyFourTeamStrategy();
                competition = new Master(competitorList, selectTeamMethod);
                competition.addObserver(new Journalists("Canal+"));
                competition.addObserver(new BookMakers("Bet+", competitorList));
                play(competition);
                break;
            case "M32" :
            	for(int i=1; i<=32; i++){
                       Competitor competitor = new Competitor("c"+i);
                       competitorList.add(competitor);   
                   }
            	selectTeamMethod = new TwoFirstPlusTwoSecondStrategy();
                competition = new Master(competitorList, selectTeamMethod);
                competition.addObserver(new Journalists("Canal+"));
                competition.addObserver(new BookMakers("Bet+", competitorList));
                play(competition);
                break;
             default :
            	 System.out.println("Choix Incorrect ");
            	 break;
        }

    }
}
