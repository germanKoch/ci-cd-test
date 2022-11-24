package com.german.ci.domain.client;

import com.german.ci.domain.general.AuditEvent;

public interface AuditClient {

    void sendAuditEvent(AuditEvent auditEvent);

}
