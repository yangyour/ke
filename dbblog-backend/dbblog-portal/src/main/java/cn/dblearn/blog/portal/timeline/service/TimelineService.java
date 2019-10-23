package cn.dblearn.blog.portal.timeline.service;

import cn.dblearn.blog.entity.timeline.Timeline;

import java.util.List;

/**
 * TimeLineService
 *
 * @description
 */
public interface TimelineService {

    /**
     * 获取timeLine数据
     * @return
     */
    List<Timeline> listTimeLine();
}
