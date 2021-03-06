package kau.DIBN.artist;

import kau.DIBN.item.Item;
import kau.DIBN.item.ItemRepository;
import kau.DIBN.member.Member;
import kau.DIBN.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    public Long addArtist(Long memberId, Long itemId) {
        Optional<Item> findItem = itemRepository.findById(itemId);
        Optional<Member> findMember = memberRepository.findById(memberId);
        Artist artist = Artist.builder()
                .member(findMember.get())
                .build();
        artist.getItems().add(findItem.get());
        Artist save = artistRepository.save(artist);
        return save.getId();
    }
}
