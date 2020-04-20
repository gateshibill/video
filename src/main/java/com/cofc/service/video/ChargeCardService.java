package com.cofc.service.video;

import org.apache.ibatis.annotations.Param;

import com.cofc.pojo.video.ChargeCard;

public interface ChargeCardService {
	public void addChargeCard(ChargeCard chargeCard);

	public void updateChargeCard(ChargeCard chargeCard);

	public void delChargeCard(@Param("id") Integer id);

	public ChargeCard getChargeCardByCardId(@Param("cardId") Integer cardId);

	public ChargeCard getChargeCardByUserId(@Param("userId") Integer userId);
}
