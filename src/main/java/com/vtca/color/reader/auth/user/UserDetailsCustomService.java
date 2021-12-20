package com.vtca.color.reader.auth.user;

import com.vtca.color.reader.auth.jwt.JwtUtil;
import com.vtca.color.reader.common.constant.CommonConstant;
import com.vtca.color.reader.common.exception.BusinessException;
import com.vtca.color.reader.common.logger.LoggerUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = null;

        User user = userDao.findByUsername(username);
        if (user != null) {
            roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
        }
        LoggerUtils.warning("User not found with the name " + username);
        throw new BusinessException(UserError.USER_EMPTY);
    }

    public User getUserProfile(String requestTokenHeader) {
        if (StringUtils.isEmpty(requestTokenHeader) || !requestTokenHeader.startsWith("Bearer ")) {
            LoggerUtils.warning("Request Token is not found");
            throw new BusinessException(UserError.USER_EMPTY);
        }

        //JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        String token = requestTokenHeader.substring(7);
        if (StringUtils.isEmpty(token)) {
            LoggerUtils.warning("Token from header is empty");
            throw new BusinessException(UserError.USER_EMPTY);
        }

        //Get username from token
        String username = jwtUtil.getUsernameFromToken(token);
        if (StringUtils.isEmpty(username)) {
            LoggerUtils.warning("Username from Request Token is empty");
            throw new BusinessException(UserError.USERNAME_EMPTY);
        }

        User user = userDao.findByUsername(username);
        if (Objects.isNull(user)) {
            LoggerUtils.warning(UserError.USER_NOT_EXIST.getMessage());
            throw new BusinessException(UserError.USER_NOT_EXIST);
        }

        return user;
    }

    /**
     * Register new user account
     * @param user
     * @return
     */
    public User save(User user) {
        //validate register info
        //username
        if (StringUtils.isEmpty(user.getUsername())) {
            throw new BusinessException(UserError.USERNAME_EMPTY);
        }

        //password
        if (StringUtils.isEmpty(user.getPassword())) {
            throw new BusinessException(UserError.PASSWORD_EMPTY);
        }

        //role
        /*if (StringUtils.isEmpty(user.getRole())) {
            throw new BusinessException(UserError.ROLE_EMPTY);
        }*/

        //email
        /*if (StringUtils.isEmpty(user.getEmail())) {
            throw new BusinessException(UserError.EMAIL_EMPTY);
        }
        */
        User userDb = userDao.findByUsername(user.getUsername().trim());
        if (Objects.nonNull(userDb)) {
            throw new BusinessException(UserError.USERNAME_EXISTED);
        }

        if (StringUtils.isNoneEmpty(user.getEmail())) {
            Pattern pattern = Pattern.compile(CommonConstant.EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(user.getEmail());
            if (!matcher.matches()) {
                throw new BusinessException(UserError.EMAIL_PATTERN);
            }

            User userEmail = userDao.findBy(user.getEmail());
            if (Objects.nonNull(userEmail)) {
                throw new BusinessException(UserError.EMAIL_EXISTED);
            }
        }

        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        user.setRole(Objects.isNull(user.getRole()) ? "ROLE_USER" : user.getRole());
        user.setCreatedBy("SYSTEM");
        user.setCreatedDate(LocalDateTime.now());
        user.setLastUpdateBy("SYSTEM");
        user.setLastUpdateDate(LocalDateTime.now());
        return userDao.save(user);
    }

    /**
     * Update profile for existing account
     * @param user
     * @param id
     */
    public void update(User user, Long id) {
        if (Objects.isNull(id)) {
            throw new BusinessException(UserError.ID_EMPTY);
        }

        if (Objects.isNull(user)) {
            throw new BusinessException(UserError.USER_EMPTY);
        }

        if (StringUtils.isEmpty(user.getUsername())) {
            throw new BusinessException(UserError.USERNAME_EMPTY);
        }

        if (StringUtils.isNoneEmpty(user.getEmail())) {
            Pattern pattern = Pattern.compile(CommonConstant.EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(user.getEmail());
            if (!matcher.matches()) {
                throw new BusinessException(UserError.EMAIL_PATTERN);
            }
        }

        Optional<User> optionalUser = userDao.findById(id);
        if (!optionalUser.isPresent()) {
            throw new BusinessException(UserError.USER_NOT_EXIST);
        }

        User userDb = optionalUser.get();

        if (!user.getUsername().equals(userDb.getUsername())) {
            throw new BusinessException(UserError.USERNAME_NOT_EXIST);
        }

        if (StringUtils.isNoneEmpty(user.getEmail())) {
            userDb.setEmail(user.getEmail());
        }

        if (StringUtils.isNoneEmpty(user.getFirstName())) {
            userDb.setFirstName(user.getFirstName());
        }

        if (StringUtils.isNoneEmpty(user.getLastName())) {
            userDb.setLastName(user.getLastName());
        }

        if (StringUtils.isNoneEmpty(user.getAddress())) {
            userDb.setAddress(user.getAddress());
        }

        userDao.save(userDb);
    }
}
