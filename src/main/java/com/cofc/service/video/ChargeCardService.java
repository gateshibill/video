package com.cofc.service.video;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cofc.pojo.video.ChargeCard;

public interface ChargeCardService {
	public void addChargeCard(ChargeCard ChargeCard);

	public void updateChargeCard(ChargeCard ChargeCard);

	public void delChargeCard(@Param("id") Integer id);

	public ChargeCard getChargeCardById(@Param("id") Integer id);

	public ChargeCard getChargeCardByAssemble(@Param("name") String name, @Param("posterUrl") String posterUrl);

	public List<ChargeCard> getChargeCardListByAssemble(@Param("name") String name, @Param("status") Integer status,
			@Param("page") Integer page, @Param("limit") Integer limit);

	public int getChargeCardCountByAssemble(@Param("name") String name, @Param("status") Integer status);

	public int getChargeCardCount();

	public List<ChargeCard> getChargeCardList(@Param("page") Integer page, @Param("limit") Integer limit);

	public List<ChargeCard> getChargeCardListByType(@Param("type") Integer type, @Param("subType") Integer subType,
			@Param("page") Integer page, @Param("limit") Integer limit);

}
