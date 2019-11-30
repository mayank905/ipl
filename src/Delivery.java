public class Delivery
{
    private String matchID;
    private String bowlingTeam;
    private String extraRuns;
    private String bowler;
    private String totalRuns;
    private String wideRuns;
    private String noBall;

    public String getMatchID()
    {return matchID;}

    public void setMatchID(String matchID)
    {this.matchID = matchID;}

    public String getBowlingTeam()
    {return bowlingTeam;}

    public void setBowlingTeam(String team)
    {this.bowlingTeam = team;}

    public String getExtraRuns()
    {return extraRuns;}

    public void setExtraRuns(String runs)
    {this.extraRuns = runs;}

    public String getBowler()
    {return bowler;}

    public void setBowler(String bowler)
    {this.bowler = bowler;}

    public String getTotalRuns()
    {return totalRuns;}

    public void setTotalRuns(String runs)
    {this.totalRuns = runs;}

    public String getWideRuns()
    {return wideRuns;}

    public void setWideRuns(String runs)
    {this.wideRuns = runs;}

    public String getNoBall()
    {return noBall;}

    public void setNoBall(String runs)
    {this.noBall = runs;}
}