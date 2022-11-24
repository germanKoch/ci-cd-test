package com.german.ci.adapters.client;

import com.german.ci.domain.general.AuditEvent;
import com.german.ci.domain.client.AuditClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@Log4j2
@RequiredArgsConstructor
/**
 * It's assumed that audit events should be put to the message broker
 */
public class MockAuditClient implements AuditClient {

    private final AuditFeign auditFeign;

    @Override
    public void sendAuditEvent(AuditEvent auditEvent) {
        log.info("AuditEvent: {}", auditEvent);
        auditFeign.sendAuditEvent(auditEvent);
    }


    @FeignClient(value = "audit", url = "localhost:8082")
    interface AuditFeign {
        @PostMapping(value = "/audit-event", produces = "application/json")
        void sendAuditEvent(@RequestBody AuditEvent auditEvent);
    }
}
