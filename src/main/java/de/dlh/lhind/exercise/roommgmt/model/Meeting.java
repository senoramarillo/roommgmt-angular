package de.dlh.lhind.exercise.roommgmt.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Meeting")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @NotNull
    private Room room;

    @Basic(optional = false)
    private String topic;

    @Basic(optional = false)
    private Instant start;

    @Basic(optional = false)
    private Instant end;

    public Meeting(@NotNull Room room, String topic, Instant start, Instant end) {
        this.room = room;
        this.topic = topic;
        this.start = start;
        this.end = end;
    }

}
