package site.tripjava.tripjava.member.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private String refreshToken;

    public List<? extends GrantedAuthority> getGrantedAuthorities(){
        List<GrantedAuthority> getGrantedAuthorities = new ArrayList<>();
        return getGrantedAuthorities;
    }
}
