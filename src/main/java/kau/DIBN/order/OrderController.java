package kau.DIBN.order;

import kau.DIBN.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final MemberRepository memberRepository;

    @PostMapping("/order")
    public Long order(HttpServletRequest request, @RequestBody Map<String, String> order) {
        orderRepository.save(Orders.builder()
                .build());
        return 1L;
    }
}
