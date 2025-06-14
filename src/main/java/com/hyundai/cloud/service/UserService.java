package com.hyundai.cloud.service;

import com.hyundai.cloud.dto.PatchUserResponseDto;
import com.hyundai.cloud.dto.ResponseDto;
import com.hyundai.cloud.entity.UserEntity;
import com.hyundai.cloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseDto<PatchUserResponseDto> patchUser(PatchUserResponseDto dto, String userEmail){
        UserEntity userEntity = null;
        UserEntity user = dto.getUser();
        String userNickname = user.getUserNickname();

        try{
            userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null){
                return ResponseDto.setFailed("Does Not Exist User");
            }
            userEntity.setUserNickname(userNickname);

            userRepository.save(userEntity);
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }

        userEntity.setUserPassword("");
        PatchUserResponseDto patchUserResponseDto = new PatchUserResponseDto(userEntity);
        return ResponseDto.setSuccess("Success", patchUserResponseDto);
    }
}