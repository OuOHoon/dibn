package kau.DIBN.cart;

import kau.DIBN.item.Item;
import kau.DIBN.item.ItemRepository;
import kau.DIBN.member.Member;
import kau.DIBN.member.MemberRepository;
import kau.DIBN.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.catalog.Catalog;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final JwtTokenProvider jwtTokenProvider;

    //
    @GetMapping("/cart/{userId}")
    public List<Cart> getCartByUserId(@PathVariable("userId") String userId) {
        return memberRepository.findById(Long.parseLong(userId)).get().getCart();
    }

    @PostMapping("/cart/order")
    public void orderCartItems(@RequestBody Map<String, String> orders) {

    }

    @PostMapping("/cart/{itemId}")
    public Long addItemToCart(HttpServletRequest request, @PathVariable("itemId") String itemId) {
        Optional<Member> findMember = memberRepository.findByEmail(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<Item> findItem = itemRepository.findById(Long.parseLong(itemId));

        Cart save = cartRepository.save(Cart.builder()
                .item(findItem.get())
                .member(findMember.get())
                .build());
        return save.getId();
    }
}
