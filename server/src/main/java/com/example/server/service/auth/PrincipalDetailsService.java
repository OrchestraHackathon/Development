package com.example.server.service.auth;

import com.example.server.domain.users.PrincipalDetails;
import com.example.server.domain.users.Users;
import com.example.server.repository.UsersRepository;
import com.example.server.util.exception.UsersException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.server.util.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public PrincipalDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsersException(NONE_USER));
        log.info("loadUserByUsername, user=[{}][{}]", users.getEmail(), users.getRole());
        return new PrincipalDetails(users);
    }
}
