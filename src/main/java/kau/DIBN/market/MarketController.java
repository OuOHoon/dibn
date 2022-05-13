package kau.DIBN.market;

import kau.DIBN.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MarketController {
    private final MarketService marketService;
    private final MarketRepository marketRepository;

    // 마켓 id로 검색
    @GetMapping("/market/{marketId}")
    public List<Item> getAllItems(@PathVariable("marketId") Long marketId){
        return marketService.getAllItemsById(marketId);
    }

    // 마켓 등록
    @PostMapping("/market")
    public Long addMarket(@RequestBody Map<String, String> market) {

        return marketRepository.save(Market.builder()
                .name(market.get("name"))
                .phone(market.get("phone"))
                .address(market.get("address"))
                .category(Integer.parseInt(market.get("category")))
                .build()).getId();
    }


}
