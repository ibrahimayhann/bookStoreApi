package com.ibrahimayhan.entities;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	
    @Column(nullable = false, unique = true)
    private String username;

    
    // İçine her zaman HASH (BCrypt) yazılacak.
    @Column(nullable = false)
    private String password;

    
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    
    
    // AKTİF / PASİF kontrolü
    @Column(nullable = false)
    private boolean enabled = true;

    
    // HESAP KİLİTLİ Mİ?
    @Column(nullable = false)
    private boolean locked = false;

    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (roles == null) return Set.of();
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.name()))
                .toList();
	}
	
	@Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    
    
    
    //bu projede ihtiyacım yok
	//@Override public boolean isAccountNonExpired() { return true; }
	//@Override public boolean isCredentialsNonExpired() { return true; }

}
