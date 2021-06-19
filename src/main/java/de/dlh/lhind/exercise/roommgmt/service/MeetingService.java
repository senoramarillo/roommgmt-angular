package de.dlh.lhind.exercise.roommgmt.service;

import de.dlh.lhind.exercise.roommgmt.model.Meeting;
import de.dlh.lhind.exercise.roommgmt.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MeetingService {

    private final MeetingRepository meetingRepository;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Meeting addMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    public Meeting updateMeeting(Meeting building) {
        return meetingRepository.save(building);
    }

    public void deleteMeetingById(Long id) {
        meetingRepository.deleteMeetingById(id);
    }

    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

}
