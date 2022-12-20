package com.adidas.shopshoes.service;

import com.adidas.shopshoes.dto.UserDTO;
import com.adidas.shopshoes.dto.UserPrincipal;
import com.adidas.shopshoes.mapper.UserMapper;
import com.adidas.shopshoes.model.Privilege;
import com.adidas.shopshoes.model.Role;
import com.adidas.shopshoes.model.User;
import com.adidas.shopshoes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        user = userRepository.save(user);
        return userMapper.toUserDTO(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        if (Objects.isNull(userDTO.getId())) return null;
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        if (userOptional.isEmpty()) return null;
        User user = userRepository.save(userMapper.toUser(userDTO));
        return userMapper.toUserDTO(user);
    }

    @Override
    public UserDTO findById(String id) {
        if (Objects.isNull(id)) return null;
        return userMapper.toUserDTO(userRepository.findById(id).orElse(null));
    }

    @Override
//    @PostFilter("filterObject == authentication.principal.username")
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        if (CollectionUtils.isEmpty(users)) return Collections.emptyList();
        return userMapper.toUserDTOs(users);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) return null;

        return UserPrincipal.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(getAuthorities(user.getRoles()))
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private Set<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    @Override
    public List<UserDTO> getAll2() {
        return findAll();
    }
}
