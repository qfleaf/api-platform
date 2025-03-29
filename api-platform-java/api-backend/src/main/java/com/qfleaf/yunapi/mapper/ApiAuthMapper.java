package com.qfleaf.yunapi.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfleaf.yunapi.entity.ApiAuth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfleaf.yunapi.open.model.vo.ApiAuthPageVO;
import com.qfleaf.yunapi.open.model.vo.ApiAuthVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author qianfang
* @description 针对表【api_auth(API 授权表，管理用户对 API 的访问权限)】的数据库操作Mapper
* @createDate 2025-03-29 12:33:27
* @Entity com.qfleaf.yunapi.entity.ApiAuth
*/
public interface ApiAuthMapper extends BaseMapper<ApiAuth> {
    @Select("select id, auth_key, expire_at, created_at " +
            "from api_auth " +
            "${ew.customSqlSegment}")
    IPage<ApiAuthPageVO> selectPageVo(IPage<ApiAuthPageVO> page, @Param("ew") LambdaQueryWrapper<ApiAuth> queryWrapper);
    @Select("select id, auth_key, expire_at, created_at " +
            "from api_auth " +
            "where id = ${id} and user_id = ${currentUserId}")
    ApiAuthVO selectVoById(Long id, Long currentUserId);
}




