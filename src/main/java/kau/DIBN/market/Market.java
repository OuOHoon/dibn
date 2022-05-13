package kau.DIBN.market;

import kau.DIBN.item.Item;
import kau.DIBN.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Market {

    @Id @GeneratedValue
    @Column(name = "market_id")
    private Long id;

    private String name;
    private String address;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // FK 있는쪽
    private Member member;

    @OneToMany(mappedBy = "market")
    private List<Item> items = new ArrayList<>();
}
