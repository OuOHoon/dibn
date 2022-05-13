package kau.DIBN.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    // 모든 아이템 리턴
    @GetMapping("/item/all")
    public List<ItemInfo> getAllItem() {
        return itemService.getAllItemInfos();
    }

    @GetMapping("/item/{category}")
    public List<ItemInfo> getAllItemByCategory(@PathVariable("category") int category) {
        return itemService.getAllItemInfosByCategory(category);
    }

    @PostMapping("/market/item")
    public Long addItem(@RequestBody Map<String, String> item) {
        return itemService.addItem(item);
    }

}
