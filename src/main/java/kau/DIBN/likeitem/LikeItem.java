package kau.DIBN.likeitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeItem {

    @Id @GeneratedValue
    @Column(name = "likeitem_id")
    private Long id;

    
}
