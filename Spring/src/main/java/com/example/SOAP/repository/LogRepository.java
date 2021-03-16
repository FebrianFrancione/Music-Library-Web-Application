package com.example.SOAP.repository;


import com.example.SOAP.entity.LogEntryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface LogRepository extends CrudRepository<LogEntryEntity, Integer> {

    @Query("from LogEntryEntity l where l.typeOfChangeCol=:change")
    public Iterable<LogEntryEntity> findByTypeOfChange(@Param("change") int change);


    @Query("from LogEntryEntity l where l.timeStampCol >= :from AND l.timeStampCol < :to")
    public Iterable<LogEntryEntity> findBytimeStampCol(@Param("from") Timestamp from, @Param("to") Timestamp to);

    @Query("from LogEntryEntity l where l.timeStampCol >= :from AND l.timeStampCol < :to AND l.typeOfChangeCol=:change")
    public Iterable<LogEntryEntity> findBytimeStampColAndTypeOfChange(@Param("from") Timestamp from, @Param("to") Timestamp to, @Param("change") int change);

    public Iterable<LogEntryEntity> findAllBytimeStampColBetween(Timestamp publicationTimeStart, Timestamp publicationTimeEnd);
}
