package com.musala.musalatestapp.domain.client;

import com.musala.musalatestapp.domain.general.AuditEvent;

public interface AuditClient {

    void sendAuditEvent(AuditEvent auditEvent);

}
