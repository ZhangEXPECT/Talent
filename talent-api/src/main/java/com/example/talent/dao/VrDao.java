package com.example.talent.dao;

import com.example.talent.module.entity.Vr;
import com.example.talent.module.vo.VrVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VrDao {

    void add(Vr vr);

    void delete(Integer vrId);

    Vr queryId(Integer vrId);

    void update(Vr vr);

    Vr queryVrBYHouseId(Integer houseId);

    List<Vr> queryBySelectActive(VrVO vrVo);

    Integer countBySelectActive(VrVO vrVo);

}
