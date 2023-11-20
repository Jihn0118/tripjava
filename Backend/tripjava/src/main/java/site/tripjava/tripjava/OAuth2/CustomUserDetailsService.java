package site.tripjava.tripjava.OAuth2;//package site.tripjava.tripjava.OAuth2;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import site.tripjava.tripjava.member.model.MemberDto;
//import site.tripjava.tripjava.member.model.mapper.MemberMapper;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = false)
//public class CustomUserDetailsService implements UserDetailsService {
//    private final MemberMapper memberMapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        MemberDto memberDto = memberMapper.findByUsername(username);
//
//        if(memberDto.equals(null)) throw new UsernameNotFoundException(username + " not found");
//
//        return new User(memberDto.getUserName(), memberDto.getPassword(), memberDto.getGrantedAuthorities());
//    }
//}
