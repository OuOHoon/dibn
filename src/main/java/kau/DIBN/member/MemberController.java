package kau.DIBN.member;

import kau.DIBN.market.Market;
import kau.DIBN.market.MarketRepository;
import kau.DIBN.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final MarketRepository marketRepository;

    // 회원가입
    @PostMapping("/join")
    public Long join(@RequestBody Map<String, String> user) {
        if (user.get("role").equals("USER")){
            Long memberId = memberRepository.save(Member.builder()
                    .email(user.get("email"))
                    .password(passwordEncoder.encode(user.get("password")))
                    .address(user.get("address"))
                    .nickname(user.get("nickname"))
                    .phone(user.get("phone"))
                    .roles(Collections.singletonList("ROLE_USER")) // 가입시 입력한 Role대로 권한
                    .build()).getId();
            return memberId;
        }
        // 마켓 사용자의 경우 마켓에도 추가하고 유저에도 추가

        Member saveMember = memberRepository.save(Member.builder()
                .email(user.get("email"))
                .password(passwordEncoder.encode(user.get("password")))
                .address(user.get("address"))
                .nickname(user.get("nickname"))
                .phone(user.get("phone"))
                .roles(Collections.singletonList("ROLE_MARKET"))// 가입시 입력한 Role대로 권한
                .build());
        marketRepository.save(Market.builder()
                .name(user.get("marketName"))
                .phone(user.get("phone"))
                .address(user.get("address"))
                .member(saveMember)
                .build());
        return saveMember.getId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        Member member = memberRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }
}
