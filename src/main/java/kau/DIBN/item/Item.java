package kau.DIBN.item;

import kau.DIBN.artist.Artist;
import kau.DIBN.market.Market;
import kau.DIBN.member.Member;
import kau.DIBN.order.Orders;
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
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name; // 상품 이름
    private String artist; // 상품 제작자
    private int price; // 상품 가격
    private String description; // 상품 설명
    private int period; // 상품 제작 기간
    private int likes; // 좋아요 수
    private String category; // 카테고리 wood metal leather etc
    private int tokenId; // NFT Token Id
    private byte[] cid; // 이미지 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market; // 상품이 등록된 마켓

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artistObject;

    @OneToMany(mappedBy = "item")
    private List<Orders> orders = new ArrayList<>();

    @OneToMany(
            mappedBy = "board",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )


    public void increaseLike() {
        this.likes += 1;
    }
}
