package ca.sheridancollege.vuhoan.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Contact {
    private Long id;
    private String name;
    private Long phone;
    private String address;
    private String email;
    private String role;
}
