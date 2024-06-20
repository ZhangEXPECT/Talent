package com.example.talent.service;

import com.example.talent.module.entity.Vr;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.module.vo.VrVO;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-4-15
 * @version: 1.0
 */
public interface VrService {

    ResultVO add(Vr vr);

    ResultVO delete(Integer vrId);

    ResultVO queryId(Integer vrId);

    ResultVO update(Vr vr);

    ResultVO queryVrBYHouseId(Integer houseId);

    ResultVO queryBySelectActive(VrVO vrVo);

    ResultVO countBySelectActive(VrVO vrVo);
}
