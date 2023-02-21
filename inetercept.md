<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="shop.mtcoding.test0221.model.UserRepository">
<configuration>
    <plugins>
        <plugin interceptor="com.example.MyBatisInterceptor">
            <!-- 설정값이 필요한 경우, properties 태그 안에 설정할 수 있음 -->
        </plugin>
    </plugins>
</configuration>
</mapper>

