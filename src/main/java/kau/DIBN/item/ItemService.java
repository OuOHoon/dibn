package kau.DIBN.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
