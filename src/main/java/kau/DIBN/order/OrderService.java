package kau.DIBN.order;


import kau.DIBN.item.Item;
import kau.DIBN.item.ItemRepository;
import kau.DIBN.member.Member;
import kau.DIBN.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    public Long addOrder(Long memberId, Long itemId) {

        Optional<Member> findMember = memberRepository.findById(memberId);
        Optional<Item> findItem = itemRepository.findById(itemId);
        Orders order = Orders.builder()
                .member(findMember.get())
                .item(findItem.get())
                .build();
        Orders save = orderRepository.save(order);
        return save.getId();
    }
}
