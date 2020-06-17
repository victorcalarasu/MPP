package web.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class SaleDto extends BaseDto{
    private int buyid;
    private long bookid;
    private long clientid;
}
