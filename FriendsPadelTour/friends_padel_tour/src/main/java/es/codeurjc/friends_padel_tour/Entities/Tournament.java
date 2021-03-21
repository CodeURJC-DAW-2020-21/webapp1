package es.codeurjc.friends_padel_tour.Entities;




import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Bussiness bussiness;   

    private String name;
    private String description;
    private String tournamentStartDate;
    private String tournamentFinishDate;
    private String inscriptionStartDate;
    private String inscriptionFinishDate;
    private int minCouples;
    private int maxCouples;
    private int registeredCouples;
    private String firstWinnngCouple;
    private String secondWinningCouple;
    private boolean accepted;

    @ManyToMany
    private List<DoubleOfPlayers> players;

    private String category;
    
    private int firstPrize;
    private int secondPrize;
    private String localization;
    

    public Tournament(){}

    

    public boolean isAccepted() {
        return accepted;
    }



    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }



    public String getSecondWinningCouple() {
        return secondWinningCouple;
    }

    public Long getId() {
        return id;
    }



    public void setSecondWinningCouple(String secondWinningCouple) {
        this.secondWinningCouple = secondWinningCouple;
    }



    public String getFirstWinnngCouple() {
        return firstWinnngCouple;
    }



    public void setFirstWinnngCouple(String firstWinnngCouple) {
        this.firstWinnngCouple = firstWinnngCouple;
    }



    public List<DoubleOfPlayers> getPlayers() {
        return players;
    }



    public void setPlayers(List<DoubleOfPlayers> players) {
        this.players = players;
    }



    public String getCategory() {
        return category;
    }



    public void setCategory(String category) {
        this.category = category;
    }

    public Bussiness getBussinnes() {
        return bussiness;
    }

    public void setBussinnes(Bussiness bussiness) {
        this.bussiness = bussiness;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public int getSecondPrize() {
        return secondPrize;
    }

    public void setSecondPrize(int prize) {
        this.secondPrize = prize;
    }

    public int getFirstPrize() {
        return firstPrize;
    }

    public void setFirstPrize(int prize) {
        this.firstPrize = prize;
    }

    

    public int getRegisteredCouples() {
        return registeredCouples;
    }

    public void setRegisteredCouples(int registeredCouples) {
        this.registeredCouples = registeredCouples;
    }

    public int getMaxCouples() {
        return maxCouples;
    }

    public void setMaxCouples(int maxCouples) {
        this.maxCouples = maxCouples;
    }

    public int getMinCouples() {
        return minCouples;
    }

    public void setMinCouples(int minCouples) {
        this.minCouples = minCouples;
    }

    public String getInscriptionFinishDate() {
        return inscriptionFinishDate;
    }

    public void setInscriptionFinishDate(String inscriptionFinishDate) {
        this.inscriptionFinishDate = inscriptionFinishDate;
    }

    public String getInscriptionStartDate() {
        return inscriptionStartDate;
    }

    public void setInscriptionStartDate(String inscriptionStartDate) {
        this.inscriptionStartDate = inscriptionStartDate;
    }

    public String getTournamentFinishDate() {
        return tournamentFinishDate;
    }

    public void setTournamentFinishDate(String tournamentFinishDate) {
        this.tournamentFinishDate = tournamentFinishDate;
    }

    public String getTournamentStartDate() {
        return tournamentStartDate;
    }

    public void setTournamentStartDate(String tournamentStartDate) {
        this.tournamentStartDate = tournamentStartDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
