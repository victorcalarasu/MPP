package Domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class SaleEntity extends BaseEntity<Long> {
    private int buyid;
    private long bookid;
    private long clientid;

}