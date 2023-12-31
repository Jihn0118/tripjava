package site.tripjava.tripjava.member.model;

import lombok.*;

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
    private String memberId;
    private String password;
    private String name;
    private LocalDate birthday;
    private String gender;
    private String image;
    private String memberType;
    private String role;
    private String token;

//    public List<? extends GrantedAuthority> getGrantedAuthorities(){
//        List<GrantedAuthority> getGrantedAuthorities = new ArrayList<>();
//        return getGrantedAuthorities;
//    }
}
