package com.yumkoori.mentoring.user.adapter.out.persistence.entity;

import static com.yumkoori.mentoring.user.domain.EmailVerification.*;

import com.yumkoori.mentoring.user.domain.EmailVerification;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import jdk.jfr.Timestamp;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "email_Code")
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class EmailVerificationJpaEntity {

    @Id
    private String email;

    private String verification;

    @Enumerated(EnumType.STRING)
    @Setter
    private verificationStatus status;

    @CreatedDate
    @Setter
    private LocalDateTime createdAt;

    public EmailVerificationJpaEntity(String email, String verifyCation, verificationStatus status) {
        this.email = email;
        this.verification = verifyCation;
        this.status = status;
    }

}
