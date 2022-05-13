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
    private String category; // wood metal leather etc

    @OneToMany(mappedBy = "market")
    private List<Item> items = new ArrayList<>();
}
