package com.bookonrails.ooad.Model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class SeatAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "trainNo", nullable = false)
    private Train train;

    @Temporal(TemporalType.DATE)
    private Date date;
    
    private int no_of_coaches;
    @Enumerated(EnumType.STRING) // to store the type of coach as a string
    private ClassType classes;
    private int availableSeats;

    @OneToMany(mappedBy = "seatAvailability", cascade = CascadeType.ALL)
    private List<Ticket> waitingList;
    
    @ElementCollection
    @CollectionTable(name = "CancelledSeats", joinColumns = @JoinColumn(name = "seatAvailability_id"))
    @Column(name = "seat_number")
    private List<Integer> CancelledSeats; // check n%2 = 1 -> Lower, n%2 = 0 -> Upper

    private int lastBookedLowerSeat;
    private int lastBookedUpperSeat;

    public SeatAvailability(){
        this.setAvailableSeats();
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNo_of_coaches() {
        return no_of_coaches;
    }

    public void setNo_of_coaches(int no_of_coaches) {
        this.no_of_coaches = no_of_coaches;
    }

    public ClassType getClasses() {
        return classes;
    }

    public void setClasses(ClassType classes) {
        this.classes = classes;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats() {
        this.availableSeats = no_of_coaches*30; // Assuming that each coach has 30 seats
    }

    public List<Ticket> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(List<Ticket> waitingList) {
        this.waitingList = waitingList;
    }

    public List<Integer> getCancelledSeats() {
        return CancelledSeats;
    }

    public void setCancelledSeats(List<Integer> cancelledSeats) {
        CancelledSeats = cancelledSeats;
    }

    public int getLastBookedLowerSeat() {
        return lastBookedLowerSeat;
    }

    public void setLastBookedLowerSeat(int lastBookedLowerSeat) {
        this.lastBookedLowerSeat = lastBookedLowerSeat;
    }

    public int getLastBookedUpperSeat() {
        return lastBookedUpperSeat;
    }

    public void setLastBookedUpperSeat(int lastBookedUpperSeat) {
        this.lastBookedUpperSeat = lastBookedUpperSeat;
    }

    // birth has only 2 preferences - Lower or Upper

}