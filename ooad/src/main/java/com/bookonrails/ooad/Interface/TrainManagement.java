package com.bookonrails.ooad.Interface;

import com.bookonrails.ooad.Model.ClassType;
import com.bookonrails.ooad.Model.SeatAvailability;
import com.bookonrails.ooad.Model.Train;

import java.sql.Date;
import java.util.List;

public interface TrainManagement {
    // Train Retrieval
    List<Train> getAllTrains();
    Train getTrainByTrainNo(String trainNo);
    
    // Train Modification
    Train saveTrain(Train train);
    Train updateTrain(String trainNo, Train updatedTrain);
    void deleteTrain(String trainNo);
    
    // Train Search
    List<Train> searchTrainBySrcAndDest(String SRC, String DEST);
    List<SeatAvailability> searchTrain(String SRC, String DEST, ClassType classes, Date date);
}
