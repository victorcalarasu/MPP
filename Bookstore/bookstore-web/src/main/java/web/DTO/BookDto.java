package web.DTO;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
@Builder
public class BookDto extends BaseDto {
    private String serialnumber;
    private String name;
    private String genre;
    private String author;
}
