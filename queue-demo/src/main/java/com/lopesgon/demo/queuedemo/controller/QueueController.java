package com.lopesgon.demo.queuedemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lopesgon.demo.queuedemo.model.DemoModel;
import com.lopesgon.demo.queuedemo.service.QueueService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/queue")
@AllArgsConstructor
public class QueueController {

  private final QueueService queueService;

  @PostMapping
  public boolean enqueueDemoData(@RequestBody DemoModel data) {
    return queueService.enqueue(data);
  }
}