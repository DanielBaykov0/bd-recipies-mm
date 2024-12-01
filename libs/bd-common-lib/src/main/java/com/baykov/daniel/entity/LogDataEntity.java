package com.baykov.daniel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "log_daily")
public class LogDataEntity extends BaseEntity {

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime insertDate;

    private String serviceName;

    @Column(nullable = false, length = 10)
    private String httpMethod;

    @Column(nullable = false)
    private String endpoint;

    @Column(length = 4000)
    private String requestBody;

    @Column(length = 4000)
    private String requestHeaders;

    private Integer responseHttpStatusCode;

    private String ipAddress;

    private String userAgent;

    private Long timeTakenMs;
}
