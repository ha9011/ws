package com.jade.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SampleMapper {
	
	@Select("select now()")
	public String getTime();

	@Select("select uname from User where uid = #{uid}")
	public String getUname(@Param("uid") String uid);
}
