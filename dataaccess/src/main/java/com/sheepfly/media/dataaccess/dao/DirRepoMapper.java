package com.sheepfly.media.dataaccess.dao;

import com.sheepfly.media.dataaccess.vo.DirRepoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DirRepoMapper {
    List<DirRepoVo> queryAll();
}
