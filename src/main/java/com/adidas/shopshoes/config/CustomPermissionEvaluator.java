package com.adidas.shopshoes.config;

import com.adidas.shopshoes.dto.UserPrincipal;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
        if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(auth, targetDomainObject.toString().toUpperCase(), permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(auth, targetType.toUpperCase(), permission.toString().toUpperCase());}

    private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        Collection<SimpleGrantedAuthority> roles = (Collection<SimpleGrantedAuthority>) userPrincipal.getRoles();
        boolean result = false;
        for (SimpleGrantedAuthority role : roles) {
            if (role.getAuthority().contains(permission)) {
                result = true;
                break;
            }
        }
        if (!result) return false;
        for (SimpleGrantedAuthority role : roles) {
            if (role.getAuthority().contains(targetType)) {
                return true;
            }
        }
        return false;
    }
}
