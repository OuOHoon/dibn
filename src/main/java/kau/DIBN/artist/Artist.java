package kau.DIBN.artist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id @GeneratedValue
    @Column(name = "artist_id")
    private Long id;



}
