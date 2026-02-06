package com.example.core.database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "workcolumn")
public class Workcolumn {
    @Id
    @ColumnDefault("nextval('columna_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @OneToMany
    @JoinColumn(name = "workcolumn")
    private Set<Workitem> workitems = new LinkedHashSet<>();
}