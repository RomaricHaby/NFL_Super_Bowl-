package com.example.miniprojet.Model;

import java.io.Serializable;

public class SuperBowl implements Serializable {
    private String winner ;
    private String qb_winner;
    private String coach_winner ;

    private String loser ;
    private String qb_loser ;
    private String coach_loser ;

    private String city ;
    private String state;
    private String stadium ;

    private String winning_pts;
    private String losing_pts;

    private String sb;
    private String attendance;


    public SuperBowl(String winner, String qb_winner, String coach_winner, String loser, String qb_loser, String coach_loser, String city, String state,String stadium , String winning_pts, String losing_pts, String sb, String attendance) {
        this.winner = winner;
        this.qb_winner = qb_winner;
        this.coach_winner = coach_winner;
        this.loser = loser;
        this.qb_loser = qb_loser;
        this.coach_loser = coach_loser;
        this.city = city;
        this.stadium = stadium;
        this.state = state;
        this.winning_pts = winning_pts;
        this.losing_pts = losing_pts;
        this.sb = sb;
        this.attendance = attendance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getQb_winner() {
        return qb_winner;
    }

    public void setQb_winner(String qb_winner) {
        this.qb_winner = qb_winner;
    }

    public String getCoach_winner() {
        return coach_winner;
    }

    public void setCoach_winner(String coach_winner) {
        this.coach_winner = coach_winner;
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }

    public String getQb_loser() {
        return qb_loser;
    }

    public void setQb_loser(String qb_loser) {
        this.qb_loser = qb_loser;
    }

    public String getCoach_loser() {
        return coach_loser;
    }

    public void setCoach_loser(String coach_loser) {
        this.coach_loser = coach_loser;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getWinning_pts() {
        return winning_pts;
    }

    public void setWinning_pts(String winning_pts) {
        this.winning_pts = winning_pts;
    }

    public String getLosing_pts() {
        return losing_pts;
    }

    public void setLosing_pts(String losing_pts) {
        this.losing_pts = losing_pts;
    }

    public String getSb() {
        return sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "SuperBowl{" +
                "winner='" + winner + '\'' +
                ", qb_winner='" + qb_winner + '\'' +
                ", coach_winner='" + coach_winner + '\'' +
                ", loser='" + loser + '\'' +
                ", qb_loser='" + qb_loser + '\'' +
                ", coach_loser='" + coach_loser + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", stadium='" + stadium + '\'' +
                ", winning_pts='" + winning_pts + '\'' +
                ", losing_pts='" + losing_pts + '\'' +
                ", sb='" + sb + '\'' +
                ", attendance='" + attendance + '\'' +
                '}';
    }
}
