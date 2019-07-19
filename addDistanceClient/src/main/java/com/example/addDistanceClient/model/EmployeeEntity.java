package com.example.addDistanceClient.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="table_distances")
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="point_a", nullable = false)
    public String city_from;

    @Column(name="point_b", nullable = false)
    public String city_to;

    @Column(name="distance", nullable=false, length=200)
    public Long distance;

}
