/*package com.example.edutopia_res.Iservices;

import com.example.edutopia_res.entities.Queue;
import com.example.edutopia_res.entities.Restaurant;
import com.example.edutopia_res.entities.User;

import java.time.LocalDateTime;
import java.util.List;

public interface IqueueService {
 //   Queue addUserToQueue(int restaurantId, User user);
 //   void removeUserFromQueue(Queue queue);
 //   LocalDateTime getEstimatedWaitTime(int restaurantId);
  //  List<Queue> getQueueForRestaurant(int restaurantId);

    int calculateEstimatedWaitTime(Queue queue);
    void addUserToQueue(int queueId, User user);
    List<Queue> getAllQueues();
    Queue getQueueById(int queueId);
    Queue createQueue(Queue queue);
    void deleteQueue(int queueId);
}
*/