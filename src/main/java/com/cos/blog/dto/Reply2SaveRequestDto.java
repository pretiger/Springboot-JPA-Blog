package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply2SaveRequestDto {
	private int userId;
	private int boardId;
	private String content;
}
