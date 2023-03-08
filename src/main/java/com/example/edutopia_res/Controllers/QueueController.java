/*package com.example.edutopia_res.Controllers;

import com.example.edutopia_res.Iservices.IqueueService;
import com.example.edutopia_res.entities.Queue;
import com.example.edutopia_res.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/queue")
public class QueueController {
    @Autowired

    IqueueService queueService;





    @GetMapping
    public List<Queue> getAllQueues() {
        return queueService.getAllQueues();
    }

    @GetMapping("/{queueId}")
    public Queue getQueueById(@PathVariable int queueId) {
        return queueService.getQueueById(queueId);
    }

    @PostMapping
    public Queue createQueue(@RequestBody Queue queue) {
        return queueService.createQueue(queue);
    }

    @DeleteMapping("/{queueId}")
    public void deleteQueue(@PathVariable int queueId) {
        queueService.deleteQueue(queueId);
    }

    @PostMapping("/{queueId}/users")
    public void addUserToQueue(@PathVariable int queueId, @RequestBody User user) {
        queueService.addUserToQueue(queueId, user);
    }
}

*/