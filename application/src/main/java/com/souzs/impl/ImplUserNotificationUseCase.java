package com.souzs.impl;

import com.souzs.core.domain.Transaction;
import com.souzs.gateway.UserNotificationGateway;
import com.souzs.usecase.UserNotificationUseCase;

public class ImplUserNotificationUseCase implements UserNotificationUseCase {
    private UserNotificationGateway userNotificationGateway;

    public ImplUserNotificationUseCase(UserNotificationGateway userNotificationGateway) {
        this.userNotificationGateway = userNotificationGateway;
    }

    @Override
    public void notificate(Transaction transaction) {
        userNotificationGateway.notificate(transaction);
    }
}
