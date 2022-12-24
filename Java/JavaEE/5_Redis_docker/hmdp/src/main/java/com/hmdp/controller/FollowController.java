package com.hmdp.controller;


import com.hmdp.dto.Result;
import com.hmdp.service.IFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private IFollowService followService;

    /**
     * 关注或者取关
     *
     * @param id
     * @param isFollowed
     * @return
     */
    @PutMapping("/{id}/{isFollowed}")
    public Result followUser(@PathVariable("id") Long id, @PathVariable("isFollowed") boolean isFollowed) {
        return followService.followUser(id, isFollowed);
    }

    /**
     * 查询是否关注
     *
     * @param id
     * @return
     */
    @GetMapping("/or/not/{id}")
    public Result followOrNot(@PathVariable("id") Long id) {
        return followService.followOrNot(id);
    }

    @GetMapping("/common/{id}")
    public Result followCommons(@PathVariable("id") Long id) {
        return followService.followCommons(id);
    }
}
