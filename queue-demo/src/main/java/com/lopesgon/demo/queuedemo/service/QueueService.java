package com.lopesgon.demo.queuedemo.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lopesgon.demo.queuedemo.model.DemoModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QueueService {
    private final ProcessService processService;
    private BlockingQueue<DemoModel> queue;
    private Thread dequeueTask;

    @Autowired
    public QueueService(ProcessService processService) {
        this.processService = processService;
        this.queue = new LinkedBlockingDeque<>(5); // queue is full if capacity is reached
        this.dequeueTask = new Thread(new DequeueTask<>(this.queue, processService));
        this.dequeueTask.start();
    }

    public boolean enqueue(DemoModel obj) {
        return this.queue.add(obj);
    }

    private class DequeueTask<E> implements Runnable {
        private BlockingQueue<E> queue;
        private ProcessService service;

        public DequeueTask(BlockingQueue<E> queue, ProcessService service) {
            this.queue = queue;
            this.service = service;
        }

        @Override
        public void run() {
            log.info("Dequeue thread task started..");
            try {
                while (true) {
                    E queueElement = this.queue.take();
                    if (queueElement != null) {
                        if (service.process(queueElement)) {
                            log.info("Queue element processed");
                        } else {
                            log.warn("Queue element failed to process.. skipping!");
                        }
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                log.error("Unexpected interrupted thread", e);
            } catch (IllegalStateException e) {
                log.error("Queue may be full! Max elements authorized is 5 for testing purpose.");
                throw e;
            }
        }

    }
}
