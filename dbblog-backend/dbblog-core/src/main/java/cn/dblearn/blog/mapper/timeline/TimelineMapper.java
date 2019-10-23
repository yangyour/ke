package cn.dblearn.blog.mapper.timeline;

import cn.dblearn.blog.entity.timeline.Timeline;
import cn.dblearn.blog.entity.timeline.TimelinePost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TimeLineMapper
 *
 * @description
 */
@Mapper
public interface TimelineMapper {

    List<TimelinePost> listTimelinePost(@Param("year") Integer year, @Param("month") Integer month);

    List<Timeline> listTimeline();
}
