package com.qfleaf.yunapi.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfleaf.yunapi.model.entity.ApiRequestLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfleaf.yunapi.model.vo.ApiRequestLogPageVO;
import com.qfleaf.yunapi.model.vo.ApiRequestLogVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author qianfang
* @description 针对表【api_request_log(API 请求日志表，记录每次 API 调用的信息)】的数据库操作Mapper
* @createDate 2025-03-29 12:33:27
* @Entity com.qfleaf.yunapi.entity.ApiRequestLog
*/
public interface ApiRequestLogMapper extends BaseMapper<ApiRequestLog> {
    @Select("select t1.id, t2.name as api_name, t4.username as request_user, t1.request_time, t1.response_status, t1.response_time " +
            "from api_request_log t1 " +
            "inner join api_info t2 on t2.id = t1.api_id " +
            "inner join api_auth t3 on t3.auth_key = t1.auth_key " +
            "inner join users t4 on t4.id = t3.user_id " +
            "${ew.customSqlSegment}")
    IPage<ApiRequestLogPageVO> selectPageVo(IPage<ApiRequestLogPageVO> page, @Param("ew") LambdaQueryWrapper<ApiRequestLog> queryWrapper);
    @Select("select t1.id, t2.name as api_name, t4.username as request_user, t1.request_time, t1.response_status, t1.response_time " +
            "from api_request_log t1 " +
            "inner join api_info t2 on t2.id = t1.api_id " +
            "inner join api_auth t3 on t3.auth_key = t1.auth_key " +
            "inner join users t4 on t4.id = t3.user_id " +
            "where t1.id = ${id}")
    ApiRequestLogVO selectVoById(Long id);
}




