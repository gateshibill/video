package com.cofc.service.video;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cofc.pojo.video.Carousel;

public interface CarouselService {
	public void addCarousel(Carousel Carousel);
	public void updateCarousel(Carousel Carousel);
	public void delCarousel(@Param("id")Integer id);
	public Carousel getCarouselById(@Param("id")Integer id);
	public Carousel getCarouselByAssemble(@Param("name")String name,@Param("posterUrl")String posterUrl);	
	public int getCarouselCount();
	public List<Carousel> getCarouselList();
}
