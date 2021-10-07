package competition;

import java.util.List;


/**
 * @author traorea
 *
 */
public class Tournament extends Competition{

    public Tournament(List<Competitor> competitorList) {
        super(competitorList);
    }

    
    protected void play(List<Competitor> competitorList) throws EmptyCompetitorListException, ListSizeIsNotPowerOfTwoException{
        if(competitorList.isEmpty())
            throw new EmptyCompetitorListException("competitor's list should not be empty");
        if(isPowerOfTwo(competitorList)){
            boolean flag = true;
            int cpt = 0;
            while (flag){
                this.playMatch(this.competitorList.get(cpt), this.competitorList.get(cpt+1));
                if(this.competitorList.get(cpt).getPoints()>this.competitorList.get(cpt+1).getPoints())
                    this.competitorList.remove(this.competitorList.get(cpt+1));
                else
                    this.competitorList.remove(this.competitorList.get(cpt));
                cpt ++;
                if(cpt == this.competitorList.size())
                    cpt = 0;
                if(this.competitorList.size() == 1)
                    flag = false;
            }
            classification();
        }else{
            throw new ListSizeIsNotPowerOfTwoException("competitor's list size should be power of 2");
        }
    }

   
    public boolean isPowerOfTwo(List<Competitor>competitorList){
        return (((float)competitorList.size()/2)%2)==0.0;
    }
   
    public void classification(){
        this.competitorList.forEach(competitor -> {
            this.competitors.put(competitor, competitor.getPoints());
        });
    }
}
