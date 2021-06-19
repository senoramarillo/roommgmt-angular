package de.dlh.lhind.exercise.roommgmt.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Building building;

    @Basic(optional = false)
    // @Column(unique = true)
    @NotNull
    private String roomNumber;

    @Basic(optional = false)
    @Range(min = 1, max = 9999, message = "Amount of seats must be between 1 and 9999")
    @NotNull
    @Valid
    private Integer seats;

    @Basic(optional = false)
    private Boolean projectorPresent;

//    public Room(Building building,
//                @NotNull String roomNumber,
//                @Size(min = 1, max = 9999, message = "Amount of seats must be between 1 and 9999") Integer seats,
//                Boolean projectorPresent) {
//        this.building = building;
//        this.roomNumber = roomNumber;
//        this.seats = seats;
//        this.projectorPresent = projectorPresent;
//    }


    public Room(Building building,
                @NotNull String roomNumber,
                @Range(min = 1, max = 9999, message = "Amount of seats must be between 1 and 9999")
                @NotNull
                @Valid Integer seats,
                Boolean projectorPresent) {
        this.building = building;
        this.roomNumber = roomNumber;
        this.seats = seats;
        this.projectorPresent = projectorPresent;
    }
}
