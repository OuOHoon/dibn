package kau.DIBN.item;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemInfo {

    private Long itemId; // 아이템pk
    private String name; // 상품 이름
    private String artist; // 상품 제작자
    private int price; // 상품 가격
    private String description; // 상품 설명
    private int period; // 상품 제작 기간
    private int like; // 좋아요 수
    private String category; // 카테고리 0 나무 1 금속 2 가죽
    private String marketName; // 마켓 이름
}
