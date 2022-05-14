package kau.DIBN.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import kau.DIBN.nft.NftService;
import kau.DIBN.nft.WalletDTO;
import kau.DIBN.artist.ArtistRepository;
import kau.DIBN.market.MarketRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final NftService nftService;
    private final ArtistRepository artistRepository;
    private final MarketRepository marketRepository;

    // 가장 최근에 등록한 순으로 모든 아이템 반환
    public List<ItemInfo> getAllItemInfos() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Item> findList = itemRepository.findAll(sort);
        List<ItemInfo> result = new ArrayList<>();

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

    // 동일한 카테고리의 모든 아이템 반환
    public List<ItemInfo> getAllItemInfosByCategory(String category){
        List<Item> findList = itemRepository.findAllByCategory(category);
        List<ItemInfo> result = new ArrayList<>();

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
    public Long addItem(Map<String, Object> item) throws TransactionException, CipherException, IOException {
        Map<String, Object> walletInfo = (Map<String, Object>)item.get("nft");
        String name = item.get("name").toString();
        String category = item.get("category").toString();
        String artist = item.get("artist").toString();
        int price = Integer.parseInt(item.get("price").toString());
        int period = Integer.parseInt(item.get("period").toString());
        String description = item.get("description").toString();

        Long id = itemRepository.save(Item.builder()
                .name(name)
                .category(category)
                .artist(artist)
                .price(price)
                .period(period)
                .likes(0)
                .description(description)
                .build()).getId();


        Item itemInfo = itemRepository.findById(id).get();

        WalletDTO wallet = new WalletDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        String keystore = objectMapper.writeValueAsString(walletInfo.get("keystore"));

        wallet.setKeystore(keystore);
        wallet.setPasswd(walletInfo.get("passwd").toString());

        // 여기서 nft 만들어야함
        if(nftService.NftMint(wallet, itemInfo)==-1)
        {
            //어떻게 실패해서 롤백되게?
            return null;
        }




        return id;
        //return null;
    }

    // 아이템 목록을 이름 키워드로 검색
    public List<ItemInfo> searchNameByKeyword(String keyword) {
        List<Item> findList = itemRepository.findByNameContaining(keyword);

        List<ItemInfo> result = new ArrayList<>();

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

    // 좋아요 상승
    public Long increaseLike(Long itemId) {
        Optional<Item> findItem = itemRepository.findById(itemId);

        Item item = findItem.get();
        item.increaseLike();
        Item save = itemRepository.save(item);


        return save.getId();
    }

}
