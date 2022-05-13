package kau.DIBN.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;

    // 가장 최근에 등록한 순으로 모든 아이템 반환
    public List<ItemInfo> getAllItemInfos() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Item> findList = itemRepository.findAll(sort);
        List<ItemInfo> result = new ArrayList<ItemInfo>();

        for (Item item: findList) {
            ItemInfo itemInfo = ItemInfo.builder()
                    .itemId(item.getId())
                    .artist(item.getArtist())
                    .like(item.getLikes())
                    .period(item.getPeriod())
                    .marketName(item.getMarket().getName())
                    .category(item.getMarket().getCategory())
                    .price(item.getPrice())
                    .description(item.getDescription())
                    .build();
            result.add(itemInfo);
        }
        return result;
    }

    // 동일한 카테고리의 모든 아이템 반환
    public List<ItemInfo> getAllItemInfosByCategory(int category){
        List<Item> findList = itemRepository.findAllByCategory(category);
        List<ItemInfo> result = new ArrayList<ItemInfo>();

        for (Item item: findList) {
            ItemInfo itemInfo = ItemInfo.builder()
                    .itemId(item.getId())
                    .artist(item.getArtist())
                    .like(item.getLikes())
                    .period(item.getPeriod())
                    .marketName(item.getMarket().getName())
                    .category(item.getCategory())
                    .price(item.getPrice())
                    .description(item.getDescription())
                    .build();
            result.add(itemInfo);
        }
        return result;
    }

    // 아이템 등록
    @Transactional
    public Long addItem(Map<String, String> item) {
        Long id = itemRepository.save(Item.builder()
                .name(item.get("name"))
                .category(item.get("category"))
                .artist(item.get("artist"))
                .price(Integer.parseInt(item.get("price")))
                .period(Integer.parseInt(item.get("period")))
                .likes(0)
                .description(item.get("description"))
                .build()).getId();

        // 여기서 nft 만들어야함
        // item.get("nft");
        return id;
    }


    public Long increaseLike(Long itemId) {
        Optional<Item> findItem = itemRepository.findById(itemId);

        Item item = findItem.get();
        item.increaseLike();
        Item save = itemRepository.save(item);


        return save.getId();
    }

}
