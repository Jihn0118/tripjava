package site.tripjava.tripjava.member.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberDto {
    private String id;
    private String password;
    private String userName;
    private LocalDate birthday;
    private String gender;
    private String loginType;
    private String memberType;
    private String role;
}
