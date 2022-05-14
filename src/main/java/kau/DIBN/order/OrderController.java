package kau.DIBN.order;

import kau.DIBN.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final MemberRepository memberRepository;

    // 한 유저의 주문 리스트
    @GetMapping("/order/{userId}")
    public List<Orders> getOrderListByUserId(@PathVariable("userId") Long userId) {
        return memberRepository.findById(userId).get().getOrders();
    }

    // 주문 생성
    @PostMapping("/order")
    public Long order(HttpServletRequest request, @RequestBody Map<String, String> order) {
        orderRepository.save(Orders.builder()
                .build());
        return 1L;
    }
}
