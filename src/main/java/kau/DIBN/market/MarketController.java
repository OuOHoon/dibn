package kau.DIBN.market;

import kau.DIBN.item.Item;
import kau.DIBN.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MarketController {
    private final MarketService marketService;
    private final MarketRepository marketRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 마켓 id로 마켓 내의 아이템 목록 검색
    @GetMapping("/market/{marketId}")
    public List<Item> getAllItems(@PathVariable("marketId") Long marketId){
        return marketService.getAllItemsById(marketId);
    }

    @GetMapping("/market/search/{marketId}")
    public Market searchMarketById(@PathVariable("marketId") Long marketId){
        return marketRepository.getById(marketId);
    }

    // 키워드로 마켓 검색
    @GetMapping("/market/search/{keyword}")
    public List<Market> searchMarketByKeyword(@PathVariable("keyword") String keyword) {
        return marketService.searchNameByKeyword(keyword);
    }

    // 마켓 등록
    @PostMapping("/market")
    public Long addMarket(@RequestBody Map<String, String> market) {

        return marketRepository.save(Market.builder()
                .name(market.get("name"))
                .phone(market.get("phone"))
                .address(market.get("address"))
                .build()).getId();
    }


}
