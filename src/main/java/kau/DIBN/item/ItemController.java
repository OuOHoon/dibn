package kau.DIBN.item;

import kau.DIBN.likeitem.LikeItem;
import kau.DIBN.likeitem.LikeItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionException;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final LikeItemRepository likeItemRepository;

    // 모든 아이템 리턴
    @GetMapping("/item/all")
    public List<ItemInfo> getAllItem() {
        return itemService.getAllItemInfos();
    }

    // 카테고리에 따른 모든 아이템 리턴
    @GetMapping("/item/{category}")
    public List<ItemInfo> getAllItemByCategory(@PathVariable("category") String category) {
        return itemService.getAllItemInfosByCategory(category);
    }

    @GetMapping("/item/search/{keyword}")
    public List<ItemInfo> getItemBySearchKeyword(@PathVariable("keyword") String keyword) {
        return itemService.searchNameByKeyword(keyword);
    }

    // itemId로 좋아요 추가
    @GetMapping("/item/like/{itemId}")
    public Long increaseItemLike(@PathVariable("itemId") long itemId) {

        itemService.increaseLike(itemId);

        return itemId;
    }

    @PostMapping("/market/item")
    public Long addItem(HttpServletRequest httpServletRequest, @RequestBody Map<String, Object> item) throws TransactionException, CipherException, IOException {
        return itemService.addItem(item);
    }

}
