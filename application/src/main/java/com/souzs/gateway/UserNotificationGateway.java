package com.souzs.gateway;

import com.souzs.core.domain.Transaction;

public interface UserNotificationGateway {
    void notificate(Transaction transaction);
}
