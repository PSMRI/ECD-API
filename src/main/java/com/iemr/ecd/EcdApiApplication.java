/*
* AMRIT – Accessible Medical Records via Integrated Technology
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.ecd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.iemr.ecd.dao.Users;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@OpenAPIDefinition(info = @Info(title = "ECD API", description = "The Early Childhood Development (ECD) Initiative by the Ministry of Health and Family Welfare (MoHFW) aims to nurture the cognitive capital of the country by enabling young children to attain their fullest potential. The initiative focuses on the critical period of brain development, which includes the 270 days of pregnancy and the first two years of the child's life, also known as the first 1,000 days.", version = "1.0", contact = @Contact(name = "AMRIT", url = "https://psmri.github.io/PSMRI/", email = "amrit@piramalswasthya.org")))
public class EcdApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcdApiApplication.class, args);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);

		// Use StringRedisSerializer for keys (userId)
		template.setKeySerializer(new StringRedisSerializer());

		// Use Jackson2JsonRedisSerializer for values (Users objects)
		Jackson2JsonRedisSerializer<Users> serializer = new Jackson2JsonRedisSerializer<>(Users.class);
		template.setValueSerializer(serializer);

		return template;
	}

}
