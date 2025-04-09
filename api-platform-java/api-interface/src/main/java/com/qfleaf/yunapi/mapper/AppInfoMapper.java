package com.qfleaf.yunapi.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfleaf.yunapi.model.entity.AppInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfleaf.yunapi.model.vo.AppInfoPageVO;
import com.qfleaf.yunapi.model.vo.AppInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author qianfang
* @description 针对表【app_info(应用信息表，记录开发者的应用信息)】的数据库操作Mapper
* @createDate 2025-03-29 12:33:27
* @Entity com.qfleaf.yunapi.entity.AppInfo
*/
public interface AppInfoMapper extends BaseMapper<AppInfo> {
    @Select("select id, name, created_at " +
            "from app_info " +
            "${ew.customSqlSegment}")
    IPage<AppInfoPageVO> selectPageVo(IPage<AppInfoPageVO> page, @Param("ew") LambdaQueryWrapper<AppInfo> queryWrapper);
    @Select("select t1.id, username as developer, name, description, t1.created_at " +
            "from app_info t1 " +
            "left join users t2 " +
            "on t2.id = t1.user_id " +
            "where t1.id = ${id}")
    AppInfoVO selectVoById(long id);
}




