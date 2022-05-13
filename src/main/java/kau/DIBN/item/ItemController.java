package kau.DIBN.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    // 모든 아이템 리턴
    @GetMapping("/item/all")
    public List<ItemInfo> getAllItem() {
        return itemService.getAllItemInfos();
    }

    @GetMapping("/item/")

    @PostMapping("/market/item")
    public Item addItem() {

        return null;
    }

}
