package kau.DIBN.market;

import kau.DIBN.item.Item;
import kau.DIBN.item.ItemRepository;
import kau.DIBN.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;
    private final ItemService itemService;

    // 마켓 ID로 자기 아이템 목록 검색
    public List<Item> getAllItemsById(Long marketId) {
        return marketRepository.findById(marketId).get().getItems();
    }

    // 마켓 이름 검색어로 검색
    public List<Market> searchNameByKeyword(String keyword){
        return null;
    }
}
