package com.ibrahimayhan.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "refresh_tokens",indexes = {
		@Index(name="ix_refresh_user",columnList = "user_id")})
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,unique = true)
	private String tokenHash;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@Column(nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)//token ı iptal etmek istersek true yaparım
    private boolean revoked = false;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
    

    public boolean isActive() {
        return !revoked && Instant.now().isBefore(expiresAt);
    }
}
