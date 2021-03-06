package kau.DIBN.item;

import com.klaytn.caver.Caver;
import kau.DIBN.likeitem.LikeItem;
import kau.DIBN.likeitem.LikeItemRepository;
import kau.DIBN.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    private final JwtTokenProvider jwtTokenProvider;

    // 모든 아이템 리턴
    @PostMapping("/item/all")
    public List<ItemInfo> getAllItem() {
        return itemService.getAllItemInfos();
    }

    // 카테고리에 따른 모든 아이템 리턴
    @PostMapping("/item/{category}")
    public List<ItemInfo> getAllItemByCategory(@PathVariable("category") String category) {
        return itemService.getAllItemInfosByCategory(category);
    }

    @PostMapping("/item/search/{keyword}")
    public List<ItemInfo> getItemBySearchKeyword(@PathVariable("keyword") String keyword) {
        System.out.println("keyword = " + keyword);
        return itemService.searchNameByKeyword(keyword);
    }

    // itemId로 좋아요 추가
    @PostMapping("/item/like/{itemId}")
    public Long increaseItemLike(@PathVariable("itemId") long itemId) {

        itemService.increaseLike(itemId);

        return itemId;
    }

    @PostMapping("/market/item")
    public Long addItem(HttpServletRequest httpServletRequest, @RequestBody Map<String, Object> item) throws TransactionException, CipherException, IOException {
        return itemService.addItem(jwtTokenProvider.resolveToken(httpServletRequest), item);
    }

    @PostMapping("/item/img")
    public String uploadItemImage(@RequestParam MultipartFile img) throws IOException {
        System.out.println("::::::::::::::::::::::::::::::::::::::::");
        System.out.println(img);
        byte[] data = img.getBytes();
        Caver caver = new Caver();
        caver.ipfs.setIPFSNode("ipfs.infura.io", 5001, true);
        String cid = caver.ipfs.add(data);

        return cid;
    }

    @GetMapping("/item/img/get")
    public byte[] getItemImage(@RequestParam("cid") String cid) throws IOException {
        Caver caver = new Caver();
        caver.ipfs.setIPFSNode("ipfs.infura.io", 5001, true);
        byte[] bytecode = caver.ipfs.get(cid);
        return bytecode;
    }
}
