package com.example.user.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @Author ZhangQiangLong
 * @Date 2020/8/14 16:01
 * @description:
 */
@RestController
@RequestMapping(path = "/tasks",produces = "application/json;charset=gbk")
public class TaskController {

    @GetMapping
    public String listTasks(){
        return "任务列表";
    }

    @PostMapping
    public String newTasks(){
        return "创建了一个新的任务";
    }

    @PutMapping("/{taskId}")
    public String updateTasks(@PathVariable("taskId")Integer id){
        return "更新了一下id为:"+id+"的任务";
    }

    @DeleteMapping("/{taskId}")
    public String deleteTasks(@PathVariable("taskId")Integer id){
        return "删除了id为:"+id+"的任务";
    }
}