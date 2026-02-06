package com.example.core.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "workitem")
public class Workitem {
    @Id
    @ColumnDefault("nextval('workitem_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = Integer.MAX_VALUE)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "ordering")
    private Integer ordering;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ColumnDefault("0")
    @JoinColumn(name = "workcolumn", nullable = false)
    // TODO: esto es así? envía toda la columna con todos los workitems?? que envía??
    @JsonIgnore
    private com.example.core.database.Workcolumn workcolumn;
}