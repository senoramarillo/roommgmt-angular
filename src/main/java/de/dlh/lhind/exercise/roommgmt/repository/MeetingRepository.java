package de.dlh.lhind.exercise.roommgmt.repository;

import de.dlh.lhind.exercise.roommgmt.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    void deleteMeetingById(Long id);

}
