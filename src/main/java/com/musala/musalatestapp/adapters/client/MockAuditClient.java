package com.musala.musalatestapp.adapters.client;

import com.musala.musalatestapp.domain.client.AuditClient;
import com.musala.musalatestapp.domain.general.AuditEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
/**
 * It's assumed that audit events should be put to the message broker
 */
public class MockAuditClient implements AuditClient {
    @Override
    public void sendAuditEvent(AuditEvent auditEvent) {
        log.info("AuditEvent: {}", auditEvent);
    }
}
