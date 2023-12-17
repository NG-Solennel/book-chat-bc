package com.sol.bookchat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "favorites",uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","imdbID"}))
public class Favorite {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imdbID;
    private String Title;
    private String Year;
    private EFilm Type;
    private String Poster;
    @ManyToOne
    @JsonBackReference
    private User user;
    @Column(nullable = false)
    private Boolean isWatched;
}
