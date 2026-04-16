package com.example.studentManagement.audit_log.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "audit_logs")
@Builder
public class AuditLogEntity {
    @Id
    @UuidGenerator
    @Column(name = "id", length = 50, updatable = false, nullable = false)
    private String id;

    @Column(name = "module", length = 50, nullable = false)
    private String module;

    @Column(name = "action", length = 50, nullable = false)
    private String action;

    @Column(name = "reference_id", length = 100)
    private String referenceId;

    @Column(name = "source_system", length = 100)
    private String sourceSystem;

    @Column(name = "request_time", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime requestTime = LocalDateTime.now();

    @Column(name = "execution_time")
    private LocalDateTime executionTime;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "error_message", length = 1000)
    private String errorMessage;

    @Lob
    @Column(name = "payload")
    private String payload;

    @Lob
    @Column(name = "response_body")
    private String responseBody;

    @Column(name = "ip_address", length = 50)
    private String ipAddress;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @PrePersist
    private void onCreate() {
        if (this.requestTime == null) {
            this.requestTime = LocalDateTime.now();
        }
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
