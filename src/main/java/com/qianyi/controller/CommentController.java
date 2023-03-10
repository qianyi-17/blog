package com.qianyi.controller;

import com.qianyi.constants.SystemConstants;
import com.qianyi.domain.ResponseResult;
import com.qianyi.domain.dto.AddCommentDto;
import com.qianyi.domain.entity.Comment;
import com.qianyi.service.CommentService;
import com.qianyi.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论区接口
 * @author 千亦
 * @create 2022-10-27-16:14
 */
@RestController
@RequestMapping("/comment") //指定请求映射规则
@Api(tags = "评论",description = "评论相关接口")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 文章评论接口
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/commentList")
    @ApiOperation(value = "评论列表",notes = "获取一页评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页号"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小")
    }
    )
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        ResponseResult result=commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
        return result;
    }

    /**
     * 发表评论接口
     * @param addComment
     * @return
     */
    @PostMapping

    public ResponseResult addComment(@RequestBody AddCommentDto addComment){
        Comment comment = BeanCopyUtils.copyBean(addComment, Comment.class);
        ResponseResult result=commentService.addComment(comment);
        return result;
    }

    /**
     * 友链评论接口
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return
     */
    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        ResponseResult result= commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
        return result;
    }


}
