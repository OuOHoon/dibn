package kau.DIBN.item;

import kau.DIBN.likeitem.LikeItem;
import kau.DIBN.likeitem.LikeItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionException;

import java.io.IOException;
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

    @GetMapping("/item/{category}")
    public List<ItemInfo> getAllItemByCategory(@PathVariable("category") int category) {
        return itemService.getAllItemInfosByCategory(category);
    }

    @GetMapping("/item/like/{itemId}")
    public Long increaseItemLike(@PathVariable("itemId") long itemId) {

        itemService.increaseLike(itemId);

        return itemId;
    }

    @PostMapping("/market/item")
    public Long addItem(@RequestBody Map<String, Object> item) throws TransactionException, CipherException, IOException {
        return itemService.addItem(item);
    }

}
