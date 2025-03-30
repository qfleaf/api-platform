package com.qfleaf.yunapi.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfleaf.yunapi.model.entity.ApiInfo;
import com.qfleaf.yunapi.model.vo.ApiInfoPageVO;
import com.qfleaf.yunapi.model.vo.ApiInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author qianfang
* @description 针对表【api_info(API 信息表，存储平台所有对外提供的接口信息)】的数据库操作Mapper
* @createDate 2025-03-29 12:33:27
* @Entity com.qfleaf.yunapi.entity.ApiInfo
*/
public interface ApiInfoMapper extends BaseMapper<ApiInfo> {
    @Select("select id, name, endpoint, method, status " +
            "from api_info " +
            "${ew.customSqlSegment}")
    IPage<ApiInfoPageVO> selectPageVo(IPage<ApiInfoPageVO> page, @Param("ew") LambdaQueryWrapper<ApiInfo> queryWrapper);
    @Select("select id, name, description, endpoint, method, version, status, created_at, updated_at " +
            "from api_info " +
            "where id = ${id}")
    ApiInfoVO selectVoById(Long id);
}




