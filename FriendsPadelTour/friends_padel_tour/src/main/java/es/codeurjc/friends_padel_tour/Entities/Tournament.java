package es.codeurjc.friends_padel_tour.Entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String description;
    private String tournamentStartDate;
    private String tournamentFinishDate;
    private String inscriptionStartDate;
    private String inscriptionFinishDate;
    private int minCouples;
    private int maxCouples;
    private int registeredCouples = 0;

    @OneToMany
    private ArrayList<Match> matches;
    private ArrayList<String> categories;
    private int prize;
    private String localization;
    
    public Tournament(String name, String tournamentStartDate, String tournamentFinishDate,
            String inscriptionStartDate, String inscriptionFinishDate, int minCouples, int maxCouples, ArrayList<String> categories, int prize,
            String localization) {
        this.setName(name);
        this.setDescription(description);
        this.setTournamentStartDate(tournamentStartDate);
        this.setTournamentFinishDate(tournamentFinishDate);
        this.setInscriptionStartDate(inscriptionStartDate);
        this.setInscriptionFinishDate(inscriptionFinishDate);
        this.setMinCouples(minCouples);
        this.setMaxCouples(maxCouples);
        this.setRegisteredCouples(registeredCouples);
        this.setMatches(matches);
        this.setCategories(categories);
        this.setPrize(prize);
        this.setLocalization(localization);
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void addCategory(String category){
        this.categories.add(category);
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public void addMatch(Match match){
        this.matches.add(match);
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
