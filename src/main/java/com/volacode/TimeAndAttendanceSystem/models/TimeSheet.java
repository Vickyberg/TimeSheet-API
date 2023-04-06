package com.volacode.TimeAndAttendanceSystem.models;


import lombok.*;


import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class TimeSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long userId;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TimeStamp> timeStamps;
}
