package com.lopesgon.demo.queuedemo.service;

import org.springframework.stereotype.Service;

import com.lopesgon.demo.queuedemo.model.DemoModel;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Service
public class ProcessService {

    public boolean process(Object obj) {
        if (obj.getClass().equals(DemoModel.class)) {
            DemoModel model = (DemoModel) obj;
            if (model.getValue1().length() > 3 && model.getValue2() > 5) {
                return true;
            } else {
                return false;
            }
        } else {
            log.warn("Object class [{}] doesn't match any supported type", obj.getClass());
            return false;
        }
    }
}
