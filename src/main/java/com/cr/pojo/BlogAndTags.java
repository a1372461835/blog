package com.cr.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_blog_tags")
public class BlogAndTags {
    private Integer id;
    private  Long blogsId;
    private  Long tagsId;

    public BlogAndTags() {
    }

    public BlogAndTags(Long blogsId, Long tagsId) {
        this.blogsId = blogsId;
        this.tagsId = tagsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getBlogsId() {
        return blogsId;
    }

    public void setBlogsId(Long blogsId) {
        this.blogsId = blogsId;
    }

    public Long getTagsId() {
        return tagsId;
    }

    public void setTagsId(Long tagsId) {
        this.tagsId = tagsId;
    }

    @Override
    public String toString() {
        return "BlogAndTags{" +
                "blogsId=" + blogsId +
                ", tagsId=" + tagsId +
                '}';
    }
}
