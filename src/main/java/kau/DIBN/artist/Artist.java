package kau.DIBN.artist;

import kau.DIBN.item.Item;
import kau.DIBN.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id @GeneratedValue
    @Column(name = "artist_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private Member member;

    @OneToMany(mappedBy = "artist")
    private List<Item> items;
}
