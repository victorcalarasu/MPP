package web.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
@Builder

public class ClientDto extends BaseDto{
    private long id2;
    private String name;
    private long phoneNumber;
}
