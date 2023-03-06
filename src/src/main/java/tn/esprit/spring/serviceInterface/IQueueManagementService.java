package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.QueueStatus;
import tn.esprit.spring.entities.User;

public interface IQueueManagementService {
    QueueStatus getQueueStatus();

    void addUserToQueue(User user);

    void removeUserFromQueue(User user);
}
